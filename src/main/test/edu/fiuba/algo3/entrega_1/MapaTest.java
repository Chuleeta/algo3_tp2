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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        //assertTrue(new Extractor(new Posicion(8,8), new Volcan(new Posicion(8, 8)), mapa));
        //assertFalse(new Extractor(new Posicion(125,3), new Volcan(new Posicion(125, 3)), mapa));
        assertDoesNotThrow(()->{ new Extractor(new Posicion(8,8), new Volcan(new Posicion(8, 8)), jugador); });
        assertThrows(RecursosInsuficientesException.class, () ->{ new Extractor(new Posicion(125,3), new Volcan(new Posicion(125, 3)), jugador);});
    }

    @Test
    public void noSePuedeConstruirAccesoFueraDeEnergia() throws NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "protoss", new Posicion(1,1), mapa, 200);
        Pilon pilon = new Pilon(new Posicion(10,10), jugador);
        jugador.incrementarMineral(300);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        //assertTrue(jugador.agregarConstruccion(new Acceso(new Posicion(10, 9), mapa )));
        //assertFalse(jugador.agregarConstruccion(new Acceso(new Posicion(2, 2), mapa)));
        assertDoesNotThrow(()->{ new Acceso(new Posicion(10, 9), jugador); });
        assertThrows(RecursosInsuficientesException.class, () ->{ new Acceso(new Posicion(2, 2), jugador);});
    }

    // Caso de uso 6

    @Test
    public void sePropagaElMohoAlConstruirse() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Criadero criadero = new Criadero(new Posicion(9, 9), jugador);
        jugador.incrementarMineral(200);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        //assertFalse(jugador.agregarConstruccion(new Extractor(new Posicion(8, 8), new Volcan(new Posicion(8, 8)), mapa)));
        assertThrows(RecursosInsuficientesException.class, () ->{ new Extractor(new Posicion(8, 8), new Volcan(new Posicion(8, 8)), jugador);});
        jugador.pasarTiempo();
        assertDoesNotThrow(()->{ new Extractor(new Posicion(8, 8), new Volcan(new Posicion(8, 8)), jugador); });
        //assertTrue(jugador.agregarConstruccion(new Extractor(new Posicion(8, 8), new Volcan(new Posicion(8, 8)), mapa)));
    }

    @Test
    public void sePropagaElMohoLentamentePosteriorALaConstruccion() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Criadero criadero = new Criadero(new Posicion(9, 9), jugador);
        jugador.incrementarMineral(800);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        //assertFalse(jugador.agregarConstruccion(new Extractor(new Posicion(9, 16), new Volcan(new Posicion(9, 16)), mapa)));
        assertThrows(RecursosInsuficientesException.class, () ->{ new Extractor(new Posicion(9, 16), new Volcan(new Posicion(9, 16)), jugador);});
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        //assertTrue(jugador.agregarConstruccion(new Extractor(new Posicion(3, 15), new Volcan(new Posicion(3, 15)), mapa)));
        assertDoesNotThrow(()->{ new Extractor(new Posicion(3, 15), new Volcan(new Posicion(3, 15)), jugador); });
    }
    
    @Test
    public void noSePuedeConstruirExtractorSinRecursos() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Criadero criadero = new Criadero(new Posicion(9,9), jugador);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        //assertFalse(jugador.agregarConstruccion(new Extractor(new Posicion(9,8), new Volcan(new Posicion(9, 8)), mapa)));
        assertThrows(RecursosInsuficientesException.class, () ->{ new Extractor(new Posicion(9,8), new Volcan(new Posicion(9, 8)), jugador);});
    }

    @Test
    public void noSePuedeConstruirCriaderoSinRecursos() throws NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Criadero criadero = new Criadero(new Posicion(9,9), jugador);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        //assertFalse(jugador.agregarConstruccion(new Criadero(new Posicion(9,8), mapa)));
        assertThrows(RecursosInsuficientesException.class, () ->{ new Criadero(new Posicion(9,8), jugador);});
    }

    @Test
    public void noSePuedeConstruirReservaSinRecursos() throws NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Criadero criadero = new Criadero(new Posicion(9,9), jugador);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        //assertFalse(jugador.agregarConstruccion(new ReservaDeReproduccion(new Posicion(9,8), mapa)));
        assertThrows(RecursosInsuficientesException.class, () ->{ new ReservaDeReproduccion(new Posicion(9,8), jugador);});
    }

    @Test
    public void noSePuedeConstruirGuaridaSinRecursos() throws NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Criadero criadero = new Criadero(new Posicion(9,9), jugador);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        //assertFalse(jugador.agregarConstruccion(new Guarida(new Posicion(9,8), mapa)));
        assertThrows(NoExisteEdificioCorrelativoException.class, () ->{ new Guarida(new Posicion(9,8), jugador);});
    }

    @Test
    public void noSePuedeConstruirEspiralSinRecursos() throws NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Criadero criadero = new Criadero(new Posicion(9,9), jugador);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        assertThrows(NoExisteEdificioCorrelativoException.class, () ->{ new Espiral(new Posicion(9,8), jugador);});
        //assertFalse(jugador.agregarConstruccion(new Espiral(new Posicion(9,8), mapa)));
    }

    @Test
    public void noSePuedeConstruirNexoMineralSinRecursos() throws MenaOcupadaException, NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "protoss", new Posicion(1,1), mapa, 200);
        Pilon pilon = new Pilon(new Posicion(9,9), jugador);
        Pilon pilonDos = new Pilon(new Posicion(10, 9), jugador);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        //assertFalse(jugador.agregarConstruccion(new NexoMineral(new Posicion(9,8), new Mena(new Posicion(9, 8)), mapa)));
        //new NexoMineral(new Posicion(9,8), new Mena(new Posicion(9, 8)), jugador);
        assertThrows(RecursosInsuficientesException.class, () ->{ new NexoMineral(new Posicion(9,8), new Mena(new Posicion(9, 8)), jugador);});
    }

    @Test
    public void noSePuedeConstruirPilonSinRecursos() throws NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "protoss", new Posicion(1,1), mapa, 200);
        Pilon pilon = new Pilon(new Posicion(9,9), jugador);
        Pilon pilonDos = new Pilon(new Posicion(10, 9), jugador);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        //assertFalse(jugador.agregarConstruccion(new Pilon(new Posicion(9,8), mapa)));
        assertThrows(RecursosInsuficientesException.class, () ->{ new Pilon(new Posicion(9,8), jugador);});
    }

    @Test
    public void noSePuedeConstruirAsimiladorSinRecursos() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "protoss", "zerg", new Posicion(1,1), mapa, 200);
        Pilon pilon = new Pilon(new Posicion(9,9), jugador);
        Pilon pilonDos = new Pilon(new Posicion(10, 9), jugador);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        //assertFalse(jugador.agregarConstruccion(new Asimilador(new Posicion(9,8), new Volcan(new Posicion(9, 8)), mapa)));
        assertThrows(RecursosInsuficientesException.class, () ->{ new Asimilador(new Posicion(9,8), new Volcan(new Posicion(9, 8)), jugador);});
    }

    @Test
    public void noSePuedeConstruirAccesoSinRecursos() throws NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "protoss", new Posicion(1,1), mapa, 200);
        Pilon pilon = new Pilon(new Posicion(9,9), jugador);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        //assertFalse(jugador.agregarConstruccion(new Acceso(new Posicion(9,8), mapa)));
        assertThrows(RecursosInsuficientesException.class, () ->{ new Acceso(new Posicion(9,8), jugador);});
    }

    @Test
    public void noSePuedeConstruirPuertoEstelarSinRecursos() throws NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "protoss", new Posicion(1,1), mapa, 200);
        Pilon pilon = new Pilon(new Posicion(9,9), jugador);
        jugador.incrementarMineral(50);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        Acceso acceso = new Acceso(new Posicion(9, 10), jugador);
        //assertFalse(jugador.agregarConstruccion(new PuertoEstelar(new Posicion(9,8), mapa)));
        assertThrows(RecursosInsuficientesException.class, () ->{ new PuertoEstelar(new Posicion(9,8), jugador);});
    }

    @Test
    public void seDestruyeUnPilonYNoSePuedeConstruirCercaDeSuCadaver() throws NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "protoss", new Posicion(1,1), mapa, 200);
        Pilon pilon = new Pilon(new Posicion(9,9), jugador);
        jugador.incrementarMineral(800);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        //assertTrue(jugador.agregarConstruccion(new Acceso(new Posicion(9,8), jugador)));
        assertDoesNotThrow(()->{ new Acceso(new Posicion(9,8), jugador); });
        pilon.destruir();
        //assertFalse(jugador.agregarConstruccion(new Acceso(new Posicion(9,10), jugador)));
        assertThrows(RecursosInsuficientesException.class, () ->{ new Acceso(new Posicion(9,10), jugador);});
    }


    @Test
    public void seDestruyeUnPilonPeroExistiendoOtroEnElAreaNoSeDesactiva() throws NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "protoss", new Posicion(1,1), mapa, 200);
        Pilon pilon = new Pilon(new Posicion(9,9), jugador);
        jugador.incrementarMineral(800);
        Pilon pilon2 = new Pilon(new Posicion(9,7), jugador);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        Acceso acceso = new Acceso(new Posicion(9,8), jugador);
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
        for  (int i = 0; i < 100; i++)
            jugador.pasarTiempo();
        assertTrue(acceso.tieneEscudoCompleto());
        
        pilon.destruir();
        jugador.pasarTiempo();
        acceso.dañar(100);
        for (int i = 0;  i < 100; i++)
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
        Criadero criadero = new Criadero(new Posicion(9,7), jugador);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        // Construccion acceso = new Acceso(new Posicion(9,8), jugador);
        // assertFalse(jugador.agregarConstruccion(acceso));
        assertThrows(RecursosInsuficientesException.class, () ->{ new Acceso(new Posicion(9,8), jugador);});
    }

    @Test
    public void elMohoSePuedeExpandirPorUnAreaEnergizada() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "protoss", new Posicion(1,1), mapa, 200);
        Pilon pilon = new Pilon(new Posicion(9,9), jugador);
        jugador.incrementarMineral(800);
        Criadero criadero = new Criadero(new Posicion(9,20), jugador);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();

        for(int i = 0; i < 40; i+=1){
            jugador.pasarTiempo();
        }
        // Construccion extractor = new Extractor(new Posicion(9,10), new Volcan(new Posicion(9, 10)), mapa);
        // assertTrue(jugador.agregarConstruccion(extractor));
        assertDoesNotThrow(()->{ new Extractor(new Posicion(9,10), new Volcan(new Posicion(9, 10)), jugador); });
    }

}
