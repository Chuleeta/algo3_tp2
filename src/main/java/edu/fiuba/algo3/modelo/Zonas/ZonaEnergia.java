package edu.fiuba.algo3.modelo.Zonas;

import edu.fiuba.algo3.modelo.Construccion;
import edu.fiuba.algo3.modelo.Posicion;

public class ZonaEnergia extends Zona {

    public ZonaEnergia(Posicion centro)
    {
        this.centro = centro;
        this.radio = 3;
    }
    
    public boolean puedeHabitar(Construccion construccion) {
        return construccion.habita(this);
    }

    @Override
    public boolean puedeAtacar(Posicion posicion) {
        return true;
    }

}
