package edu.fiuba.algo3.modelo;


import java.util.ArrayList;
import java.util.List;

public class Criadero extends Edificio {

    private List<Larva> larvas;
    private static int COSTO_DE_CONSTRUCCION = 50;
    private static int VIDA_COMPLETA = 500;

    public Criadero(Posicion posicion, Mapa mapa) {
        //validarMinerales(recursosDelJugador);
        this.posicion = posicion;
        this.estado = new EstadoNoConstruido();
        larvas = new ArrayList<>();
        this.mapa = mapa;
        zona = new ZonaNeutral();
        tiempo = 0;
        vida = 500;
        TURNOS_PARA_CONSTRUIRSE = 4;

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

    public void pasarTiempo() {
        tiempo += 1;
        regenerarVida();
        if (tiempo % 2 == 0 && tiempo > TURNOS_PARA_CONSTRUIRSE)
            zona.propagar();
        if (larvas.size() < 3)
            larvas.add(new Larva());
        if (estado.puedeConstruirse(TURNOS_PARA_CONSTRUIRSE, tiempo)) construir();
    }

    private void regenerarVida() {
        vida += 100;
        if(vida > VIDA_COMPLETA) vida = VIDA_COMPLETA;
    }

    @Override
    public boolean habita(Zona zona) {
        return this.zona.equals(zona);
    }

    public void dañar(int daño){
        vida -= daño;
    }

    public boolean tieneVidaCompleta(){
        return vida == VIDA_COMPLETA;
    }
}
