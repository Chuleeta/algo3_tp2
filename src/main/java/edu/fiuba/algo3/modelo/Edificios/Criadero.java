package edu.fiuba.algo3.modelo.Edificios;


import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.CriaderoNoDisponibleException;
import edu.fiuba.algo3.modelo.EstadoConstruido;
import edu.fiuba.algo3.modelo.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.GasVespeno;
import edu.fiuba.algo3.modelo.Larva;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Mineral;
import edu.fiuba.algo3.modelo.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.VidaZerg;
import edu.fiuba.algo3.modelo.Zangano;
import edu.fiuba.algo3.modelo.Zona;
import edu.fiuba.algo3.modelo.ZonaEnergia;
import edu.fiuba.algo3.modelo.ZonaMoho;
import edu.fiuba.algo3.modelo.ZonaNeutral;

public class Criadero extends Edificio {

    private List<Larva> larvas;
    private static int COSTO_DE_CONSTRUCCION = 50;
    private static int VIDA_COMPLETA = 500;
    private VidaZerg vida;

    public Criadero(Posicion posicion, Mapa mapa) {
        //validarMinerales(recursosDelJugador);
        this.posicion = posicion;
        this.estado = new EstadoNoConstruido();
        larvas = new ArrayList<>();
        this.mapa = mapa;
        zona = mapa.getZonaNeutral();
        tiempo = 0;
        this.vida = new VidaZerg(VIDA_COMPLETA);
    }

    private void validarMinerales(int recursosDelJugador) throws RecursosInsuficientesException{
        if(recursosDelJugador < COSTO_DE_CONSTRUCCION) throw new RecursosInsuficientesException();
    }

    public int getCostoDeConstruccion(){
        return COSTO_DE_CONSTRUCCION;
    }

    private List<Larva> inicializarLarvas() {
        List<Larva> listaDeLarvas = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Larva unaLarva = new Larva();
            listaDeLarvas.add(unaLarva);
        }
        return listaDeLarvas;
    }

    public List<Larva> getLarvas() {
        return larvas;
    }

    public boolean llenoDeLarvas() {
        return larvas.size() == 3;
    }

    public Zangano engendrarZangano() throws CriaderoNoDisponibleException {
        if (larvas.size() == 0) {
            throw new CriaderoNoDisponibleException();
        }
        return larvas.remove(0).mutar();
    }

    @Override
    public void construir() {
        larvas = inicializarLarvas();
        estado = new EstadoConstruido();
        zona = new ZonaMoho(this.posicion);
        mapa.agregarZona(this.zona);
    }

    public void pasarTiempo() throws NoExisteEdificioCorrelativoException{
        tiempo += 1;
        this.estado = estado.desarrollar(this, 4, tiempo);
    }

    public void actualizar(){
        this.vida.regenerarVida();
        if (tiempo % 2 == 0)
        zona.propagar();
        if (larvas.size() < 3)
        larvas.add(new Larva());
    }

    @Override
    public boolean habita(ZonaNeutral zona) {
        return true;
    }

    @Override
    public boolean habita(ZonaMoho zona) {
        return true;
    }

    @Override
    public boolean habita(ZonaEnergia zona) {
        return true;
    }


    public void dañar(int daño){
        this.vida.dañar(daño);
    }

    public boolean tieneVidaCompleta(){
        return this.vida.tieneVidaCompleta();
    }

    @Override
    public boolean agregarAlMapa(Mineral mineral, GasVespeno gas) {
        if(mineral.invertir(50))
        {
            this.mapa.agregarEnListaConstruccion(this);
            return true;
        }
        return false;
    }
}
