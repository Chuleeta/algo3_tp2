package edu.fiuba.algo3.modelo.Edificios;

import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Exceptions.maximaPoblacionAlcanzadaException;
import edu.fiuba.algo3.modelo.Individuos.Dragon;
import edu.fiuba.algo3.modelo.Individuos.Hidralisco;
import edu.fiuba.algo3.modelo.Individuos.Mutalisco;
import edu.fiuba.algo3.modelo.Individuos.Scout;
import edu.fiuba.algo3.modelo.Individuos.Zangano;
import edu.fiuba.algo3.modelo.Individuos.Zealot;
import edu.fiuba.algo3.modelo.Individuos.Zerling;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.VidaEscudoProtoss;
import edu.fiuba.algo3.modelo.Zonas.ZonaEnergia;
import edu.fiuba.algo3.modelo.Zonas.ZonaMoho;
import edu.fiuba.algo3.modelo.Zonas.ZonaNeutral;

public class Acceso extends Edificio{

    public Acceso(Posicion posicion, Mapa mapa)
    {
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.mapa = mapa;
        tiempo = 0;
        this.zona = new ZonaEnergia(this.posicion);
        this.vida = new VidaEscudoProtoss(500, 500);
        // if(this.jugador == null){
        //     crearJugadorPorDefecto();
        // }
    }

    public Acceso(Posicion posicion, Jugador jugador) throws RecursosInsuficientesException {
        this(posicion, jugador.getMapa());
        this.jugador = jugador;
        if(!this.jugador.agregarConstruccion(this) || !this.mapa.agregarOcupable(this, posicion)){
            throw new RecursosInsuficientesException();
        }
    }

    public void pasarTiempo() throws NoExisteEdificioCorrelativoException {
        this.tiempo += 1;
        this.estado = this.estado.desarrollar(this, 8, tiempo);
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
        return false;
    }

    @Override
    public boolean habita(ZonaEnergia zona) {
        if(this.mapa.hayMohoEnPosicion(this.posicion)) return false;
        return zona.abarca(posicion);
    }

    public boolean tieneVidaCompleta(){
        return this.vida.tieneVidaCompleta();
    }

    public boolean tieneEscudoCompleto(){
        return this.vida.tieneEscudoCompleto();
    }

    @Override
    public boolean agregarAlMapa(Mineral mineral, GasVespeno gas) {
        if(mineral.invertir(150))
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

    public Zealot crearZealot(Mineral mineral) throws RequerimientosInsuficientesException, maximaPoblacionAlcanzadaException {
        if (jugador.unidadesDisponibles()) {
            jugador.añadirUnidad();
            return new Zealot(mineral, posicion.clone(),this.mapa);
        }
        throw new maximaPoblacionAlcanzadaException();
    }

    @Override
    public boolean verificarCorrelativa(Espiral espiral) {
        return false;
    }

    @Override
    public boolean verificarCorrelativa(Guarida guarida) {
        return false;
    }

    @Override
    public boolean verificarCorrelativa(PuertoEstelar puertoEstelar) {
        return true;
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
        return true;
    }

    @Override
    public boolean verificarCorrelativa(Dragon dragon) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean verificarCorrelativa(Scout scout) {
        // TODO Auto-generated method stub
        return false;
    }

}
