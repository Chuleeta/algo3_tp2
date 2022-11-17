package edu.fiuba.algo3.modelo;

public class VidaEscudoProtoss extends Vida{

    private int escudo;
    private int escudoCompleto;

    public VidaEscudoProtoss(int vida, int escudo) {
        this.vidaCompleta = vida;
        this.vida = vida;
        this.escudoCompleto = escudo;
        this.escudo = escudo;
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
    public void regenerar() {
        this.escudo += 100;
        if (this.escudo > this.vidaCompleta) {
            this.escudo = this.escudoCompleto;
        }
    }

    public void dañar(int daño) {
        dañarEscudo(daño);
    }

    

}
