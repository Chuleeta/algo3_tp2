package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Edificios.Edificio;

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

    public EstadoConstruccion desarrollar(Edificio edificio, int tiempoDeConstruccion, int turnosParaConstruirse) throws NoExisteEdificioCorrelativoException{
        if(tiempoDeConstruccion == turnosParaConstruirse){
            edificio.construir();
            return (new EstadoConstruido());
        }
        return (this);
    }
}
