package edu.fiuba.algo3.modelo.Estados;

import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Edificios.Edificio;

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

    public EstadoConstruccion desarrollar(Edificio edificio, int tiempoDeConstruccion, int turnosParaConstruirse) throws NoExisteEdificioCorrelativoException{
        edificio.actualizar();
        return this;
    }

}
