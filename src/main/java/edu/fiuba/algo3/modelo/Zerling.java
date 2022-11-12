package edu.fiuba.algo3.modelo;

public class Zerling {
    private int vida;
    private int unidadesDeDaño;

    public Zerling() {
        this.unidadesDeDaño = 4;
        this.vida = 35;
    }
    public void atacarEdificio(Edificio edificio) {
        edificio.dañar(this.unidadesDeDaño);
    }
}
