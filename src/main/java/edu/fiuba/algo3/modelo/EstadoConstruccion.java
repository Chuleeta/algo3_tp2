package edu.fiuba.algo3.modelo;

public abstract class EstadoConstruccion {

    public abstract boolean puedeConstruirse(int tiempoDeConstruccion, int turnosParaConstruirse);
    public abstract boolean estaConstruido();
    public abstract boolean estaActivado();
}
