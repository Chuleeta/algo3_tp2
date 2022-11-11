package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EspiralTest {
    //caso de uso 10
    @Test
    public void seRegeneraTodaLaVidaDespuesDeAlgunosTurnos() throws NoExisteEdificioCorrelativoException {
        //given
        Espiral espiral = new Espiral(new Posicion(1,1), new Mapa());
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();

        //when
        espiral.dañar(200);
        espiral.pasarTiempo();
        espiral.pasarTiempo();

        //then
        assertTrue(espiral.tieneVidaCompleta());
    }

    @Test
    public void seRegeneraLaVidaParcialmenteDespuesDeUnTurno() throws NoExisteEdificioCorrelativoException {
        //given
        Espiral espiral = new Espiral(new Posicion(1,1), new Mapa());
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();

        //when
        espiral.dañar(200);
        espiral.pasarTiempo();

        //then
        assertFalse(espiral.tieneVidaCompleta());
    }

    @Test
    public void noSePuedeConstruirEspiralSiNoHayGuarida() throws NoExisteEdificioCorrelativoException {
        //given
        Espiral espiral = new Espiral(new Posicion(1,1), new Mapa());
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        assertThrows(NoExisteEdificioCorrelativoException.class, () ->{ espiral.pasarTiempo();});
    }
}
