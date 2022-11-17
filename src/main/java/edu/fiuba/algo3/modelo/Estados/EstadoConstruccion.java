package edu.fiuba.algo3.modelo.Estados;

import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Edificios.Edificio;

public abstract class EstadoConstruccion {

    public abstract boolean puedeConstruirse(int tiempoDeConstruccion, int turnosParaConstruirse);
    public abstract boolean estaConstruido();
    public abstract EstadoConstruccion desarrollar(Edificio edificio, int tiempoDeConstruccion, int turnosParaConstruirse) throws NoExisteEdificioCorrelativoException;
}
