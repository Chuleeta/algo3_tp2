package edu.fiuba.algo3.modelo;

public interface Construccion {
    void construir();
    //void actualizar();
    boolean habita(Zona zona);
    void pasarTiempo();
    boolean agregarAlMapa(Mineral mineral, GasVespeno gas);
    void desactivar();
    boolean estaActivado();
}
