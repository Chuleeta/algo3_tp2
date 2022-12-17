package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Edificios.Asimilador;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.VolcanOcupadoException;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import edu.fiuba.algo3.modelo.Recursos.Volcan;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AsimiladorTest {
    
    @Test
    public void asimiladorConsigueVeinteDeGasEnUnTurno() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "protoss", new Posicion(1,1), mapa, 200);
        Asimilador asimilador = new Asimilador(new Posicion(1, 2), new Volcan(new Posicion(1, 2)), jugador);
        jugador.agregarConstruccion(asimilador);

        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();

        jugador.pasarTiempo();
 
        assertEquals(20, asimilador.obtenerGas());
    }

    @Test
    public void asimiladorEmpiezaSinGasRecolectado() throws VolcanOcupadoException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "protoss", new Posicion(1,1), mapa, 200);
        Asimilador asimilador = new Asimilador(new Posicion(2, 1), new Volcan(new Posicion(2, 1)), mapa);
        jugador.agregarConstruccion(asimilador);
        assertEquals(0, asimilador.obtenerGas());    
    }

    @Test
    public void asimiladorConsigueVeinteDeGasEnCadaTurno() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "protoss", new Posicion(1,1), mapa, 200);
        Asimilador asimilador = new Asimilador(new Posicion(2, 1), new Volcan(new Posicion(2, 1)), jugador);
        jugador.agregarConstruccion(asimilador);

        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();

        jugador.pasarTiempo();
        assertEquals(20, asimilador.obtenerGas());
        jugador.pasarTiempo();
        assertEquals(40, asimilador.obtenerGas());
        jugador.pasarTiempo();
        assertEquals(60, asimilador.obtenerGas());
        jugador.pasarTiempo();
        assertEquals(80, asimilador.obtenerGas());
        jugador.pasarTiempo();
        assertEquals(100, asimilador.obtenerGas());
    }

    // Caso de uso 11
    @Test
    public void recibeDañoYElEscudoYSeRecuperaConElTiempoHastaEstarCompleto() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "protoss", new Posicion(1,1), mapa, 200);
        Asimilador asimilador = new Asimilador(new Posicion(2, 1), new Volcan(new Posicion(2, 1)), jugador);
        jugador.agregarConstruccion(asimilador);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        asimilador.dañar(450);
        assertFalse(asimilador.tieneEscudoCompleto());

        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        assertTrue(asimilador.tieneEscudoCompleto());
    }

    // Caso de uso 12
    @Test
    public void recibeDañoElEscudoYSeRecuperaPeroLaVidaNo() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "protoss", new Posicion(1,1), mapa, 200);
        Asimilador asimilador = new Asimilador(new Posicion(2, 1), new Volcan(new Posicion(2, 1)), mapa);
        jugador.agregarConstruccion(asimilador);
        asimilador.dañar(500);
        assertFalse(asimilador.tieneEscudoCompleto());
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();

        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        assertTrue(asimilador.tieneEscudoCompleto());
        assertFalse(asimilador.tieneVidaCompleta());
    }

}
