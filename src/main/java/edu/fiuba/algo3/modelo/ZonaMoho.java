package edu.fiuba.algo3.modelo;

public class ZonaMoho extends Zona{
    public ZonaMoho(Posicion centro)
    {
        this.centro = centro;
        this.radio = 5;
    }

    public boolean puedeHabitar(Construccion construccion) {
        return construccion.habita(this);
    }

    public void propagar()
    {
        this.radio += 1;
    }
}
