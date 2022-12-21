package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Edificios.Acceso;
import edu.fiuba.algo3.modelo.Edificios.Pilon;
import edu.fiuba.algo3.modelo.Edificios.PuertoEstelar;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PuertoEstelarTest {

    //Caso de uso 11
    @Test
    public void recibeDañoYElEscudoYSeRecuperaConElTiempoHastaEstarCompleto() throws NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "protoss", "zerg", new Posicion(1,1), mapa, 200);
        Pilon pilon = new Pilon(new Posicion(1, 3), jugador);
        jugador.incrementarMineral(800);
        jugador.incrementarGas(800);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        Acceso acceso = new Acceso(new Posicion(1, 2), jugador);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        PuertoEstelar puertoEstelar = new PuertoEstelar(new Posicion(2, 1), jugador);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        puertoEstelar.dañar(200);
        assertFalse(puertoEstelar.tieneEscudoCompleto());
        for (int i = 0;  i < 100; i++)
            puertoEstelar.pasarTiempo();
        assertTrue(puertoEstelar.tieneEscudoCompleto());
    }

    //Caso de uso 12
    @Test
    public void recibeDañoElEscudoYSeRecuperaPeroLaVidaNo() throws NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "protoss", "zerg", new Posicion(1,1), mapa, 200);
        jugador.incrementarMineral(800);
        jugador.incrementarGas(800);
        Pilon pilon = new Pilon(new Posicion(2, 1), jugador);

        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        Acceso acceso = new Acceso(new Posicion(1,2), jugador);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();

        PuertoEstelar puertoEstelar = new PuertoEstelar(new Posicion(2, 2), jugador);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        puertoEstelar.dañar(700);
        assertFalse(puertoEstelar.tieneEscudoCompleto());

        for (int i = 0;  i < 200; i++)
            jugador.pasarTiempo();
        assertTrue(puertoEstelar.tieneEscudoCompleto());
        assertFalse(puertoEstelar.tieneVidaCompleta());
    }

    @Test
    public void noSePuedeConstruirPuertoEstelarSiNoHayAcceso() throws NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        //given
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "protoss", new Posicion(1,1), mapa, 200);
        assertThrows(NoExisteEdificioCorrelativoException.class, () ->{ new PuertoEstelar(new Posicion(1,1), jugador);});
    }

}
