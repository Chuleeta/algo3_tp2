package edu.fiuba.algo3.modelo;

public class NexoMineral extends Edificio{

    private int minerales;
    private Mena mena;
    private static int VIDA_COMPLETA = 500;

    public NexoMineral(Posicion posicion, Mena mena, Mapa mapa)
    {
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.minerales = 0;
        this.mena = mena;
        this.mapa = mapa;
        TURNOS_PARA_CONSTRUIRSE = 4;
        vida = 500;
    }

    public void pasarTiempo() 
    {
        tiempo += 1;

    }

    @Override
    public void construir()
    {
        this.minerales = 0;
        return;
    }

    public int recogerMineral()
    {
        return this.minerales;
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
