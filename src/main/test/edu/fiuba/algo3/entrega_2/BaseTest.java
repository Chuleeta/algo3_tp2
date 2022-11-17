package edu.fiuba.algo3.entrega_2;

import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Base;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mapa;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BaseTest {

    //Caso de Uso 24
    @Test
    public void testSeCreaUnaBaseCercaDeUnJugadorYSeAgreganLosElementosDeEstaAlMapa(){
        Jugador jugador = new Jugador("jugador", "azul", "zerg", null);
        Mapa mapa = new Mapa();
        Base base = new Base(jugador);


        assertTrue(base.agregarAlMapaElementos(mapa));
    }

    @Test
    public void testSeCreaUnaBaseCercaDeCadaJugadorYSeAgreganLosElementosDeEstasAlMapa(){
        Jugador jugadorUno = new Jugador("jugadorUno", "azul", "zerg", null);
        Jugador jugadorDos = new Jugador("jugadorDos", "rojo", "protoss", jugadorUno);
        Mapa mapa = new Mapa();
        Base baseJugadorUno = new Base(jugadorUno);
        Base baseJugadorDos = new Base(jugadorDos);


        assertTrue(baseJugadorUno.agregarAlMapaElementos(mapa));
        assertTrue(baseJugadorDos.agregarAlMapaElementos(mapa));
    }
}
