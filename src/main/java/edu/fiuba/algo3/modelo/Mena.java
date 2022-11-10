package edu.fiuba.algo3.modelo;

public class Mena {
    private Posicion posicion;
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

    public int minarMena()
    {
        if (this.minerales >= 50)
        {
            this.minerales -= 50; //Estipulado
            return 50;
        }
        return 0;    
    }

    public void ocupar() throws MenaOcupadaException {
        if (this.estaOcupado) {
            throw new MenaOcupadaException();
        }
        this.estaOcupado = true;
    }
}
