package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VolcanTest {
    // Caso 16
    @Test
    public void noSePuedoConstruirEnVolcanSiYaExisteUnaEdificacionDeLaMismaRaza () throws VolcanOcupadoException {
        Volcan volcan = new Volcan(new Posicion(1,1));
        Mapa mapa = new Mapa();
        Asimilador asimiladorUno = new Asimilador(new Posicion(1, 1), volcan, mapa);
        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(10000);
        mapa.agregarConstruccion(asimiladorUno, mineral, gas);
        assertThrows(VolcanOcupadoException.class, ()->{ new Asimilador(new Posicion(1, 1), volcan, mapa); });
    }
    @Test
    public void noSePuedoConstruirEnVolcanSiYaExisteUnaEdificacionDeOtraRaza () throws VolcanOcupadoException {
        Volcan volcan = new Volcan(new Posicion(1,1));
        Mapa mapa = new Mapa();
        Asimilador asimiladorUno = new Asimilador(new Posicion(1, 1), volcan, mapa);
        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(10000);
        mapa.agregarConstruccion(asimiladorUno, mineral, gas);
        assertThrows(VolcanOcupadoException.class, ()->{ new Extractor(new Posicion(1, 1), volcan, mapa); });
    }

}
