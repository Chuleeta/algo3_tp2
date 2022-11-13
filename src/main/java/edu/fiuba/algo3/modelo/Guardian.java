package edu.fiuba.algo3.modelo;

public class Guardian {
    private int vida;
    private int unidadesDeDaño;
    private int rangoDeAtaque;

    public Guardian() {
        this.vida = 100;
        this.unidadesDeDaño = 25;
        this.rangoDeAtaque = 10;
    }

    public void atacarEdificio(Edificio edificio) {
        edificio.dañar(this.unidadesDeDaño);
    }

}
