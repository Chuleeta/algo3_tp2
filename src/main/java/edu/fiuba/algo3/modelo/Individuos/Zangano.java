package edu.fiuba.algo3.modelo.Individuos;

import edu.fiuba.algo3.modelo.Exceptions.MenaOcupadaException;
import edu.fiuba.algo3.modelo.Recursos.Mena;
import edu.fiuba.algo3.modelo.Recursos.Volcan;

public class Zangano {

    public Zangano() {
        
    }
    public void minarMena(Mena mena) throws MenaOcupadaException {
        mena.ocupar();
    }
    public int extraerGas(Volcan volcan) {
        return volcan.extraerGas(10);
    }

}
