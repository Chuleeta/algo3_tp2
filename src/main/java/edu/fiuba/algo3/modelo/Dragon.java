package edu.fiuba.algo3.modelo;

public class Dragon {
    private VidaEscudoProtoss vida;
    private int unidadesDeDaño;
    public Dragon() {
        this.unidadesDeDaño = 20;
        this.vida = new VidaEscudoProtoss(100, 80);
    }
    public void atacarEdificio(Edificio edificio) {
        edificio.dañar(this.unidadesDeDaño);
    }
}
