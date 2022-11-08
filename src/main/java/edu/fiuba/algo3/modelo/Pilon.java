package edu.fiuba.algo3.modelo;

public class Pilon implements Construccion{
    private Posicion posicion;
    private EstadoConstruccion estado;
    private Mapa mapa;
    private ZonaEnergia zona;
    private int tiempo;

    public Pilon (Posicion posicion, Mapa mapa) {
        this.posicion = posicion;
        this.estado = new EstadoNoConstruido();
        this.mapa = mapa;
        this.tiempo = 0;
    }

    public void pasarTiempo() {
        this.estado.pasarTiempo(this);
        this.estado = this.estado.construir(4);
    }

    @Override
    public void construir()
    {
        this.zona = new ZonaEnergia(this.posicion);
        this.mapa.agregarZona(this.zona);
    }

    @Override
    public void actualizar()
    {
        this.tiempo += 1;
    }

    @Override
    public boolean habita(ZonaMoho zona) {
        return true;
    }

    @Override
    public boolean habita(ZonaEnergia zona) {
        return true;
    }

    @Override
    public boolean habita(ZonaNeutral zona) {
        return true;
    }
}
