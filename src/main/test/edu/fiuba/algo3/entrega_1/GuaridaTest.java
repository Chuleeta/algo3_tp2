package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GuaridaTest {
    //caso de uso 10
    @Test
    public void seRegeneraTodaLaVidaDespuesDeAlgunosTurnos() throws NoExisteEdificioCorrelativoException {
        //given
        Guarida guarida = new Guarida(new Posicion(1,1), new Mapa());
        guarida.pasarTiempo();
        guarida.pasarTiempo();
        guarida.pasarTiempo();
        guarida.pasarTiempo();

        //when
        guarida.dañar(200);
        guarida.pasarTiempo();
        guarida.pasarTiempo();

        //then
        assertTrue(guarida.tieneVidaCompleta());
    }

    @Test
    public void seRegeneraLaVidaParcialmenteDespuesDeUnTurno() throws NoExisteEdificioCorrelativoException {
        //given
        Guarida guarida = new Guarida(new Posicion(1,1), new Mapa());
        guarida.pasarTiempo();
        guarida.pasarTiempo();
        guarida.pasarTiempo();
        guarida.pasarTiempo();

        //when
        guarida.dañar(200);
        guarida.pasarTiempo();

        //then
        assertFalse(guarida.tieneVidaCompleta());
    }
    // caso de uso 17
    @Test
    public void noSePuedeConstruirGuaridaSiNoHayReservaDeReproduccion() throws NoExisteEdificioCorrelativoException {
        //given
        Guarida guarida = new Guarida(new Posicion(1,1), new Mapa());
        guarida.pasarTiempo();
        guarida.pasarTiempo();
        guarida.pasarTiempo();
        guarida.pasarTiempo();
        guarida.pasarTiempo();
        guarida.pasarTiempo();
        guarida.pasarTiempo();
        guarida.pasarTiempo();
        guarida.pasarTiempo();
        guarida.pasarTiempo();
        guarida.pasarTiempo();
        assertThrows(NoExisteEdificioCorrelativoException.class, () ->{ guarida.pasarTiempo();});
    }
}
