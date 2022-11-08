package edu.fiuba.algo3.modelo;

public abstract class EstadoConstruccion {

    public abstract EstadoConstruccion construir(int limit);
    public abstract void pasarTiempo(Construccion construccion);
    public abstract boolean estaConstruido();
}
