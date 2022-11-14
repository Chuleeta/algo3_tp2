package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Edificios.Edificio;

public class Dragon {
    private VidaEscudoProtoss vida;
    private int unidadesDeDaño;
    private final int tiempoDeConstruccion;
    private int tiempo;
    private EstadoConstruccion estado;

    public Dragon(Mineral mineral, GasVespeno gas) throws RequerimientosInsuficientesException {
        if (!mineral.invertir(125) | !gas.invertir(50)) {
            throw new RequerimientosInsuficientesException();
        }
        this.unidadesDeDaño = 20;
        this.vida = new VidaEscudoProtoss(100, 80);
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 6;
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
            edificio.dañar(this.unidadesDeDaño);
        }
    }

}
