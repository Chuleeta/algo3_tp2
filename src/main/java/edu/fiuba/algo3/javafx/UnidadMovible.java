package edu.fiuba.algo3.javafx;
import edu.fiuba.algo3.modelo.Construccion;
import edu.fiuba.algo3.modelo.Jugador;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class UnidadMovible extends Rectangle implements Notificable {
    private Construccion construccion;

    public UnidadMovible(Construccion construccion, int coordenadaX, int coordenadaY) {
        super(20, 20, Color.GREEN);
        this.construccion = construccion;
        this.setTranslateX(coordenadaX);
        this.setTranslateY(coordenadaY);
    }
    @Override
    public void actualizar(Jugador jugadorDeTurno) {
        this.setDisable(jugadorDeTurno != this.construccion.mostrarJugador());
    }
}
