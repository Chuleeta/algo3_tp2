package edu.fiuba.algo3.modelo.Edificios;

import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Recursos.Mena;
import edu.fiuba.algo3.modelo.Exceptions.MenaOcupadaException;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.Exceptions.VolcanOcupadoException;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.VidaEscudoProtoss;
import edu.fiuba.algo3.modelo.Zonas.ZonaEnergia;
import edu.fiuba.algo3.modelo.Zonas.ZonaMoho;
import edu.fiuba.algo3.modelo.Zonas.ZonaNeutral;

public class NexoMineral extends Edificio{
    private Mineral mineral;
    private Mena mena;
    private static int VIDA_ESCUDO_COMPLETO = 250;

    public NexoMineral(Posicion posicion, Mena mena, Mapa mapa) throws MenaOcupadaException {
        this.mena = mena;
        this.mena.ocupar();
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.zona = new ZonaNeutral();
        this.mapa = mapa;
        this.vida = new VidaEscudoProtoss(VIDA_ESCUDO_COMPLETO, VIDA_ESCUDO_COMPLETO);
        if(this.jugador == null){
            crearJugadorPorDefecto();
        }
    }

    public NexoMineral(Posicion posicion, Mena mena, Jugador jugador) throws MenaOcupadaException {
        this(posicion, mena, jugador.getMapa());
        this.jugador = jugador;
    }
    
    public NexoMineral(Posicion posicion, Jugador jugador) throws MenaOcupadaException, RecursosInsuficientesException {
        // this.mena = mena;
        // this.mena.ocupar();
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.zona = new ZonaNeutral();
        this.jugador = jugador;
        this.mapa = jugador.getMapa();
        this.vida = new VidaEscudoProtoss(VIDA_ESCUDO_COMPLETO, VIDA_ESCUDO_COMPLETO);
        if(!this.mapa.inyectarRecurso(this))  throw new MenaOcupadaException();
        if(this.jugador.agregarConstruccion(this)){
            throw new RecursosInsuficientesException();
        }
    }

    public void pasarTiempo() throws NoExisteEdificioCorrelativoException
    {
        tiempo += 1;
        this.estado = this.estado.desarrollar(this, 4, tiempo);
    }

    @Override
    public void construir()
    {
        estado = new EstadoConstruido();
        mapa.agregarZona(zona);
    }

    @Override
    public boolean habita(ZonaNeutral zona) {
        return true;
    }

    @Override
    public boolean habita(ZonaMoho zona) {
        return false;
    }

    @Override
    public boolean habita(ZonaEnergia zona) {
        return true;
    }


    public boolean tieneVidaCompleta(){
        return this.vida.tieneVidaCompleta();
    }
    public boolean tieneEscudoCompleto(){
        return this.vida.tieneEscudoCompleto();
    }

    @Override
    public boolean agregarAlMapa(Mineral mineral, GasVespeno gas) {
        this.mineral = mineral;
        if(mineral.invertir(50))
        {
            this.jugador.agregarEnListaConstruccion(this);
            return true;
        }
        return false;
    }

    @Override
    public void actualizar() {
        mineral.agregarMineral(mena.extraerMineral(100));
        this.vida.regenerar();
    }

    public void setMena(Mena mena) throws MenaOcupadaException{
        mena.ocupar();
        this.mena = mena;
    }

    @Override
    public boolean verificarCorrelativa(Espiral espiral) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean verificarCorrelativa(Guarida guarida) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean verificarCorrelativa(PuertoEstelar puertoEstelar) {
        // TODO Auto-generated method stub
        return false;
    }
}
