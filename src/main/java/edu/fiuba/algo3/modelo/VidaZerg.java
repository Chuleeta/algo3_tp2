package edu.fiuba.algo3.modelo;

public class VidaZerg extends Vida{

    public VidaZerg(int vida) {
        this.vidaCompleta = vida;
        this.vida = vida;
    }

    public void regenerar() {
        this.vida += 100;
        if (this.vida > this.vidaCompleta) {
            this.vida = this.vidaCompleta;
        }
    }

    @Override
    public boolean tieneEscudoCompleto() {
        return false;
    }


    public void dañar(int daño) {
        this.vida -= daño;
        System.out.print("Daño: " + daño + "Vida Restate:" + this.vida);
    }
}
