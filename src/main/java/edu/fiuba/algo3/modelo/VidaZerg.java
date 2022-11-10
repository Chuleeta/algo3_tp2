package edu.fiuba.algo3.modelo;

public class VidaZerg {
    private int vida;
    private int vidaCompleta;

    public VidaZerg(int vida) {
        this.vidaCompleta = vida;
        this.vida = vida;
    }

    public boolean tieneVidaCompleta(){
        return this.vida == this.vidaCompleta;
    }

    public void regenerarVida() {
        this.vida += 100;
        if (this.vida > this.vidaCompleta) {
            this.vida = this.vidaCompleta;
        }
    }

    public void dañar(int daño) {
        this.vida -= daño;
    }
}
