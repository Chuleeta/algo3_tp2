package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Edificios.Asimilador;
import edu.fiuba.algo3.modelo.Edificios.Extractor;
import edu.fiuba.algo3.modelo.Exceptions.VolcanOcupadoException;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import edu.fiuba.algo3.modelo.Recursos.Volcan;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VolcanTest {
    // Caso 16
    @Test
    public void noSePuedoConstruirEnVolcanSiYaExisteUnaEdificacionDeLaMismaRaza () throws VolcanOcupadoException {
        Volcan volcan = new Volcan(new Posicion(1,2));
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "protoss", new Posicion(1,1), mapa, 200);
        Asimilador asimiladorUno = new Asimilador(new Posicion(1, 2), volcan, mapa, jugador);
        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(10000);
        jugador.agregarConstruccion(asimiladorUno, mineral, gas);
        assertThrows(VolcanOcupadoException.class, ()->{ new Asimilador(new Posicion(1, 2), volcan, mapa); });
    }
    @Test
    public void noSePuedoConstruirEnVolcanSiYaExisteUnaEdificacionDeOtraRaza () throws VolcanOcupadoException {
        Volcan volcan = new Volcan(new Posicion(1,2));
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "protoss", new Posicion(1,1), mapa, 200);
        Asimilador asimiladorUno = new Asimilador(new Posicion(1, 2), volcan, mapa, jugador);
        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(10000);
        jugador.agregarConstruccion(asimiladorUno, mineral, gas);
        assertThrows(VolcanOcupadoException.class, ()->{ new Extractor(new Posicion(1, 2), volcan, mapa); });
    }

}
