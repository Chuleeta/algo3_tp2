package edu.fiuba.algo3.modelo.Edificios;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import edu.fiuba.algo3.modelo.Recursos.RecursoInyectable;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.Recursos.Volcan;
import edu.fiuba.algo3.modelo.Exceptions.VolcanOcupadoException;
import edu.fiuba.algo3.modelo.Individuos.Dragon;
import edu.fiuba.algo3.modelo.Individuos.Hidralisco;
import edu.fiuba.algo3.modelo.Individuos.Mutalisco;
import edu.fiuba.algo3.modelo.Individuos.Scout;
import edu.fiuba.algo3.modelo.Individuos.Zangano;
import edu.fiuba.algo3.modelo.Individuos.Zealot;
import edu.fiuba.algo3.modelo.Individuos.Zerling;
import edu.fiuba.algo3.modelo.Zonas.ZonaEnergia;
import edu.fiuba.algo3.modelo.Zonas.ZonaMoho;
import edu.fiuba.algo3.modelo.Zonas.ZonaNeutral;

public class Extractor extends Edificio{

    private List<Zangano> zanganos;
    private static int VIDA_COMPLETA = 750;
    private GasVespeno gas;
    //private VidaZerg vida;
    private Volcan volcan;

    public Extractor(Posicion posicion, Volcan volcan, Mapa mapa) throws VolcanOcupadoException {
        this.volcan = volcan;
        this.volcan.ocupar();
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.mapa = mapa;
        zona = new ZonaMoho(this.posicion);
        zanganos = new ArrayList<>();
        tiempo = 0;
        this.vida = new VidaZerg(VIDA_COMPLETA);
        this.gas = new GasVespeno(0);
        // if(this.jugador == null){
        //     crearJugadorPorDefecto();
        // }
    }

    public Extractor(Posicion posicion, Volcan volcan, Jugador jugador) throws VolcanOcupadoException, RecursosInsuficientesException {
        this(posicion, volcan, jugador.getMapa());
        this.jugador = jugador;
        if(!this.jugador.agregarConstruccion(this) || !this.mapa.agregarOcupable(this, posicion)){
            throw new RecursosInsuficientesException();
        }
    }

    public Extractor(Posicion posicion, Jugador jugador) throws VolcanOcupadoException, RecursosInsuficientesException {
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.jugador = jugador;
        this.mapa = jugador.getMapa();
        zona = new ZonaMoho(this.posicion);
        zanganos = new ArrayList<>();
        tiempo = 0;
        this.vida = new VidaZerg(VIDA_COMPLETA);
        this.gas = new GasVespeno(0);
        if(!this.mapa.inyectarRecurso(this)) throw new VolcanOcupadoException();
        //if(!this.jugador.agregarConstruccion(this) || !this.mapa.agregarOcupable(this, posicion)){
        if(!this.jugador.agregarConstruccion(this)){
            throw new RecursosInsuficientesException();
        }
    }

    public void pasarTiempo() throws NoExisteEdificioCorrelativoException
    {
        tiempo += 1;
        this.estado = this.estado.desarrollar(this, 6, tiempo);
    }

    public Integer obtenerGas()
    {
        return gas.getCantidad();
    }

    public void extraerGas(){
        if(zanganos.size() != 0){
            for(Zangano zangano: zanganos){
                this.gas.agregarGas(zangano.extraerGas(this.volcan));
            }
            jugador.incrementarGas(this.gas.getCantidad());
        }
    }

    public void agregarZangano(Zangano zangano)
    {
        if(zanganos.size() < 3)
            zanganos.add(zangano);
    }


    @Override
    public void construir()
    {
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

    // public void dañar(int daño){
    //     this.vida.dañar(daño);
    // }

    public boolean tieneVidaCompleta(){
        return this.vida.tieneVidaCompleta();
    }

    @Override
    public boolean agregarAlMapa(Mineral mineral, GasVespeno gas) {
        if(mineral.invertir(100))
        {
            this.jugador.agregarEnListaConstruccion(this);
            /*this.mapa.agregarEnListaConstruccion(this);
            this.mapa.agregarEnListaConstruccionZerg(this);*/
            return true;
        }
        return false;
    }

    @Override
    public void actualizar(){
        this.vida.regenerar();
        extraerGas();
    }

    public void setVolcan(Volcan volcan) throws VolcanOcupadoException{
        volcan.ocupar();
        this.volcan = volcan;
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

    @Override
    public boolean verificarCorrelativa(Zangano zangano) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean verificarCorrelativa(Zerling zerling) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean verificarCorrelativa(Hidralisco hidralisco) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean verificarCorrelativa(Mutalisco mutalisco) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean verificarCorrelativa(Zealot zealot) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean verificarCorrelativa(Dragon dragon) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean verificarCorrelativa(Scout scout) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getSpray(){
        return "/imagenes/extractor.png";
    }
    /*public void destruir()
    {
        this.mapa.destruirConstruccion(this);
        this.mapa.destruirConstruccionZerg(this);
    }*/

    public boolean compararPosicionConOtroRecurso(RecursoInyectable recurso) {
        return recurso.estaOcupada(this.posicion);
    }

    public void desocupar(Zangano zangano) {
        this.zanganos.remove(zangano);
    }

    // @Override
    // public boolean estaOcupada(Posicion posicionDada) {
    //     return this.posicion.equals(posicionDada);
    // }

}
