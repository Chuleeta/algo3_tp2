package edu.fiuba.algo3.modelo.Recursos;

import edu.fiuba.algo3.modelo.Colectable;

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
