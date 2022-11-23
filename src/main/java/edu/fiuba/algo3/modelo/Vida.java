package edu.fiuba.algo3.modelo;


public abstract class Vida {
    public int vida;
    protected int vidaCompleta;
    public boolean tieneVidaCompleta()
    {
        return this.vida == this.vidaCompleta;
    }
    public abstract void dañar(int daño);
    public abstract void regenerar();
    public abstract boolean tieneEscudoCompleto();
}
