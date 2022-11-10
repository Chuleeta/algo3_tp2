package edu.fiuba.algo3.modelo;

public class EstadoNoConstruido extends EstadoConstruccion {

    public EstadoNoConstruido() 
    {

    }

    public boolean puedeConstruirse(int tiempoDeConstruccion, int turnosInicializado) {
        return (tiempoDeConstruccion == turnosInicializado);
    }

    public boolean estaConstruido()
    {
        return false;
    }

    public boolean estaActivado(){
        return false;
    }

}
