package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.Exceptions.AtributoInvalidoException;
import edu.fiuba.algo3.modelo.Jugador;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JugadorTest {

    @Test
    public void testSeCreanLosDosJugadoresCorrectamente() throws AtributoInvalidoException {
        Jugador jugadorUno = new Jugador("jugadorUno", "azul", "zerg");
        Jugador jugadorDos = new Jugador("jugadorDos", "rojo", "protoss");
        /*App app = new App();
        app.start(new Stage());*/

        assertDoesNotThrow(()->jugadorUno.validarAtributos("jugadorUno"));
        assertDoesNotThrow(()->jugadorDos.validarAtributos("jugadorDos", "rojo", "protoss", jugadorUno));
    }

    @Test
    public void testSeLanzaExcepcionAlCrearAlJugadorUno() throws AtributoInvalidoException {
        Jugador jugadorUno = new Jugador("juga", "azul", "zerg");
        /*App app = new App();
        app.start(new Stage());*/

        assertThrows(AtributoInvalidoException.class, () -> jugadorUno.validarAtributos("juga"));
    }

    @Test
    public void testSeLanzaExcepcionPorElNombreDelJugadorDos() throws AtributoInvalidoException {
        Jugador jugadorUno = new Jugador("jugadorUno", "azul", "zerg");
        Jugador jugadorDos = new Jugador("jugadorUno", "rojo", "protoss");
        /*App app = new App();
        app.start(new Stage());*/

        assertThrows(AtributoInvalidoException.class, () -> jugadorDos.validarAtributos("jugadorUno", "rojo", "protoss", jugadorUno));
    }

    @Test
    public void testSeLanzaExcepcionPorElColorDelJugadorDos() throws AtributoInvalidoException {
        Jugador jugadorUno = new Jugador("jugadorUno", "azul", "zerg");
        Jugador jugadorDos = new Jugador("jugadorDos", "azul", "protoss");
        /*App app = new App();
        app.start(new Stage());*/

        assertThrows(AtributoInvalidoException.class, () -> jugadorDos.validarAtributos("jugadorUno", "azul", "protoss", jugadorUno));
    }

    @Test
    public void testSeLanzaExcepcionPorLaRazaDelJugadorDos() throws AtributoInvalidoException {
        Jugador jugadorUno = new Jugador("jugadorUno", "azul", "zerg");
        Jugador jugadorDos = new Jugador("jugadorDos", "rojo", "zerg");
        /*App app = new App();
        app.start(new Stage());*/

        assertThrows(AtributoInvalidoException.class, () -> jugadorDos.validarAtributos("jugadorUno", "rojo", "zerg", jugadorUno));
    }

}
