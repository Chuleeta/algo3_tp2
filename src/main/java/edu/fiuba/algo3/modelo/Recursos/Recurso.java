package edu.fiuba.algo3.modelo.Recursos;

public abstract class Recurso{

    public int cantidad;

    public int getCantidad(){
        return cantidad;
    }

    public boolean invertir(int i) {
        if (this.cantidad >= i)
        {
            this.cantidad -= i;
            return true;
        }
        return false;
    }
}
