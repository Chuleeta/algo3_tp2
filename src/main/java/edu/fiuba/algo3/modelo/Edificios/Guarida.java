package edu.fiuba.algo3.modelo.Edificios;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Individuos.Hidralisco;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Zonas.ZonaEnergia;
import edu.fiuba.algo3.modelo.Zonas.ZonaMoho;
import edu.fiuba.algo3.modelo.Zonas.ZonaNeutral;

public class Guarida extends Edificio{
    private static int VIDA_COMPLETA = 1250;

    public Guarida(Posicion posicion, Mapa mapa)
    {
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.mapa = mapa;
        this.zona = new ZonaMoho(this.posicion);
        tiempo = 0;
        this.vida = new VidaZerg(VIDA_COMPLETA);
        if(this.jugador == null){
            crearJugadorPorDefecto();
        }
    }

    public Guarida(Posicion posicion, Jugador jugador) {
        this(posicion, jugador.mapa);
        this.jugador = jugador;
    }

    public void pasarTiempo() throws NoExisteEdificioCorrelativoException {
        tiempo += 1;
        this.estado = this.estado.desarrollar(this, 12, tiempo);
    }

    @Override
    public void construir() throws NoExisteEdificioCorrelativoException {
        if (!this.mapa.verificarEdificacionCorrelativa(jugador, new ReservaDeReproduccion(new Posicion(0,0), new Mapa()))){
            throw new NoExisteEdificioCorrelativoException();
        }
        estado = new EstadoConstruido();
    }

    @Override
    public boolean habita(ZonaNeutral zona) {
        return false;
    }

    @Override
    public boolean habita(ZonaMoho zona) {
        return zona.abarca(posicion);
    }

    @Override
    public boolean habita(ZonaEnergia zona) {
        return false;
    }


    public boolean tieneVidaCompleta(){
        return this.vida.tieneVidaCompleta();
    }

    @Override
    public boolean agregarAlMapa(Mineral mineral, GasVespeno gas) {
        if(mineral.invertir(200)&& gas.invertir(100))
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
    public Hidralisco generarHidralisco(Mineral mineral, GasVespeno gas, Larva larva) throws RequerimientosInsuficientesException {
        return new Hidralisco(mineral, gas, new Posicion(3,3), this.mapa);
    }
}
