package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Edificios.NexoMineral;
import edu.fiuba.algo3.modelo.Exceptions.MenaOcupadaException;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mena;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NexoMineralTest {
    //Caso de uso 7
    @Test
    public void SeMinaElMineralConUnNexoMineralYLoMinaExitosamente() throws MenaOcupadaException, NoExisteEdificioCorrelativoException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "protoss", new Posicion(1,1), mapa, 200);
        NexoMineral nexito = new NexoMineral(new Posicion(2, 2), new Mena(new Posicion(2, 2)), mapa, jugador);
        jugador.agregarConstruccion(nexito);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        assertEquals(jugador.mineral.getCantidad(), 250);
    }

    @Test
    public void nexoMineralNoConstruidoNoMinaMineral() throws MenaOcupadaException, NoExisteEdificioCorrelativoException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "protoss", new Posicion(1,1), mapa, 200);
        NexoMineral nexito = new NexoMineral(new Posicion(2, 2), new Mena(new Posicion(2, 2)), mapa, jugador);
        jugador.agregarConstruccion(nexito);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();

        assertEquals(jugador.mineral.getCantidad(), 150);
    }

    @Test
    public void nexoMineralNoMinaMenaAgotada() throws MenaOcupadaException, NoExisteEdificioCorrelativoException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "protoss", "zerg", new Posicion(1,1), mapa, 200);
        NexoMineral nexito = new NexoMineral(new Posicion(2, 1), new Mena(new Posicion(2, 1)), mapa, jugador);
        jugador.agregarConstruccion(nexito);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        for(int i = 0; i<60; i+=1){
            jugador.pasarTiempo();
        }
        assertEquals(jugador.mineral.getCantidad(), 2150);
    }

    // Caso de uso 11
    @Test
    public void recibeDañoYElEscudoYSeRecuperaConElTiempoHastaEstarCompleto() throws MenaOcupadaException, NoExisteEdificioCorrelativoException {

        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "protoss", "zerg", new Posicion(1,1), mapa, 200);
        NexoMineral nexito = new NexoMineral(new Posicion(2, 1), new Mena(new Posicion(2, 1)), mapa, jugador);
        jugador.agregarConstruccion(nexito);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        nexito.dañar(200);
        assertFalse(nexito.tieneEscudoCompleto());
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        assertTrue(nexito.tieneEscudoCompleto());
    }

    // Caso de uso 12
    @Test
    public void recibeDañoElEscudoYSeRecuperaPeroLaVidaNo() throws MenaOcupadaException, NoExisteEdificioCorrelativoException {

        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "protoss", "zerg", new Posicion(1,1), mapa, 200);
        NexoMineral nexito = new NexoMineral(new Posicion(2, 1), new Mena(new Posicion(2, 1)), mapa, jugador);
        jugador.agregarConstruccion(nexito);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        nexito.dañar(300);
        assertFalse(nexito.tieneEscudoCompleto());
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        assertTrue(nexito.tieneEscudoCompleto());
        assertFalse(nexito.tieneVidaCompleta());
    }
}
