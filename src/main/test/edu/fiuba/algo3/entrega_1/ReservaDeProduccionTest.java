package edu.fiuba.algo3.entrega_1;

import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.ReservaDeProduccion;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservaDeProduccionTest {
    //caso de uso 10
    @Test
    public void seRegeneraTodaLaVidaDespuesDeAlgunosTurnos(){
        //given
        ReservaDeProduccion reserva = new ReservaDeProduccion(new Posicion(1,1), new Mapa());
        reserva.pasarTiempo();
        reserva.pasarTiempo();
        reserva.pasarTiempo();
        reserva.pasarTiempo();

        //when
        reserva.dañar(200);
        reserva.pasarTiempo();
        reserva.pasarTiempo();

        //then
        assertTrue(reserva.tieneVidaCompleta());
    }

    @Test
    public void seRegeneraLaVidaParcialmenteDespuesDeUnTurno(){
        //given
        ReservaDeProduccion reserva = new ReservaDeProduccion(new Posicion(1,1), new Mapa());
        reserva.pasarTiempo();
        reserva.pasarTiempo();
        reserva.pasarTiempo();
        reserva.pasarTiempo();

        //when
        reserva.dañar(200);
        reserva.pasarTiempo();

        //then
        assertFalse(reserva.tieneVidaCompleta());
    }
}
