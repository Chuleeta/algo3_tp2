package edu.fiuba.algo3.modelo;

public class Acceso extends Edificio{

    private static int VIDA_COMPLETA = 500;

    public Acceso(Posicion posicion, Mapa mapa)
    {
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.mapa = mapa;
        tiempo = 0;
        TURNOS_PARA_CONSTRUIRSE = 8;
    }

    public void pasarTiempo() {

    }

    @Override
    public void construir()
    {
    }

    @Override
    public boolean habita(Zona zona) {
        if(!this.zona.equals(zona)) return false;
        return zona.abarca(posicion);
    }

    public void dañar(int daño){
        vida -= daño;
    }

    public boolean tieneVidaCompleta(){
        return vida == VIDA_COMPLETA;
    }

}
