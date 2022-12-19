package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Edificios.Acceso;
import edu.fiuba.algo3.modelo.Edificios.Asimilador;
import edu.fiuba.algo3.modelo.Edificios.Criadero;
import edu.fiuba.algo3.modelo.Edificios.Espiral;
import edu.fiuba.algo3.modelo.Edificios.Extractor;
import edu.fiuba.algo3.modelo.Edificios.Guarida;
import edu.fiuba.algo3.modelo.Edificios.NexoMineral;
import edu.fiuba.algo3.modelo.Edificios.Pilon;
import edu.fiuba.algo3.modelo.Edificios.PuertoEstelar;
import edu.fiuba.algo3.modelo.Edificios.ReservaDeReproduccion;
import edu.fiuba.algo3.modelo.Exceptions.MenaOcupadaException;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.Exceptions.VolcanOcupadoException;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mena;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import edu.fiuba.algo3.modelo.Recursos.Volcan;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MapaTest {
    //Caso de uso 5

     
    @Test
    public void noSePuedeConstruirExtractorFueraDelMoho() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Criadero criadero = new Criadero(new Posicion(9,9), jugador);
        jugador.incrementarMineral(2000);
        jugador.agregarConstruccion(criadero);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        assertTrue(jugador.agregarConstruccion(new Extractor(new Posicion(8,8), new Volcan(new Posicion(8, 8)), mapa)));
        assertFalse(jugador.agregarConstruccion(new Extractor(new Posicion(125,3), new Volcan(new Posicion(125, 3)), mapa)));
    }

    @Test
    public void noSePuedeConstruirAccesoFueraDeEnergia() throws NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "protoss", new Posicion(1,1), mapa, 200);
        Pilon pilon = new Pilon(new Posicion(10,10), jugador);
        jugador.incrementarMineral(300);
        jugador.agregarConstruccion(pilon);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        assertTrue(jugador.agregarConstruccion(new Acceso(new Posicion(10, 9), mapa )));
        assertFalse(jugador.agregarConstruccion(new Acceso(new Posicion(2, 2), mapa)));
    }

    // Caso de uso 6

    @Test
    public void sePropagaElMohoAlConstruirse() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Criadero criadero = new Criadero(new Posicion(9, 9), jugador);
        jugador.incrementarMineral(200);
        jugador.agregarConstruccion(criadero);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        assertFalse(jugador.agregarConstruccion(new Extractor(new Posicion(8, 8), new Volcan(new Posicion(8, 8)), mapa)));
        jugador.pasarTiempo();
        assertTrue(jugador.agregarConstruccion(new Extractor(new Posicion(8, 8), new Volcan(new Posicion(8, 8)), mapa)));
    }

    @Test
    public void sePropagaElMohoLentamentePosteriorALaConstruccion() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Criadero criadero = new Criadero(new Posicion(9, 9), jugador);
        jugador.incrementarMineral(800);
        jugador.agregarConstruccion(criadero);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        assertFalse(jugador.agregarConstruccion(new Extractor(new Posicion(9, 16), new Volcan(new Posicion(9, 16)), mapa)));
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        assertTrue(jugador.agregarConstruccion(new Extractor(new Posicion(3, 15), new Volcan(new Posicion(3, 15)), mapa)));
    }

    @Test
    public void noSePuedeConstruirExtractorSinRecursos() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Criadero criadero = new Criadero(new Posicion(9,9), jugador);
        jugador.agregarConstruccion(criadero);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        assertFalse(jugador.agregarConstruccion(new Extractor(new Posicion(9,8), new Volcan(new Posicion(9, 8)), mapa)));
    }

    @Test
    public void noSePuedeConstruirCriaderoSinRecursos() throws NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Criadero criadero = new Criadero(new Posicion(9,9), jugador);
        jugador.agregarConstruccion(criadero);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        assertFalse(jugador.agregarConstruccion(new Criadero(new Posicion(9,8), mapa)));
    }

    @Test
    public void noSePuedeConstruirReservaSinRecursos() throws NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Criadero criadero = new Criadero(new Posicion(9,9), jugador);
        jugador.agregarConstruccion(criadero);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        assertFalse(jugador.agregarConstruccion(new ReservaDeReproduccion(new Posicion(9,8), mapa)));
    }

    @Test
    public void noSePuedeConstruirGuaridaSinRecursos() throws NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Criadero criadero = new Criadero(new Posicion(9,9), jugador);
        jugador.agregarConstruccion(criadero);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        assertFalse(jugador.agregarConstruccion(new Guarida(new Posicion(9,8), mapa)));
    }

    @Test
    public void noSePuedeConstruirEspiralSinRecursos() throws NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Criadero criadero = new Criadero(new Posicion(9,9), jugador);
        jugador.agregarConstruccion(criadero);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        assertFalse(jugador.agregarConstruccion(new Espiral(new Posicion(9,8), mapa)));
    }

    @Test
    public void noSePuedeConstruirNexoMineralSinRecursos() throws MenaOcupadaException, NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "protoss", new Posicion(1,1), mapa, 200);
        Pilon pilon = new Pilon(new Posicion(9,9), jugador);
        Pilon pilonDos = new Pilon(new Posicion(10, 9), jugador);
        jugador.agregarConstruccion(pilonDos);
        jugador.agregarConstruccion(pilon);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        assertFalse(jugador.agregarConstruccion(new NexoMineral(new Posicion(9,8), new Mena(new Posicion(9, 8)), mapa)));
    }

    @Test
    public void noSePuedeConstruirPilonSinRecursos() throws NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "protoss", new Posicion(1,1), mapa, 200);
        Pilon pilon = new Pilon(new Posicion(9,9), jugador);
        Pilon pilonDos = new Pilon(new Posicion(10, 9), jugador);
        jugador.agregarConstruccion(pilonDos);
        jugador.agregarConstruccion(pilon);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        assertFalse(jugador.agregarConstruccion(new Pilon(new Posicion(9,8), mapa)));
    }

    @Test
    public void noSePuedeConstruirAsimiladorSinRecursos() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "protoss", "zerg", new Posicion(1,1), mapa, 200);
        Pilon pilon = new Pilon(new Posicion(9,9), jugador);
        Pilon pilonDos = new Pilon(new Posicion(10, 9), jugador);
        jugador.agregarConstruccion(pilonDos);
        jugador.agregarConstruccion(pilon);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        assertFalse(jugador.agregarConstruccion(new Asimilador(new Posicion(9,8), new Volcan(new Posicion(9, 8)), mapa)));
    }

    @Test
    public void noSePuedeConstruirAccesoSinRecursos() throws NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "protoss", new Posicion(1,1), mapa, 200);
        Pilon pilon = new Pilon(new Posicion(9,9), jugador);
        jugador.agregarConstruccion(pilon);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        assertFalse(jugador.agregarConstruccion(new Acceso(new Posicion(9,8), mapa)));
    }

    @Test
    public void noSePuedeConstruirPuertoEstelarSinRecursos() throws NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "protoss", new Posicion(1,1), mapa, 200);
        Pilon pilon = new Pilon(new Posicion(9,9), jugador);
        jugador.agregarConstruccion(pilon);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        assertFalse(jugador.agregarConstruccion(new PuertoEstelar(new Posicion(9,8), mapa)));
    }

    @Test
    public void seDestruyeUnPilonYNoSePuedeConstruirCercaDeSuCadaver() throws NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "protoss", new Posicion(1,1), mapa, 200);
        Pilon pilon = new Pilon(new Posicion(9,9), jugador);
        jugador.incrementarMineral(800);
        jugador.agregarConstruccion(pilon);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        assertTrue(jugador.agregarConstruccion(new Acceso(new Posicion(9,8), mapa)));
        pilon.destruir();
        assertFalse(jugador.agregarConstruccion(new Acceso(new Posicion(9,10), mapa)));
    }


    @Test
    public void seDestruyeUnPilonPeroExistiendoOtroEnElAreaNoSeDesactiva() throws NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "protoss", new Posicion(1,1), mapa, 200);
        Pilon pilon = new Pilon(new Posicion(9,9), jugador);
        jugador.incrementarMineral(800);
        jugador.agregarConstruccion(pilon);
        Pilon pilon2 = new Pilon(new Posicion(9,7), jugador);
        jugador.agregarConstruccion(pilon2);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        Acceso acceso = new Acceso(new Posicion(9,8), jugador);
        assertTrue(jugador.agregarConstruccion(acceso));
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

        acceso.dañar(100);
        jugador.pasarTiempo();
        assertTrue(acceso.tieneEscudoCompleto());
        
        pilon.destruir();
        jugador.pasarTiempo();
        acceso.dañar(100);
        jugador.pasarTiempo();
        assertTrue(acceso.tieneEscudoCompleto());
        
        pilon2.destruir();
        jugador.pasarTiempo();
        acceso.dañar(100);
        jugador.pasarTiempo();
        assertFalse(acceso.tieneEscudoCompleto());

    }


    // Caso de uso 14
    @Test
    public void noSePuedeConstruirUnaEstructuraProtossSiHayMohoEnSuAreaEnergizada() throws NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "protoss", new Posicion(1,1), mapa, 200);
        Pilon pilon = new Pilon(new Posicion(9,9), jugador);
        jugador.incrementarMineral(800);
        jugador.agregarConstruccion(pilon);
        Criadero criadero = new Criadero(new Posicion(9,7), jugador);
        jugador.agregarConstruccion(criadero);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        Construccion acceso = new Acceso(new Posicion(9,8), jugador);
        assertFalse(jugador.agregarConstruccion(acceso));
    }

    @Test
    public void elMohoSePuedeExpandirPorUnAreaEnergizada() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "protoss", new Posicion(1,1), mapa, 200);
        Pilon pilon = new Pilon(new Posicion(9,9), jugador);
        jugador.incrementarMineral(800);
        jugador.agregarConstruccion(pilon);
        Criadero criadero = new Criadero(new Posicion(9,20), jugador);
        jugador.agregarConstruccion(criadero);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();

        for(int i = 0; i < 40; i+=1){
            jugador.pasarTiempo();
        }
        Construccion extractor = new Extractor(new Posicion(9,10), new Volcan(new Posicion(9, 10)), mapa);
        assertTrue(jugador.agregarConstruccion(extractor));
    }

}
