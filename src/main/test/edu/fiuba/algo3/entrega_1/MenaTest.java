package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Edificios.NexoMineral;
import edu.fiuba.algo3.modelo.Exceptions.MenaOcupadaException;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Individuos.Zangano;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mena;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MenaTest {
    // Caso 16
    @Test
    public void noSePuedoConstruirEnMenaSiYaExisteUnNexoMineral () throws MenaOcupadaException {
        Mena mena = new Mena(new Posicion(1,2));
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "protoss", new Posicion(1,1), mapa, 200);
        NexoMineral nexo = new NexoMineral(new Posicion(1, 2), mena, mapa);
        jugador.agregarConstruccion(nexo);
        assertThrows(MenaOcupadaException.class, ()->{ new NexoMineral(new Posicion(1, 2), mena, mapa); });
    }
    @Test
    public void noSePuedoConstruirNexoEnMenaSiYaHayUnZanganoMinando () throws MenaOcupadaException, RequerimientosInsuficientesException {
        Mena mena = new Mena(new Posicion(1,1));
        Mapa mapa = new Mapa();
        Zangano zangano = new Zangano(new Mineral(25));
        zangano.pasarTiempo();
        zangano.ocuparMena(mena);
        assertThrows(MenaOcupadaException.class, ()->{ new NexoMineral(new Posicion(1, 1), mena, mapa); });
    }

    @Test
    public void zanganoNoPuedeMinarSiHayNexoMineralEnMena () throws MenaOcupadaException, RequerimientosInsuficientesException {
        Mena mena = new Mena(new Posicion(1,2));
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "protoss", new Posicion(1,1), mapa, 200);
        Zangano zangano = new Zangano(new Mineral(25));
        zangano.pasarTiempo();
        NexoMineral nexo = new NexoMineral(new Posicion(1, 2), mena, mapa);
        jugador.agregarConstruccion(nexo);
        assertThrows(MenaOcupadaException.class, ()->{ zangano.ocuparMena(mena); });
    }

}
