package edu.fiuba.algo3.modelo;

public class Volcan {
    private Posicion posicion;
    private int gas;

    public Volcan(Posicion posicion)
    {
        this.posicion = posicion;
        this.gas = 5000;
    }

    public int colectarGas()
    {
        if (this.gas >= 10)
        {
            this.gas -= 10; //Estipulado
            return 10;
        }
        return 0;    
    }
}
