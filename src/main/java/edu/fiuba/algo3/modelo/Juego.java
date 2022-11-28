package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;

public class Juego {
    private Mapa mapa;

    public Juego(Mapa mapa) throws RequerimientosInsuficientesException{
        if(mapa == null) throw new RequerimientosInsuficientesException();
        this.mapa = mapa;
    }

    public boolean pasarTiempo() throws NoExisteEdificioCorrelativoException {
        this.mapa.pasarTiempo();
        
        return this.mapa.verificarConstruccionesVacias();
    }

}
