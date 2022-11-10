package edu.fiuba.algo3.entrega_1;

import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Guarida;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Posicion;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GuaridaTest {
    //caso de uso 10
    @Test
    public void seRegeneraTodaLaVidaDespuesDeAlgunosTurnos(){
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
    public void seRegeneraLaVidaParcialmenteDespuesDeUnTurno(){
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
}
