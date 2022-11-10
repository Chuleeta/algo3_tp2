package edu.fiuba.algo3.modelo;

public class Volcan {
    private Posicion posicion;
    private int gas;

    private boolean estaOcupado;



    public Volcan(Posicion posicion)
    {
        this.posicion = posicion;
        this.gas = 5000;
        this.estaOcupado = false;
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

    public void ocupar() throws VolcanOcupadoException {
        if (this.estaOcupado) {
            throw new VolcanOcupadoException();
        }
        this.estaOcupado = true;
    }
}
