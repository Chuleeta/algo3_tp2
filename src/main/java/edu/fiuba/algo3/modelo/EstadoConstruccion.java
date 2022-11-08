package edu.fiuba.algo3.modelo;

public abstract class  EstadoConstruccion {
    private int tiempo;

    public abstract EstadoConstruccion construir();
    public abstract int pasarTiempo(int larvas);
}
