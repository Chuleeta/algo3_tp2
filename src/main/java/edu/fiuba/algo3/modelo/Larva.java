package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Individuos.Zangano;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

public class Larva {

    public Zangano mutar(Mineral mineral) throws RequerimientosInsuficientesException {
        return new Zangano(mineral);
    }
}
