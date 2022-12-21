package edu.fiuba.algo3.modelo.Individuos;

import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Exceptions.AccesoNoDisponibleException;
import edu.fiuba.algo3.modelo.Exceptions.EspiralNoDisponibleException;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.PuertoEstelarNoDisponibleException;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.VidaZerg;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

public class Mutalisco extends Individuo implements UnidadVoladora{

    private final int tiempoDeConstruccion;
    private int tiempo;

    public Mutalisco(Mineral mineral, GasVespeno gas, Posicion posicion, Mapa mapa) throws RequerimientosInsuficientesException {
        if (!mineral.invertir(100) | !gas.invertir(100)) {
            throw new RequerimientosInsuficientesException();
        }
        this.vida = new VidaZerg(120);
        this.mapa = mapa;
        this.unidadesDeDañoAereo = 9;
        this.unidadesDeDañoTerrestre = 9;
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 7;
        this.tiempo = 0;
        rangoDeAtaque= 3;
        this.posicion = posicion;
    }
    public Mutalisco(Posicion posicion, Jugador jugador) throws RequerimientosInsuficientesException, EspiralNoDisponibleException, NoExisteEdificioCorrelativoException {
        this.vida = new VidaZerg(120);
        this.mapa = jugador.getMapa();
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 7;
        this.unidadesDeDañoAereo = 9;
        this.unidadesDeDañoTerrestre = 9;
        this.tiempo = 0;
        this.rangoDeAtaque= 3;
        this.posicion = posicion;
        this.jugador = jugador;
        jugador.añadirUnidad();
        this.jugador.verificarEdificacionCorrelativa(this);
        if(!this.jugador.agregarIndividuo(this) || !this.mapa.agregarOcupable(this, posicion)){
            throw new RequerimientosInsuficientesException();
        }
    }

    private void construir() {
        this.estado = new EstadoConstruido();
    }

    @Override
    public boolean recibirAtaqueAereo(int unidades) {
        vida.dañar(unidades);
        return true;
    }

    @Override
    public boolean recibirAtaqueTerrestre(int unidades) {
        return false;
    }

    public void pasarTiempo() {
        this.tiempo += 1;
        if (estado.puedeConstruirse(this.tiempoDeConstruccion, this.tiempo )) construir();
    }

    public boolean atacar(Individuo individuo)
    {
        if (estado.estaConstruido() && estaDentroDelRango(individuo.posicion())) {
            if(individuo.recibirAtaqueTerrestre(unidadesDeDañoTerrestre)){
                return true;
            }
            return individuo.recibirAtaqueAereo(unidadesDeDañoAereo);
        }
        return false;
    }


    public boolean mover(Posicion posicion)
    {
        this.posicion = posicion;
        return true;
    }

    public Guardian evolucionarAGurdian(Mineral mineral, GasVespeno gas) throws RequerimientosInsuficientesException
    {
        return new Guardian(mineral, gas, this.posicion, this.mapa);
    }

    public Devorador evolucionarADevorador(Mineral mineral, GasVespeno gas) throws RequerimientosInsuficientesException
    {
        return new Devorador(mineral, gas, this.posicion, this.mapa);
    }

    @Override
    public boolean agregarAlMapa(Mineral mineral, GasVespeno gas) {
        if(mineral.invertir(100)&& gas.invertir(100))
        {
            this.jugador.agregarEnListaIndividuo(this);
            return true;
        }
        return false;
    }

    @Override
    public boolean validarCorrelativaEvolucion(Devorador devorador) {
        return true;
    }

    @Override
    public boolean validarCorrelativaEvolucion(Guardian guardian) {
        return true;
    }

}
