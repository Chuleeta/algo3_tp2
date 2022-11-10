package edu.fiuba.algo3.modelo;

public class Acceso extends Edificio{

    private static int VIDA_COMPLETA = 500;

    public Acceso(Posicion posicion, Mapa mapa)
    {
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.mapa = mapa;
        tiempo = 0;
        this.zona = new ZonaEnergia(this.posicion);
    }

    public void pasarTiempo() {
        this.tiempo += 1;
        if (estado.puedeConstruirse(8, tiempo)) construir();
    }

    @Override
    public void construir()
    {
        estado = new EstadoConstruido();
        //mapa.agregarZona(zona);
    }

    @Override
    public boolean habita(Zona zona) {
        if(!this.zona.getClass().equals(zona.getClass())) return false;
        return zona.abarca(posicion);
    }

    public void dañar(int daño){
        vida -= daño;
    }

    public boolean tieneVidaCompleta(){
        return vida == VIDA_COMPLETA;
    }

}
