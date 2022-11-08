package edu.fiuba.algo3.modelo;

public class Extractor implements Construccion{

    private Posicion posicion;
    private EstadoConstruccion estado;
    private int zanganos;
    private int gas;

    public Extractor(Posicion posicion)
    {
        this.posicion = posicion;
        this.estado = new EstadoNoConstruido();   
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

    public void actualizar()
    {
        this.gas += this.zanganos*10;
    }

    public void construir()
    {
        this.zanganos = 0;
        this.gas = 0;
        return;
    }




}
