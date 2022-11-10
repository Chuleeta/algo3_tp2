package edu.fiuba.algo3.modelo;

public class EstadoNoConstruido extends EstadoConstruccion {

    public EstadoNoConstruido() 
    {

    }

    /*public EstadoConstruccion construir(int limit)
    {
        if (this.tiempo >= limit) {
            return new EstadoConstruido();
        }
        return this;
    }*/

    public boolean puedeConstruirse(int tiempoDeConstruccion, int turnosInicializado) {
        return tiempoDeConstruccion == turnosInicializado;
    }

    /*public void pasarTiempo(Construccion construccion)
    {
        this.tiempo += 1;
        return;
    }*/

    /*public boolean estaConstruido()
    {
        return false;
    }*/

}
