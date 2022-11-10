package edu.fiuba.algo3.modelo;

public class VidaEscudoProtoss {

    private int escudo;
    private int escudoCompleto;
    private int vida;
    private int vidaCompleta;

    public VidaEscudoProtoss(int vida, int escudo) {
        this.vidaCompleta = vida;
        this.vida = vida;
        this.escudoCompleto = escudo;
        this.escudo = escudo;
    }
    public boolean tieneVidaCompleta(){
        return this.vida == this.vidaCompleta;
    }
    public boolean tieneEscudoCompleto(){
        return this.escudo == this.escudoCompleto;
    }
    public void dañarVida(int daño){
        this.vida -= daño;
    }
    public void dañarEscudo(int daño){
        if (this.escudo < daño) {
            int sobra = daño - this.escudo;
            this.escudo = 0;
            dañarVida(sobra);
        } else {
            this.escudo -= daño;
        }
    }
    public void repararEscudo() {

        this.escudo += 100;
        if (this.escudo > 250) {
            this.escudo = this.escudoCompleto;
        }
    }

    public void dañar(int daño) {
        dañarEscudo(daño);
    }
}
