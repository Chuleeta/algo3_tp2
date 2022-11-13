package edu.fiuba.algo3.modelo;

public class Mutalisco {

    private final int unidadesDeDaño;
    private final int vida;

    public Mutalisco() {
        this.vida = 120;
        this.unidadesDeDaño = 9;
    }
    public void atacarEdificio(Edificio edificio) {
        edificio.dañar(this.unidadesDeDaño);
    }
}
