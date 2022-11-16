package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Edificios.ReservaDeReproduccion;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;

import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Individuos.Zerling;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
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

    // caso 22
    @Test
    public void seEngendraZerlingExitosamente() throws NoExisteEdificioCorrelativoException, RequerimientosInsuficientesException {
        //given
        ReservaDeReproduccion reserva = new ReservaDeReproduccion(new Posicion(1,1), new Mapa());

        for (int i = 0; i < 12; i++)
            reserva.pasarTiempo();

        Mineral mineral = new Mineral(25);
        Zerling zerling = reserva.generarZerling(mineral, new Larva());
        assertNotNull(zerling);
    }

}
