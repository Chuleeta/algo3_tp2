package edu.fiuba.algo3.modelo;

public class Hidralisco {
    private int vida;
    private int unidadesDeDaño;
    public Hidralisco() {
        this.unidadesDeDaño = 10;
        this.vida = 80;
    }
    public void atacarEdificio(Edificio edificio) {
        edificio.dañar(this.unidadesDeDaño);
    }
}
