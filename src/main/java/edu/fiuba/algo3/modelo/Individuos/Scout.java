package edu.fiuba.algo3.modelo.Individuos;

import edu.fiuba.algo3.modelo.Exceptions.AccesoNoDisponibleException;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.PuertoEstelarNoDisponibleException;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.VidaEscudoProtoss;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

public class Scout extends Individuo implements UnidadVoladora{
    private final int tiempoDeConstruccion;
    private int tiempo;
    public Scout(Mineral mineral, GasVespeno gas, Posicion posicion, Mapa mapa) throws RequerimientosInsuficientesException {
        if (!mineral.invertir(300) | !gas.invertir(150)) {
            throw new RequerimientosInsuficientesException();
        }
        this.unidadesDeDaño = 8;
        this.vida = new VidaEscudoProtoss(100, 80);
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 9;
        this.tiempo = 0;
        this.rangoDeAtaque = 4;
        this.posicion = posicion;
        this.mapa = mapa;
    }

    public Scout(Posicion posicion, Jugador jugador) throws RequerimientosInsuficientesException, PuertoEstelarNoDisponibleException, NoExisteEdificioCorrelativoException {
        this.unidadesDeDaño = 8;
        this.vida = new VidaEscudoProtoss(100, 80);
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 9;
        this.tiempo = 0;
        this.rangoDeAtaque = 4;
        this.posicion = posicion;
        this.mapa = jugador.getMapa();
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

    public void pasarTiempo() {
        this.tiempo += 1;
        if (estado.puedeConstruirse(this.tiempoDeConstruccion, this.tiempo )) construir();
    }

    public boolean atacar(UnidadTierra unidad)
    {
        if (estado.estaConstruido() && estaDentroDelRango(unidad.posicion())) {
            unidad.recibirDaño(8);
            return true;
        }
        return false;
    }

    public boolean atacar(UnidadVoladora unidad)
    {
        if (estado.estaConstruido() && estaDentroDelRango(unidad.posicion())) {
            unidad.recibirDaño(14);
            return true;
        }
        return false;
    }

    public boolean mover(Posicion posicion)
    {
        this.posicion = posicion;
        return true;
    }

    @Override
    public boolean agregarAlMapa(Mineral mineral, GasVespeno gas) {
        if(mineral.invertir(300)&& gas.invertir(150))
        {
            this.jugador.agregarEnListaIndividuo(this);
            return true;
        }
        return false;
    }
}
