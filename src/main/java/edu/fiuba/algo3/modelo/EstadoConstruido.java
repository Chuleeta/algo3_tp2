package edu.fiuba.algo3.modelo;

public class EstadoConstruido extends EstadoConstruccion {

    private int tiempo;

    public EstadoConstruido() {
        this.tiempo = 0;
    }

    public EstadoConstruido construir() {
        return this;
    }

    public int pasarTiempo(int larvas) {
        if ((this.tiempo == 0) || (larvas == 3)) {
            return 3;
        }

        this.tiempo += 1;
        return 1;
    }

}
