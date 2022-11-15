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
    public void noSePuedeConstruirExtractorFueraDelMoho() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException {
        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(10000);
        
        Mapa mapa = new Mapa();
        Criadero criadero = new Criadero(new Posicion(9,9), mapa);
        mapa.agregarConstruccion(criadero, mineral, gas);
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        assertTrue(mapa.agregarConstruccion(new Extractor(new Posicion(8,8), new Volcan(new Posicion(8, 8)), mapa), mineral, gas));
        assertFalse(mapa.agregarConstruccion(new Extractor(new Posicion(125,3), new Volcan(new Posicion(125, 3)), mapa), mineral, gas));
    }

    @Test
    public void noSePuedeConstruirAccesoFueraDeEnergia() throws NoExisteEdificioCorrelativoException {
        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(10000);

        Mapa mapa = new Mapa();
        Pilon pilon = new Pilon(new Posicion(9,9), mapa);
        mapa.agregarConstruccion(pilon, mineral, gas);
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        assertTrue(mapa.agregarConstruccion(new Acceso(new Posicion(8, 8), mapa), mineral, gas)); 
        assertFalse(mapa.agregarConstruccion(new Acceso(new Posicion(1, 1), mapa), mineral, gas));
    }

    // Caso de uso 6

    @Test
    public void sePropagaElMohoAlConstruirse() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException {
        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(10000);
        Mapa mapa = new Mapa();
        Criadero criadero = new Criadero(new Posicion(9, 9), mapa);
        mapa.agregarConstruccion(criadero, mineral, gas);
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        assertFalse(mapa.agregarConstruccion(new Extractor(new Posicion(8, 8), new Volcan(new Posicion(8, 8)), mapa), mineral, gas)); 
        mapa.pasarTiempo();
        assertTrue(mapa.agregarConstruccion(new Extractor(new Posicion(8, 8), new Volcan(new Posicion(8, 8)), mapa), mineral, gas));
    }

    @Test
    public void sePropagaElMohoLentamentePosteriorALaConstruccion() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException {
        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(10000);
        Mapa mapa = new Mapa();
        Criadero criadero = new Criadero(new Posicion(9, 9), mapa);
        mapa.agregarConstruccion(criadero, mineral, gas);
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();              
        mapa.pasarTiempo();
        assertFalse(mapa.agregarConstruccion(new Extractor(new Posicion(9, 16), new Volcan(new Posicion(9, 16)), mapa), mineral, gas));
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        assertTrue(mapa.agregarConstruccion(new Extractor(new Posicion(3, 15), new Volcan(new Posicion(3, 15)), mapa), mineral, gas));
    }

    @Test
    public void noSePuedeConstruirExtractorSinRecursos() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException {
        Mineral mineral = new Mineral(0);
        GasVespeno gas = new GasVespeno(0);
        Mapa mapa = new Mapa();
        Criadero criadero = new Criadero(new Posicion(9,9), mapa);
        mapa.agregarConstruccion(criadero, mineral, gas);
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        assertFalse(mapa.agregarConstruccion(new Extractor(new Posicion(9,8), new Volcan(new Posicion(9, 8)), mapa), mineral, gas));
    }

    @Test
    public void noSePuedeConstruirCriaderoSinRecursos() throws NoExisteEdificioCorrelativoException {
        Mineral mineral = new Mineral(0);
        GasVespeno gas = new GasVespeno(0);
        
        Mapa mapa = new Mapa();
        Criadero criadero = new Criadero(new Posicion(9,9), mapa);
        mapa.agregarConstruccion(criadero, mineral, gas);
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        assertFalse(mapa.agregarConstruccion(new Criadero(new Posicion(9,8), mapa), mineral, gas));
    }

    @Test
    public void noSePuedeConstruirReservaSinRecursos() throws NoExisteEdificioCorrelativoException {
        Mineral mineral = new Mineral(0);
        GasVespeno gas = new GasVespeno(0);
        
        Mapa mapa = new Mapa();
        Criadero criadero = new Criadero(new Posicion(9,9), mapa);
        mapa.agregarConstruccion(criadero, mineral, gas);
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        assertFalse(mapa.agregarConstruccion(new ReservaDeReproduccion(new Posicion(9,8), mapa), mineral, gas));
    }

    @Test
    public void noSePuedeConstruirGuaridaSinRecursos() throws NoExisteEdificioCorrelativoException {
        Mineral mineral = new Mineral(0);
        GasVespeno gas = new GasVespeno(0);
        
        Mapa mapa = new Mapa();
        Criadero criadero = new Criadero(new Posicion(9,9), mapa);
        mapa.agregarConstruccion(criadero, mineral, gas);
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        assertFalse(mapa.agregarConstruccion(new Guarida(new Posicion(9,8), mapa), mineral, gas));
    }

    @Test
    public void noSePuedeConstruirEspiralSinRecursos() throws NoExisteEdificioCorrelativoException {
        Mineral mineral = new Mineral(0);
        GasVespeno gas = new GasVespeno(0);
        
        Mapa mapa = new Mapa();
        Criadero criadero = new Criadero(new Posicion(9,9), mapa);
        mapa.agregarConstruccion(criadero, mineral, gas);
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        assertFalse(mapa.agregarConstruccion(new Espiral(new Posicion(9,8), mapa), mineral, gas));
    }

    @Test
    public void noSePuedeConstruirNexoMineralSinRecursos() throws MenaOcupadaException, NoExisteEdificioCorrelativoException {
        Mineral mineral = new Mineral(0);
        GasVespeno gas = new GasVespeno(0);
        
        Mapa mapa = new Mapa();
        Pilon pilon = new Pilon(new Posicion(9,9), mapa);
        mapa.agregarConstruccion(pilon, mineral, gas);
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        assertFalse(mapa.agregarConstruccion(new NexoMineral(new Posicion(9,8), new Mena(new Posicion(9, 8)), mapa), mineral, gas));
    }

    @Test
    public void noSePuedeConstruirPilonSinRecursos() throws NoExisteEdificioCorrelativoException {
        Mineral mineral = new Mineral(0);
        GasVespeno gas = new GasVespeno(0);
        
        Mapa mapa = new Mapa();
        Pilon pilon = new Pilon(new Posicion(9,9), mapa);
        mapa.agregarConstruccion(pilon, mineral, gas);
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        assertFalse(mapa.agregarConstruccion(new Pilon(new Posicion(9,8), mapa), mineral, gas));
    }

    @Test
    public void noSePuedeConstruirAsimiladorSinRecursos() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException {
        Mineral mineral = new Mineral(0);
        GasVespeno gas = new GasVespeno(0);
        
        Mapa mapa = new Mapa();
        Pilon pilon = new Pilon(new Posicion(9,9), mapa);
        mapa.agregarConstruccion(pilon, mineral, gas);
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        assertFalse(mapa.agregarConstruccion(new Asimilador(new Posicion(9,8), new Volcan(new Posicion(9, 8)), mapa), mineral, gas));
    }

    @Test
    public void noSePuedeConstruirAccesoSinRecursos() throws NoExisteEdificioCorrelativoException {
        Mineral mineral = new Mineral(0);
        GasVespeno gas = new GasVespeno(0);
        
        Mapa mapa = new Mapa();
        Pilon pilon = new Pilon(new Posicion(9,9), mapa);
        mapa.agregarConstruccion(pilon, mineral, gas);
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        assertFalse(mapa.agregarConstruccion(new Acceso(new Posicion(9,8), mapa), mineral, gas));
    }

    @Test
    public void noSePuedeConstruirPuertoEstelarSinRecursos() throws NoExisteEdificioCorrelativoException {
        Mineral mineral = new Mineral(0);
        GasVespeno gas = new GasVespeno(0);
        
        Mapa mapa = new Mapa();
        Pilon pilon = new Pilon(new Posicion(9,9), mapa);
        mapa.agregarConstruccion(pilon, mineral, gas);
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        assertFalse(mapa.agregarConstruccion(new PuertoEstelar(new Posicion(9,8), mapa), mineral, gas));
    }

    @Test
    public void seDestruyeUnPilonYNoSePuedeConstruirCercaDeSuCadaver() throws NoExisteEdificioCorrelativoException {
        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(10000);
        
        Mapa mapa = new Mapa();
        Pilon pilon = new Pilon(new Posicion(9,9), mapa);
        mapa.agregarConstruccion(pilon, mineral, gas);
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        assertTrue(mapa.agregarConstruccion(new Acceso(new Posicion(9,8), mapa), mineral, gas));
        pilon.destruir();
        assertFalse(mapa.agregarConstruccion(new Acceso(new Posicion(9,10), mapa), mineral, gas));
    }
    // ----------------------------------C A M B I A R----------------------------------------------------
    // @Test
    // public void seDestruyeUnPilonPeroExistiendoOtroEnElAreaNoSeDesactiva() throws NoExisteEdificioCorrelativoException {
    //     Mineral mineral = new Mineral(10000);
    //     GasVespeno gas = new GasVespeno(10000);
        
    //     Mapa mapa = new Mapa();
    //     Pilon pilon = new Pilon(new Posicion(9,9), mapa);
    //     mapa.agregarConstruccion(pilon, mineral, gas);
    //     Pilon pilon2 = new Pilon(new Posicion(9,7), mapa);
    //     mapa.agregarConstruccion(pilon2, mineral, gas);
    //     mapa.pasarTiempo();
    //     mapa.pasarTiempo();
    //     mapa.pasarTiempo();
    //     mapa.pasarTiempo();
    //     mapa.pasarTiempo();
    //     Construccion acceso = new Acceso(new Posicion(9,8), mapa);
    //     assertTrue(mapa.agregarConstruccion(acceso, mineral, gas));
    //     mapa.pasarTiempo();
    //     mapa.pasarTiempo();
    //     mapa.pasarTiempo();
    //     mapa.pasarTiempo();
    //     mapa.pasarTiempo();
    //     mapa.pasarTiempo();
    //     mapa.pasarTiempo();
    //     mapa.pasarTiempo();
    //     mapa.pasarTiempo();
    //     mapa.pasarTiempo();
    //     assertTrue(acceso.estaActivado());
    //     pilon.destruir();
    //     mapa.pasarTiempo();
    //     assertTrue(acceso.estaActivado());
    //     pilon2.destruir();
    //     mapa.pasarTiempo();
    //     assertFalse(acceso.estaActivado());

    // }
    // ----------------------------------C A M B I A R----------------------------------------------------

    // Caso de uso 14
    @Test
    public void noSePuedeConstruirUnaEstructuraProtossSiHayMohoEnSuAreaEnergizada() throws NoExisteEdificioCorrelativoException {
        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(10000);
        
        Mapa mapa = new Mapa();
        Pilon pilon = new Pilon(new Posicion(9,9), mapa);
        mapa.agregarConstruccion(pilon, mineral, gas);
        Criadero criadero = new Criadero(new Posicion(9,7), mapa);
        mapa.agregarConstruccion(criadero, mineral, gas);
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        Construccion acceso = new Acceso(new Posicion(9,8), mapa);
        assertFalse(mapa.agregarConstruccion(acceso, mineral, gas));
    }

    @Test
    public void elMohoSePuedeExpandirPorUnAreaEnergizada() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException {
        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(10000);
        
        Mapa mapa = new Mapa();
        Pilon pilon = new Pilon(new Posicion(9,9), mapa);
        mapa.agregarConstruccion(pilon, mineral, gas);
        Criadero criadero = new Criadero(new Posicion(9,20), mapa);
        mapa.agregarConstruccion(criadero, mineral, gas);
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();

        for(int i = 0; i < 40; i+=1){
            mapa.pasarTiempo();
        }
        Construccion extractor = new Extractor(new Posicion(9,10), new Volcan(new Posicion(9, 10)), mapa);
        assertTrue(mapa.agregarConstruccion(extractor, mineral, gas));
    }





    //Caso de uso 9

    /*@Test
    public void hayUnaConstruccionConDosPilonesSeDestruyeUnoDeEstosPeroLaEstructuraSigueFuncionando(){
        Mapa mapa = new Mapa();
        Pilon pilon1 = new Pilon(new Posicion(9,9), mapa);
        mapa.agregarConstruccion(pilon1);
        Pilon pilon2 = new Pilon(new Posicion(7,9), mapa);
        mapa.agregarConstruccion(pilon2);

        Protoss proto = new Protoss();

        Acceso acceso = new Acceso(new Posicion(8,9), mapa);
        mapa.agregarConstruccion(acceso);

        mapa.destruir(pilon1);

        assertTrue(proto.equals(acceso.transportar()));
    }*/
/*
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

*/
}
