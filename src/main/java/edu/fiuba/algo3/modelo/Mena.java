package edu.fiuba.algo3.modelo;

public class Mena {
    private Posicion posicion;
    private EstadoConstruccion estado;
    private int minerales;
    private Mena mena;

    public Mena(Posicion posicion)
    {
        this.posicion = posicion;
        this.minerales = 2000;
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
}
