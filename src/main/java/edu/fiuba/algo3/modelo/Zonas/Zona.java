package edu.fiuba.algo3.modelo.Zonas;

import edu.fiuba.algo3.modelo.Construccion;
import edu.fiuba.algo3.modelo.Posicion;

public abstract class Zona 
{   
    protected float radio;
    protected Posicion centro;
    public abstract boolean puedeHabitar(Construccion construccion);
    public boolean abarca(Posicion posicion)
    {
        return (posicion.adentro(this.radio, this.centro));
    }

    public void propagar(){

    }
}
