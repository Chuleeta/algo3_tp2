package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.Exceptions.AtributoInvalidoException;
import edu.fiuba.algo3.modelo.Recursos.Mena;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mapa;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JugadorTest {

    // Caso de uso 25
    
    @Test
    public void testSeCreanLosDosJugadoresCorrectamente() throws AtributoInvalidoException {
        Jugador jugadorUno = new Jugador("jugadorUno", "azul", "zerg", null);
        Jugador jugadorDos = new Jugador("jugadorDos", "rojo", "protoss", jugadorUno);
        
        assertDoesNotThrow(()->jugadorUno.validarAtributos(null));
        assertDoesNotThrow(()->jugadorDos.validarAtributos(jugadorUno));
    }
    
    @Test
    public void testSeLanzaExcepcionPorElNombreDelJugadorDos() throws AtributoInvalidoException {
        Jugador jugadorUno = new Jugador("jugadorUno", "azul", "zerg", null);
        Jugador jugadorDos = new Jugador("jugadorUno", "rojo", "protoss", jugadorUno);
        
        assertThrows(AtributoInvalidoException.class, () -> jugadorDos.validarAtributos(jugadorUno));
    }
    
    @Test
    public void testSeLanzaExcepcionPorElColorDelJugadorDos() throws AtributoInvalidoException {
        Jugador jugadorUno = new Jugador("jugadorUno", "azul", "zerg", null);
        Jugador jugadorDos = new Jugador("jugadorDos", "azul", "protoss", jugadorUno);
        
        assertDoesNotThrow(()->jugadorUno.validarAtributos(null));
        assertThrows(AtributoInvalidoException.class, ()->jugadorDos.validarAtributos(jugadorUno));
    }
    
    @Test
    public void testSeLanzaExcepcionPorLaRazaDelJugadorDos() throws AtributoInvalidoException {
        Jugador jugadorUno = new Jugador("jugadorUno", "azul", "zerg", null);
        Jugador jugadorDos = new Jugador("jugadorDos", "rojo", "zerg", jugadorUno);

        assertThrows(AtributoInvalidoException.class, () -> jugadorDos.validarAtributos(jugadorUno));
    }
    // @Test
    // public void testSeLanzaExcepcionAlCrearAlJugadorUno() throws AtributoInvalidoException {
    //     Jugador jugadorUno = new Jugador("juga", "azul", "zerg");

    //     assertThrows(AtributoInvalidoException.class, () -> jugadorUno.validarAtributos("juga"));
    // }




}
