package edu.fiuba.algo3.modelo;

public class GasVespeno extends Recurso{

    public GasVespeno(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public void colectar(int cantidad) {
        this.cantidad += cantidad;
    }
}
