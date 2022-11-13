package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MenaTest {
    // Caso 16
    @Test
    public void noSePuedoConstruirEnMenaSiYaExisteUnNexoMineral () throws MenaOcupadaException {
        Mena mena = new Mena(new Posicion(1,1));
        Mapa mapa = new Mapa();
        NexoMineral nexo = new NexoMineral(new Posicion(1, 1), mena, mapa);
        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(10000);
        mapa.agregarConstruccion(nexo, mineral, gas);
        assertThrows(MenaOcupadaException.class, ()->{ new NexoMineral(new Posicion(1, 1), mena, mapa); });
    }
    @Test
    public void noSePuedoConstruirNexoEnMenaSiYaHayUnZanganoMinando () throws MenaOcupadaException {
        Mena mena = new Mena(new Posicion(1,1));
        Mapa mapa = new Mapa();
        Zangano zangano = new Zangano();
        zangano.minarMena(mena);
        assertThrows(MenaOcupadaException.class, ()->{ new NexoMineral(new Posicion(1, 1), mena, mapa); });
    }

    @Test
    public void zanganoNoPuedeMinarSiHayNexoMineralEnMena () throws MenaOcupadaException {
        Mena mena = new Mena(new Posicion(1,1));
        Mapa mapa = new Mapa();
        Zangano zangano = new Zangano();
        NexoMineral nexo = new NexoMineral(new Posicion(1, 1), mena, mapa);
        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(10000);
        mapa.agregarConstruccion(nexo, mineral, gas);
        assertThrows(MenaOcupadaException.class, ()->{ zangano.minarMena(mena); });
    }

}
