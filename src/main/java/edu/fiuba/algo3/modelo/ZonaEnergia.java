package edu.fiuba.algo3.modelo;

public class ZonaEnergia extends Zona {

    public boolean puedeHabitar(Construccion construccion) {
        construccion.habita(this);
        return false;
    }

}
