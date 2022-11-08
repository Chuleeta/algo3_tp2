package edu.fiuba.algo3.modelo;

public class Asimilador implements Construccion{

    private int gas;
    private Posicion posicion;
    private EstadoConstruccion estado;

    public Asimilador(Posicion posicion)
    { 
        this.gas = 0;
        this.posicion = posicion;
        this.estado = new EstadoNoConstruido();
    }

    public void construir() 
    {
        this.gas = 0;
    }

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

}
