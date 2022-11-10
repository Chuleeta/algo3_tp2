package edu.fiuba.algo3.modelo;

public class EstadoDesactivado extends EstadoConstruccion {
    @Override
    public boolean puedeConstruirse(int tiempoDeConstruccion, int turnos) {
        return false;
    }

    public boolean estaConstruido(){
        return false;
    }

    public boolean estaActivado(){
        return false;
    }
}
