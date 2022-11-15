package edu.fiuba.algo3.modelo;

public class Mena {
    private Posicion posicion;
    // falta incorporar estado de construccion
    private EstadoConstruccion estado;
    private int minerales;
    private Mena mena;
    private boolean estaOcupado;

    public Mena(Posicion posicion)
    {
        this.posicion = posicion;
        this.minerales = 2000;
        this.estaOcupado = false;
    }

    public int extraerMineral(int cantidad)
    {
        if (this.minerales >= cantidad)
        {
            this.minerales -= cantidad; 
            return cantidad;
        }
        return this.minerales;   
    }

    public void ocupar() throws MenaOcupadaException {
        if (this.estaOcupado) {
            throw new MenaOcupadaException();
        }
        this.estaOcupado = true;
    }
}
