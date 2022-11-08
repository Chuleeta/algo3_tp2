package edu.fiuba.algo3.modelo;

public class Asimilador {

    private int gas;
    private Posicion posicion;

    public Asimilador(Posicion posicion){ 
        this.gas = 0;
        this.posicion = posicion;
    }

    public void pasarTiempo() {
        this.gas += 20;
    }

    public Integer obtenerGas() {
        return this.gas;
    }

}
