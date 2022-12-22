package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Edificios.Criadero;
import edu.fiuba.algo3.modelo.Edificios.Espiral;
import edu.fiuba.algo3.modelo.Edificios.Guarida;
import edu.fiuba.algo3.modelo.Edificios.ReservaDeReproduccion;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Individuos.Hidralisco;
import edu.fiuba.algo3.modelo.Individuos.Mutalisco;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EspiralTest {
    //caso de uso 10

    @Test
    public void seRegeneraTodaLaVidaDespuesDeAlgunosTurnos() throws NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        //given
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Criadero criadero = new Criadero(new Posicion(1,3), jugador);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.incrementarMineral(2000);
        jugador.incrementarGas(2000);
        ReservaDeReproduccion reserva = new ReservaDeReproduccion(new Posicion(1,2), jugador);
        for(int i = 0; i < 13; i += 1){
            jugador.pasarTiempo();
        }
        Guarida guarida = new Guarida(new Posicion(2,1), jugador);
        for(int i = 0; i < 13; i += 1){
            jugador.pasarTiempo();
        }

        Espiral espiral = new Espiral(new Posicion(2,2), jugador);
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();

        //when
        espiral.dañar(10);
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

    // Caso de uso 17
    @Test
    public void noSePuedeConstruirEspiralSiNoHayGuarida() throws NoExisteEdificioCorrelativoException {
        //given
        assertThrows(NoExisteEdificioCorrelativoException.class, () ->{ new Espiral(new Posicion(1,1), new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), new Mapa(), 200));});
    }


    // caso 22
    @Test
    public void seEngendraMutaliscoExitosamente() throws NoExisteEdificioCorrelativoException, RequerimientosInsuficientesException, RecursosInsuficientesException {
        //given
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        jugador.incrementarMineral(1000);
        Criadero criadero = new Criadero(new Posicion(2,3), jugador);
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        ReservaDeReproduccion reservaDeReproduccion = new ReservaDeReproduccion(new Posicion(2,2), jugador);
        reservaDeReproduccion.pasarTiempo();
        reservaDeReproduccion.pasarTiempo();
        reservaDeReproduccion.pasarTiempo();
        reservaDeReproduccion.pasarTiempo();
        reservaDeReproduccion.pasarTiempo();
        reservaDeReproduccion.pasarTiempo();
        reservaDeReproduccion.pasarTiempo();
        reservaDeReproduccion.pasarTiempo();
        reservaDeReproduccion.pasarTiempo();
        reservaDeReproduccion.pasarTiempo();
        jugador.incrementarMineral(2000);
        jugador.incrementarGas(2000);
        Guarida guarida = new Guarida(new Posicion(2,1), jugador);
        Espiral espiral = new Espiral(new Posicion(3,3), jugador);
        for (int i = 0; i < 10; i++)
            espiral.pasarTiempo();

        Mineral mineral = new Mineral(100);
        GasVespeno gas = new GasVespeno(100);
        Mutalisco mutalisco = espiral.generarMutalisco(mineral, gas, new Larva());
        assertNotNull(mutalisco);
    }
}
