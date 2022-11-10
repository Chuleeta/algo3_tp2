package edu.fiuba.algo3.entrega_1;

import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Espiral;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Posicion;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EspiralTest {
    //caso de uso 10
    @Test
    public void seRegeneraTodaLaVidaDespuesDeAlgunosTurnos(){
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
    public void seRegeneraLaVidaParcialmenteDespuesDeUnTurno(){
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
}
