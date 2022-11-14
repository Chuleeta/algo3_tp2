package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Edificios.Edificio;

public class Hidralisco {
    private final int tiempoDeConstruccion;
    private int tiempo;
    private int vida;
    private int unidadesDeDaño;
    private EstadoConstruccion estado;

    public Hidralisco(Mineral mineral, GasVespeno gas) throws RequerimientosInsuficientesException {
        if (!mineral.invertir(75) | !gas.invertir(25)) {
            throw new RequerimientosInsuficientesException();
        }
        this.unidadesDeDaño = 10;
        this.vida = 80;
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 4;
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
