package edu.fiuba.algo3.modelo.Edificios;

import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.Individuos.Dragon;
import edu.fiuba.algo3.modelo.Individuos.Hidralisco;
import edu.fiuba.algo3.modelo.Individuos.Mutalisco;
import edu.fiuba.algo3.modelo.Individuos.Scout;
import edu.fiuba.algo3.modelo.Individuos.Zangano;
import edu.fiuba.algo3.modelo.Individuos.Zealot;
import edu.fiuba.algo3.modelo.Individuos.Zerling;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.VidaEscudoProtoss;
import edu.fiuba.algo3.modelo.Zonas.ZonaEnergia;
import edu.fiuba.algo3.modelo.Zonas.ZonaMoho;
import edu.fiuba.algo3.modelo.Zonas.ZonaNeutral;

public class Pilon extends Edificio{

    public Pilon (Posicion posicion, Mapa mapa) {
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.mapa = mapa;
        tiempo = 0;
        this.zona = mapa.getZonaNeutral();
        this.vida = new VidaEscudoProtoss(300, 300);
        // if(this.jugador == null){
        //     crearJugadorPorDefecto();
        // }
    }

    public Pilon(Posicion posicion, Jugador jugador) throws RecursosInsuficientesException {
        this(posicion, jugador.getMapa());
        this.jugador = jugador;
        if(!this.jugador.agregarConstruccion(this) || !this.mapa.agregarOcupable(this, posicion)){
            throw new RecursosInsuficientesException();
        }
    }

    public void pasarTiempo() throws NoExisteEdificioCorrelativoException {
        tiempo += 1;
        this.estado = this.estado.desarrollar(this, 5, tiempo);
    }


    @Override
    public void construir()
    {
        this.zona = new ZonaEnergia(this.posicion);
        mapa.agregarZona(this.zona);
        this.estado = new EstadoConstruido();
        jugador.incrementarCapacidadDePoblacion(5);
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

    public boolean tieneVidaCompleta() {
        return this.vida.tieneVidaCompleta();
    }

    public boolean tieneEscudoCompleto() {
        return this.vida.tieneEscudoCompleto();
    }

    @Override
    public boolean agregarAlMapa(Mineral mineral, GasVespeno gas) {
        if(mineral.invertir(100))
        {
            this.jugador.agregarEnListaConstruccion(this);
            return true;
        }
        return false;
    }

    public void destruir()
    {
        this.jugador.decrementarCapacidadDePoblacion(5);
        this.mapa.destruirZona(this.zona);
        this.jugador.destruirConstruccion(this);
    }
    @Override
    public void actualizar() {
        this.vida.regenerar();
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
        return "/imagenes/pilon.png";
    }
}
