package edu.fiuba.algo3.modelo;

public class Mineral extends Recurso{

    public Mineral(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int getCantidad() {
        return this.cantidad;
    }

    public void agregarMineral(int cantidad){
        this.cantidad += cantidad;
    }

}
