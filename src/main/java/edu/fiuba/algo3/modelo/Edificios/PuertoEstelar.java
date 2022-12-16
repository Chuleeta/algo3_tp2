package edu.fiuba.algo3.modelo.Edificios;

import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.VidaEscudoProtoss;
import edu.fiuba.algo3.modelo.Zonas.ZonaEnergia;
import edu.fiuba.algo3.modelo.Zonas.ZonaMoho;
import edu.fiuba.algo3.modelo.Zonas.ZonaNeutral;

public class PuertoEstelar extends Edificio {

    public PuertoEstelar(Posicion posicion, Mapa mapa) {
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.zona = new ZonaEnergia(this.posicion);
        this.mapa = mapa;
        tiempo = 0;
        this.vida = new VidaEscudoProtoss(600, 600);
        if(this.jugador == null){
            crearJugadorPorDefecto();
        }
    }

    public PuertoEstelar(Posicion posicion, Mapa mapa, Jugador jugador) {
        this(posicion, mapa);
        this.jugador = jugador;
    }

    @Override
    public void pasarTiempo() throws NoExisteEdificioCorrelativoException {
        tiempo += 1;
        this.estado = this.estado.desarrollar(this, 10, tiempo);
    }


    @Override
    public void construir() throws NoExisteEdificioCorrelativoException {
        if (!this.mapa.verificarEdificacionCorrelativa(jugador, new Acceso(new Posicion(0,0), new Mapa()))){
            throw new NoExisteEdificioCorrelativoException();
        }
        this.estado = new EstadoConstruido();
    }

    @Override
    public boolean habita(ZonaNeutral zona) {
        return false;
    }

    @Override
    public boolean habita(ZonaMoho zona) {
        return false;
    }

    @Override
    public boolean habita(ZonaEnergia zona) {
        if(this.mapa.hayMohoEnPosicion(this.posicion)) return false;
        return zona.abarca(posicion);
    }

    public boolean tieneVidaCompleta() {
        return this.vida.tieneVidaCompleta();
    }

    public boolean tieneEscudoCompleto() {
        return this.vida.tieneEscudoCompleto();
    }

    @Override
    public boolean agregarAlMapa(Mineral mineral, GasVespeno gas) {
        if(mineral.invertir(150) && gas.invertir(150))
        {
            this.jugador.agregarEnListaConstruccion(this);
            return true;
        }
        return false;
    }

    @Override
    public void actualizar() {
        this.vida.regenerar();
    }

}
