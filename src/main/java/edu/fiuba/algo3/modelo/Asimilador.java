package edu.fiuba.algo3.modelo;

public class Asimilador implements Construccion{

    private int gas;
    private Posicion posicion;
    private EstadoConstruccion estado;
    private Mapa mapa;

    public Asimilador(Posicion posicion, Mapa mapa)
    { 
        this.gas = 0;
        this.posicion = posicion;
        this.estado = new EstadoNoConstruido();
        this.mapa = mapa;
    }

    @Override
    public void construir() 
    {
        this.gas = 0;
    }

    @Override
    public void actualizar() 
    {
        this.gas += 20;
    }

    public void pasarTiempo() 
    {
        this.estado.pasarTiempo(this);
        this.estado = this.estado.construir(5);
    }

    public Integer obtenerGas() 
    {
        return this.gas;
    }

    @Override
    public boolean habita(ZonaMoho zona) {
        return false;
    }

    @Override
    public boolean habita(ZonaEnergia zona) {
        return false;
    }

    @Override
    public boolean habita(ZonaNeutral zona) {
        // TODO Auto-generated method stub
        return false;
    }

}
