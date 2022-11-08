package edu.fiuba.algo3.modelo;

public class Acceso implements Construccion{
    private Posicion posicion;
    private EstadoConstruccion estado;
    private Mapa mapa;
    private int tiempo;

    public Acceso(Posicion posicion, Mapa mapa)
    {
        this.posicion = posicion;
        this.estado = new EstadoNoConstruido();
        this.mapa = mapa;
        this.tiempo = 0;
    }

    public void pasarTiempo() 
    {
        this.estado.pasarTiempo(this);
        this.estado = this.estado.construir(7);
    }

    @Override
    public void actualizar()
    {
        this.tiempo += 1;
    }

    @Override
    public void construir()
    {
        this.tiempo = 0;
    }

    @Override
    public boolean habita(ZonaMoho zona) {
        return false;
    }

    @Override
    public boolean habita(ZonaEnergia zona) {
        return zona.abarca(this.posicion);
    }

    @Override
    public boolean habita(ZonaNeutral zona) {
        return false;
    }
}
