package edu.fiuba.algo3.modelo;

public class Pilon extends Edificio{

    private static int VIDA_COMPLETA = 100;

    public Pilon (Posicion posicion, Mapa mapa) {
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.mapa = mapa;
        tiempo = 0;
        this.zona = mapa.getZonaNeutral();
    }

    public void pasarTiempo() {
        tiempo += 1;
        if (estado.puedeConstruirse(5, tiempo)) construir();
    }

    @Override
    public void construir()
    {
        this.zona = new ZonaEnergia(this.posicion);
        mapa.agregarZona(this.zona);
        this.estado = new EstadoConstruido();
    }

    @Override
    public boolean habita(Zona zona) {
        return true;
    }

    public void dañar(int daño){
        vida -= daño;
    }

    public boolean tieneVidaCompleta(){
        return vida == VIDA_COMPLETA;
    }
}
