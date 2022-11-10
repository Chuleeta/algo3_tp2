package edu.fiuba.algo3.modelo;

public class GasVespeno extends Recurso{

    public GasVespeno(int cantidad) {
        this.cantidad = cantidad;
    }

    public void colectarGas(Volcan volcan) {
        this.cantidad += volcan.colectarGas();
    }
}
