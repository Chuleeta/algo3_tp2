package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;

public class Juego {
    private Mapa mapa;
    private Jugador jugadorUno;
    private Jugador jugadorDos;

    private Canvas canvas;

    public Juego(Mapa mapa, Jugador jugadorUno, Jugador jugadorDos) throws RequerimientosInsuficientesException{
        if(mapa == null || jugadorUno == null || jugadorDos == null) throw new RequerimientosInsuficientesException();
        this.mapa = mapa;
        this.jugadorUno = jugadorUno;
        this.jugadorDos = jugadorDos;
    }

    public Juego(Mapa mapa, Jugador jugadorUno, Jugador jugadorDos, Canvas canvasDado) throws RequerimientosInsuficientesException{
        if(mapa == null || jugadorUno == null || jugadorDos == null) throw new RequerimientosInsuficientesException();
        this.mapa = mapa;
        this.jugadorUno = jugadorUno;
        this.jugadorDos = jugadorDos;

        this.canvas = canvasDado;
    }

    public boolean pasarTiempo() throws NoExisteEdificioCorrelativoException {
        this.jugadorUno.pasarTiempo();
        this.jugadorDos.pasarTiempo();
        
        return (this.jugadorDos.verificarConstruccionesVacias() && this.jugadorUno.verificarConstruccionesVacias());
    }

    // public Group getMapa() {

    //     return null;
    // }

}
