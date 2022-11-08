package edu.fiuba.algo3.modelo;

public class Extractor implements Construccion, HabitanteMoho {

    private Posicion posicion;
    private EstadoConstruccion estado;
    private int zanganos;
    private int gas;
    private Mapa mapa;

    public Extractor(Posicion posicion, Mapa mapa)
    {
        this.posicion = posicion;
        this.estado = new EstadoNoConstruido();
        this.mapa = mapa;
    }

    public void pasarTiempo() 
    {
        this.estado.pasarTiempo(this);
        this.estado = this.estado.construir(5);
    }

    public int obtenerGas() 
    {
        return this.gas;
    }

    public void agregarZangano() 
    {
        if(this.zanganos < 3)
            this.zanganos += 1;
    }

    @Override
    public void actualizar()
    {
        this.gas += this.zanganos*10;
    }

    @Override
    public void construir()
    {
        this.zanganos = 0;
        this.gas = 0;
        return;
    }

    @Override
    public boolean habita(ZonaMoho zona) {
        return zona.abarca(this.posicion);
    }

    @Override
    public boolean habita(ZonaEnergia zona) {
        return false;
    }

    @Override
    public boolean habita(ZonaNeutral zona) {
        return false;
    }




}
