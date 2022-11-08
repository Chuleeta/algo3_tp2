package edu.fiuba.algo3.modelo;

public class EstadoNoConstruido extends EstadoConstruccion {

    private int tiempo;

    public EstadoNoConstruido() 
    {
        this.tiempo = 0;
    }

    public EstadoConstruccion construir(int limit) 
    {
        if (this.tiempo >= limit) {
            return new EstadoConstruido();
        }
        return this;
    }

    public void pasarTiempo(Construccion construccion) 
    {
        this.tiempo += 1;
        return;
    }

    public boolean estaConstruido()
    {
        return false;
    }

}
