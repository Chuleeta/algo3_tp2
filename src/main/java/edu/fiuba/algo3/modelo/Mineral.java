package edu.fiuba.algo3.modelo;

public class Mineral extends Recurso{

    public Mineral(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public void colectar(int cantidad) {
        this.cantidad += cantidad;
    }

    public void minarMena(Mena mena){
        cantidad += mena.minarMena();
    }
}
