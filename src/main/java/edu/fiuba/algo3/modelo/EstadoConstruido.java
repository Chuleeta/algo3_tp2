package edu.fiuba.algo3.modelo;

public class EstadoConstruido extends EstadoConstruccion {

    private int tiempo;

    public EstadoConstruido() {
        this.tiempo = 0;
    }

    public EstadoConstruido construir(int limit) {
        return this;
    }


    public void pasarTiempo(Construccion construccion) {
        this.tiempo += 1;
        if (this.tiempo == 1)
        {
            construccion.construir();
            return;
        }
        construccion.actualizar();
    }

    public boolean estaConstruido()
    {
        return true;
    }

}
