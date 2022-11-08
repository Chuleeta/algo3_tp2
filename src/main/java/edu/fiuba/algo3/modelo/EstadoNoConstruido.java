package edu.fiuba.algo3.modelo;

public class EstadoNoConstruido extends EstadoConstruccion {

    private int tiempo;

    public EstadoNoConstruido() {
        this.tiempo = 0;
    }

    public EstadoConstruccion construir() {
        if (this.tiempo >= 3) {
            return new EstadoConstruido();
        }
        return this;
    }

    public int pasarTiempo(int larvas) {
        this.tiempo += 1;
        return larvas;
    }

}
