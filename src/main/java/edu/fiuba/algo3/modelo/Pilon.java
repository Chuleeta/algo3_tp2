package edu.fiuba.algo3.modelo;

public class Pilon extends Edificio{

    private static int VIDA_COMPLETA = 100;

    public Pilon (Posicion posicion, Mapa mapa) {
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.mapa = mapa;
        tiempo = 0;
        TURNOS_PARA_CONSTRUIRSE = 5;
    }

    public void pasarTiempo() {

    }

    @Override
    public void construir()
    {

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
