package edu.fiuba.algo3.modelo;

public abstract class Recurso implements Colectable{

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
    
    @Override
    public void colectar(int cantidad) {

    }
}
