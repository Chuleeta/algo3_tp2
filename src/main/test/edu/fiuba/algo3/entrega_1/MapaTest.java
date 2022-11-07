package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Criadero;
import edu.fiuba.algo3.modelo.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MapaTest {
    
        //Caso de uso 5

        @Test
        public void noSePuedeConstruirExtractorFueraDelMoho() 
        {
            Mapa mapa = new Mapa(10, 10);
            Criadero criadero = new Criadero();
            mapa.agregarConstruccion(criadero, 9, 9);
            mapa.pasarTiempo();
            mapa.pasarTiempo();
            mapa.pasarTiempo();
            mapa.pasarTiempo();
            assertTrue(mapa.agregarConstruccion(new Extractor(), 8, 8)); //construccion, direccion x, direccion y
            assertFalse(mapa.agregarConstruccion(new Extractor(), 1, 1));
        }

        @Test
        public void noSePuedeConstruirAccesoFueraDelMoho() 
        {
            Mapa mapa = new Mapa(10, 10);
            Pilon pilon = new Pilon();
            mapa.agregarConstruccion(pilon, 9, 9);
            mapa.pasarTiempo();
            mapa.pasarTiempo();
            mapa.pasarTiempo();
            mapa.pasarTiempo();
            mapa.pasarTiempo();
            assertTrue(mapa.agregarConstruccion(new Acceso(), 8, 8)); //construccion, direccion x, direccion y
            assertFalse(mapa.agregarConstruccion(new Acceso(), 1, 1));
        }

        // Caso de uso 6

        @Test
        public void sePropagaElMohoAlConstruirse() 
        {
            Mapa mapa = new Mapa(10, 10);
            Criadero criadero = new Criadero();
            mapa.agregarConstruccion(criadero, 9, 9);
            mapa.pasarTiempo();
            mapa.pasarTiempo();
            mapa.pasarTiempo();
            assertFalse(mapa.agregarConstruccion(new Extractor(), 8, 8)); //construccion, direccion x, direccion y
            mapa.pasarTiempo();
            assertFalse(mapa.agregarConstruccion(new Extractor(), 8, 8));
        }

        @Test
        public void sePropagaElMohoLentamentePosteriorALaConstruccion() 
        {
            Mapa mapa = new Mapa(10, 10);
            Criadero criadero = new Criadero();
            mapa.agregarConstruccion(criadero, 9, 9);
            mapa.pasarTiempo();
            mapa.pasarTiempo();
            mapa.pasarTiempo();              
            mapa.pasarTiempo();
            assertFalse(mapa.agregarConstruccion(new Extractor(), 3, 3));
            mapa.pasarTiempo();
            assertTrue(mapa.agregarConstruccion(new Extractor(), 3, 3));
        }


}
