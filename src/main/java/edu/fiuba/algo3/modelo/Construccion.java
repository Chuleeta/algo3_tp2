package edu.fiuba.algo3.modelo;

public interface Construccion {
    void construir() throws NoExisteEdificioCorrelativoException;
    //void actualizar();
    boolean habita(ZonaNeutral zona);
    boolean habita(ZonaMoho zona);
    boolean habita(ZonaEnergia zona);
    void pasarTiempo() throws NoExisteEdificioCorrelativoException;
    boolean agregarAlMapa(Mineral mineral, GasVespeno gas);
    void desactivar();
    //boolean estaActivado();
}
