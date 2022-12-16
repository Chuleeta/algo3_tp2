package edu.fiuba.algo3.modelo.Zonas;

import edu.fiuba.algo3.modelo.Construccion;
import edu.fiuba.algo3.modelo.Posicion;

public class ZonaVigilada extends Zona{

    public ZonaVigilada(Posicion centro){
        this.centro = centro;
        radio = 4;
    }

    @Override
    public boolean puedeHabitar(Construccion construccion) {
        return false;
    }

    @Override
    public boolean puedeAtacar(Posicion posicion) {
        return posicion.adentro(radio, centro);
    }
}
