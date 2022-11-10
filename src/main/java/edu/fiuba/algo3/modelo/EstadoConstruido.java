package edu.fiuba.algo3.modelo;

public class EstadoConstruido extends EstadoConstruccion {

    public EstadoConstruido() {

    }

    @Override
    public boolean puedeConstruirse(int tiempoDeConstruccion, int turnos) {
        return false;
    }

    public boolean estaConstruido(){
        return true;
    }

}
