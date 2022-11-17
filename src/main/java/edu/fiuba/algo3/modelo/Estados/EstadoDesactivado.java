package edu.fiuba.algo3.modelo.Estados;

import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Edificios.Edificio;

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

    @Override
    public EstadoConstruccion desarrollar(Edificio edificio, int tiempoDeConstruccion, int turnosParaConstruirse)
            throws NoExisteEdificioCorrelativoException {
        return this;
    }
}
