package edu.fiuba.algo3.modelo.Edificios;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Exceptions.maximaPoblacionAlcanzadaException;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
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

public class ReservaDeReproduccion extends Edificio{
    private static int VIDA_COMPLETA = 1000;

    public ReservaDeReproduccion(Posicion posicion, Mapa mapa)
    {
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.mapa = mapa;
        zona = new ZonaMoho(this.posicion);
        tiempo = 0;
        this.vida = new VidaZerg(VIDA_COMPLETA);
        // if(this.jugador == null){
        //     crearJugadorPorDefecto();
        // }
    }

    public ReservaDeReproduccion(Posicion posicion, Jugador jugador) throws RecursosInsuficientesException {
        this(posicion, jugador.getMapa());
        this.jugador = jugador;
        if(!this.jugador.agregarConstruccion(this) || !this.mapa.agregarOcupable(this, posicion)){
            throw new RecursosInsuficientesException();
        }
    }

    public void pasarTiempo() throws NoExisteEdificioCorrelativoException
    {
        tiempo += 1;
        this.estado = this.estado.desarrollar(this, 12, tiempo);
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

    public boolean tieneVidaCompleta(){
        return this.vida.tieneVidaCompleta();
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
    public Zerling generarZerling(Mineral mineral, Larva larva) throws RequerimientosInsuficientesException {
            return new Zerling(mineral, this.posicion.clone(), this.mapa);
    }
    public Zerling generarZerling(Mineral mineral, Larva larva, Posicion posicion) throws RequerimientosInsuficientesException, maximaPoblacionAlcanzadaException {
        if (jugador.unidadesDisponibles()) {
            Zerling zerling = new Zerling(mineral, posicion, this.mapa);
            this.jugador.agregarIndividuo(zerling);
            jugador.añadirUnidad();
            return zerling;
        }
        throw new maximaPoblacionAlcanzadaException();
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
        return true;
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
        return true;
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
        return "/imagenes/reserva.png";
    }
}
