package edu.fiuba.algo3.modelo;

public abstract class Recurso implements Colectable{

    public int cantidad;

    public int getCantidad(){
        return cantidad;
    }

    @Override
    public void colectar() {

    }
}
