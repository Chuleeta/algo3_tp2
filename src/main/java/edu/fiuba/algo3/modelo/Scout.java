package edu.fiuba.algo3.modelo;

public class Scout {
    private final int unidadesDeDañoTierra;
    private final int unidadesDeDañoAire;
    private VidaEscudoProtoss vida;
    private final int tiempoDeConstruccion;
    private int tiempo;
    private EstadoConstruccion estado;
    public Scout(Mineral mineral, GasVespeno gas) throws RequerimientosInsuficientesException {
        if (!mineral.invertir(300) | !gas.invertir(150)) {
            throw new RequerimientosInsuficientesException();
        }
        this.unidadesDeDañoTierra = 8;
        this.unidadesDeDañoAire = 14;
        this.vida = new VidaEscudoProtoss(100, 80);
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 9;
        this.tiempo = 0;
    }

    private void construir() {
        this.estado = new EstadoConstruido();
    }
    public void pasarTiempo() {
        this.tiempo += 1;
        if (estado.puedeConstruirse(this.tiempoDeConstruccion, this.tiempo )) construir();
    }
    public void atacarEdificio(Edificio edificio) {
        if (this.estado.estaConstruido()) {
            edificio.dañar(this.unidadesDeDañoTierra);
        }
    }
}
