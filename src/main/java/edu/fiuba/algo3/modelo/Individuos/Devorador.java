package edu.fiuba.algo3.modelo.Individuos;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Exceptions.EspiralNoDisponibleException;
import edu.fiuba.algo3.modelo.Exceptions.GuaridaNoDisponibleException;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.VidaZerg;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

public class Devorador extends Individuo implements UnidadVoladora{

    private final int tiempoDeConstruccion;
    private int tiempo;

    public Devorador (Mineral mineral, GasVespeno gas, Posicion posicion, Mapa mapa) throws RequerimientosInsuficientesException{
        if (!mineral.invertir(150) | !gas.invertir(50)) {
            throw new RequerimientosInsuficientesException();
        }
        this.mapa = mapa;
        this.vida = new VidaZerg(100);
        this.unidadesDeDañoAereo = 15;
        this.unidadesDeDañoTerrestre = 0;
        this.rangoDeAtaque = 5;
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 4;
        this.tiempo = 0;
        this.posicion = posicion;
    }

    public Devorador(Posicion posicion, Jugador jugador) throws RequerimientosInsuficientesException, EspiralNoDisponibleException {

        this.mapa = jugador.getMapa();
        this.vida = new VidaZerg(100);
        this.unidadesDeDañoAereo = 15;
        this.unidadesDeDañoTerrestre = 0;
        this.rangoDeAtaque = 5;
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 4;
        this.tiempo = 0;
        this.posicion = posicion;
        this.jugador = jugador;
        jugador.añadirUnidad();
        if(!this.jugador.agregarIndividuo(this) || !this.jugador.validarCorrelativaEvolucion(this)){
            throw new RequerimientosInsuficientesException();
        }
        this.mapa.agregarOcupable(this, this.posicion);
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
    public boolean recibirAtaqueTerrestre(int unidades, Posicion posicionAtacante) {
        return false;
    }

    public void pasarTiempo() {
        this.tiempo += 1;
        if (estado.puedeConstruirse(this.tiempoDeConstruccion, this.tiempo )) construir();
    }

    @Override
    public boolean mover(Posicion posicion) {
        this.posicion = posicion;
        return true;
    }

    @Override
    public boolean atacar(Individuo individuo) {
        if (estado.estaConstruido() && estaDentroDelRango(individuo.posicion())) {
            return individuo.recibirAtaqueAereo(unidadesDeDañoAereo);
        }
        return false;
    }

    public boolean atacar(Edificio edificio) {
        if (estado.estaConstruido()) {
            if (estaDentroDelRango(edificio.posicion())) {
                edificio.dañar(unidadesDeDañoAereo);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean agregarAlMapa(Mineral mineral, GasVespeno gas) {
        if(mineral.invertir(150)&& gas.invertir(50))
        {
            this.jugador.agregarEnListaIndividuo(this);
            return true;
        }
        return false;
    }

    @Override
    public String getSpray(){
        return "/imagenes/devorador.png";
    }
}
