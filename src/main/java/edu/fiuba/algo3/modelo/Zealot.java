package edu.fiuba.algo3.modelo;

public class Zealot {
    private VidaEscudoProtoss vida;
    private int unidadesDeDaño;
    public Zealot() {
        this.unidadesDeDaño = 8;
        this.vida = new VidaEscudoProtoss(100, 60);
    }
    public void atacarEdificio(Edificio edificio) {
        edificio.dañar(this.unidadesDeDaño);
    }
}
