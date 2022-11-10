package edu.fiuba.algo3.modelo;

public class Acceso extends Edificio{

    private static int VIDA_COMPLETA = 500;
    private VidaEscudoProtoss vidaYEscudo;

    public Acceso(Posicion posicion, Mapa mapa)
    {
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.mapa = mapa;
        tiempo = 0;
        TURNOS_PARA_CONSTRUIRSE = 8;
        this.vidaYEscudo = new VidaEscudoProtoss(500, 500);
    }

    public void pasarTiempo() {
        this.vidaYEscudo.repararEscudo();
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
        this.vidaYEscudo.dañar(daño);
    }

    public boolean tieneVidaCompleta(){
        return this.vidaYEscudo.tieneVidaCompleta();
    }
    public boolean tieneEscudoCompleto(){
        return this.vidaYEscudo.tieneEscudoCompleto();
    }

}
