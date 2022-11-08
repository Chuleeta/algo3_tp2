package edu.fiuba.algo3.modelo;

public interface Construccion {
    void construir();
    void actualizar();
    boolean habita(ZonaMoho zona);
    boolean habita(ZonaEnergia zona);
    boolean habita(ZonaNeutral zona);
    void pasarTiempo();
}
