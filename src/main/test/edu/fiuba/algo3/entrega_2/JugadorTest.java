package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.Exceptions.AtributoInvalidoException;
import edu.fiuba.algo3.modelo.Recursos.Mena;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Posicion;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

public class JugadorTest {

    // Caso de uso 25
    
    @Test
    public void testSeCreanLosDosJugadoresCorrectamente() throws AtributoInvalidoException {
        Posicion posicionUno = new Posicion(1,1);
        Posicion posicionDos = new Posicion(100,100);
        Jugador jugadorUno = new Jugador("jugadorUno", "azul", "zerg", posicionUno);
        Jugador jugadorDos = new Jugador("jugadorDos", "rojo", "protoss", posicionDos);
        
        assertDoesNotThrow(()->jugadorUno.validarAtributos(null));
        assertDoesNotThrow(()->jugadorDos.validarAtributos(jugadorUno));
    }
    
    @Test
    public void testSeLanzaExcepcionPorElNombreDelJugadorDos() throws AtributoInvalidoException {
        Posicion posicionUno = new Posicion(1,1);
        Posicion posicionDos = new Posicion(100,100);
        Jugador jugadorUno = new Jugador("jugadorUno", "azul", "zerg", posicionUno);
        Jugador jugadorDos = new Jugador("jugadorUno", "rojo", "protoss", posicionDos);
        
        assertThrows(AtributoInvalidoException.class, () -> jugadorDos.validarAtributos(jugadorUno));
    }
    
    @Test
    public void testSeLanzaExcepcionPorElColorDelJugadorDos() throws AtributoInvalidoException {
        Posicion posicionUno = new Posicion(1,1);
        Posicion posicionDos = new Posicion(100,100);
        Jugador jugadorUno = new Jugador("jugadorUno", "azul", "zerg", posicionUno);
        Jugador jugadorDos = new Jugador("jugadorDos", "azul", "protoss", posicionDos);
        
        assertDoesNotThrow(()->jugadorUno.validarAtributos(null));
        assertThrows(AtributoInvalidoException.class, ()->jugadorDos.validarAtributos(jugadorUno));
    }
    
    @Test
    public void testSeLanzaExcepcionPorLaRazaDelJugadorDos() throws AtributoInvalidoException {
        Posicion posicionUno = new Posicion(1,1);
        Posicion posicionDos = new Posicion(100,100);
        Jugador jugadorUno = new Jugador("jugadorUno", "azul", "zerg", posicionUno);
        Jugador jugadorDos = new Jugador("jugadorDos", "rojo", "zerg", posicionDos);

        assertThrows(AtributoInvalidoException.class, () -> jugadorDos.validarAtributos(jugadorUno));
    }

    @Test
    public void testSeLanzaExcepcionPorLaPosicionDelJugadorDos() throws AtributoInvalidoException {
        Posicion posicionUno = new Posicion(1,1);
        Posicion posicionDos = new Posicion(99,99);
        Jugador jugadorUno = new Jugador("jugadorUno", "azul", "zerg", posicionUno);
        Jugador jugadorDos = new Jugador("jugadorDos", "rojo", "zerg", posicionDos);

        assertThrows(AtributoInvalidoException.class, () -> jugadorDos.validarAtributos(jugadorUno));
    }
    // @Test
    // public void testSeLanzaExcepcionAlCrearAlJugadorUno() throws AtributoInvalidoException {
    //     Jugador jugadorUno = new Jugador("juga", "azul", "zerg");

    //     assertThrows(AtributoInvalidoException.class, () -> jugadorUno.validarAtributos("juga"));
    // }




}
