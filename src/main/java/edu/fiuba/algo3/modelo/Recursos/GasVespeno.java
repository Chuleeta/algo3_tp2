package edu.fiuba.algo3.modelo.Recursos;

public class GasVespeno extends Recurso{

    public GasVespeno(int cantidad) {
        this.cantidad = cantidad;
    }

    public void agregarGas(int cantidadDada) {
        this.cantidad += cantidadDada;
    }
}
