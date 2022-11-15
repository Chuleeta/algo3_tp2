package edu.fiuba.algo3.modelo.Zonas;

import edu.fiuba.algo3.modelo.Construccion;
import edu.fiuba.algo3.modelo.Posicion;

public class ZonaNeutral extends Zona {

    public ZonaNeutral()
    {
        this.centro = new Posicion(0, 0);
        this.radio = 5000;
    }

    @Override
    public boolean puedeHabitar(Construccion construccion) {
        return construccion.habita(this);
    }

}
