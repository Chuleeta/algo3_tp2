package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Edificios.ReservaDeReproduccion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReservaDeReproduccionTest {
    //caso de uso 10
    @Test
    public void seRegeneraTodaLaVidaDespuesDeAlgunosTurnos() throws NoExisteEdificioCorrelativoException{
        //given
        ReservaDeReproduccion reserva = new ReservaDeReproduccion(new Posicion(1,1), new Mapa());
        reserva.pasarTiempo();
        reserva.pasarTiempo();
        reserva.pasarTiempo();
        reserva.pasarTiempo();
        reserva.pasarTiempo();
        reserva.pasarTiempo();
        reserva.pasarTiempo();
        reserva.pasarTiempo();
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
    public void seRegeneraLaVidaParcialmenteDespuesDeUnTurno() throws NoExisteEdificioCorrelativoException{
        //given
        ReservaDeReproduccion reserva = new ReservaDeReproduccion(new Posicion(1,1), new Mapa());
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
