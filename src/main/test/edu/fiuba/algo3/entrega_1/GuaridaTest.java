package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Edificios.Criadero;
import edu.fiuba.algo3.modelo.Edificios.Guarida;
import edu.fiuba.algo3.modelo.Edificios.ReservaDeReproduccion;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Individuos.Hidralisco;
import edu.fiuba.algo3.modelo.Individuos.Zerling;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GuaridaTest {
    //caso de uso 10
    @Test
    public void seRegeneraTodaLaVidaDespuesDeAlgunosTurnos() throws NoExisteEdificioCorrelativoException {
        //given
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Criadero criadero = new Criadero(new Posicion(1,3), mapa);
        jugador.incrementarMineral(2000);
        jugador.incrementarGas(2000);
        jugador.agregarConstruccion(criadero);
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        ReservaDeReproduccion reserva = new ReservaDeReproduccion(new Posicion(1,2), jugador);
        jugador.agregarConstruccion(reserva);
        for(int i = 0; i < 13; i += 1){
            reserva.pasarTiempo();
        }
        Guarida guarida = new Guarida(new Posicion(1,1), jugador);
        jugador.agregarConstruccion(guarida);
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

    // caso 22
    @Test
    public void seEngendraHidraliscoExitosamente() throws NoExisteEdificioCorrelativoException, RequerimientosInsuficientesException {
        //given
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        jugador.incrementarGas(2000);
        jugador.incrementarMineral(2000);
        // es el edificio correlativo
        ReservaDeReproduccion reservaDeReproduccion = new ReservaDeReproduccion(new Posicion(2,2), jugador);
        jugador.agregarConstruccion(reservaDeReproduccion);
        Guarida guarida = new Guarida(new Posicion(1,2), jugador);
        jugador.agregarConstruccion(guarida);
        for (int i = 0; i < 13; i++)
            jugador.pasarTiempo();

        Mineral mineral = new Mineral(75);
        GasVespeno gas = new GasVespeno(25);
        Hidralisco hidralisco = guarida.generarHidralisco(mineral, gas, new Larva());
        assertNotNull(hidralisco);
    }
}
