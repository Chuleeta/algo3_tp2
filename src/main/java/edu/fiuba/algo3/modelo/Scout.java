package edu.fiuba.algo3.modelo;

public class Scout {
    private final int unidadesDeDañoTierra;
    private final int unidadesDeDañoAire;
    private VidaEscudoProtoss vida;
    public Scout() {
        this.unidadesDeDañoTierra = 8;
        this.unidadesDeDañoAire = 14;
        this.vida = new VidaEscudoProtoss(100, 80);
    }
    public void atacarEdificio(Edificio edificio) {
        edificio.dañar(this.unidadesDeDañoTierra);
    }
}
