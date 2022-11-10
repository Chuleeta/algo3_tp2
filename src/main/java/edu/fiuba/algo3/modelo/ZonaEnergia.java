package edu.fiuba.algo3.modelo;

public class ZonaEnergia extends Zona {

    public ZonaEnergia(Posicion centro)
    {
        this.centro = centro;
        this.radio = 3;
    }
    
    public boolean puedeHabitar(Construccion construccion) {
        return construccion.habita(this);
    }

}
