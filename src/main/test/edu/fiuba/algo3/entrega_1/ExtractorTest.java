package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Edificios.Criadero;
import edu.fiuba.algo3.modelo.Edificios.Extractor;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Exceptions.VolcanOcupadoException;
import edu.fiuba.algo3.modelo.Individuos.Zangano;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import edu.fiuba.algo3.modelo.Recursos.Volcan;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class ExtractorTest {
    
    // Caso de uso 4
    @Test
    public void extractorSinZanganosNoGeneraGas() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Criadero criadero = new Criadero(new Posicion(1 , 2), mapa, jugador);
        jugador.agregarConstruccion(criadero, new Mineral(10000), new GasVespeno(0));
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        Extractor extractor = new Extractor(new Posicion(2, 1), new Volcan(new Posicion(2, 1)), mapa, jugador);
        jugador.agregarConstruccion(extractor, new Mineral(10000), new GasVespeno(0));
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        assertEquals(0, extractor.obtenerGas());
    }


    @Test
    public void extractorConUnZanganoGeneraDiezDeGas() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException, RequerimientosInsuficientesException {
        //given
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Criadero criadero = new Criadero(new Posicion(1 , 2), mapa, jugador);
        jugador.agregarConstruccion(criadero, new Mineral(10000), new GasVespeno(0));
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        Extractor extractor = new Extractor(new Posicion(2, 2), new Volcan(new Posicion(2, 2)), mapa, jugador);
        jugador.agregarConstruccion(extractor, new Mineral(10000), new GasVespeno(0));
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        Zangano zangano = new Zangano(new Mineral(25));
        zangano.pasarTiempo();
        //when
        extractor.agregarZangano(zangano);
        jugador.pasarTiempo();

        //then
        assertEquals(10, extractor.obtenerGas());    
    }

    @Test
    public void extractorConDosZanganosGeneraVeinteDeGas() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException, RequerimientosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Criadero criadero = new Criadero(new Posicion(1 , 2), mapa, jugador);
        jugador.agregarConstruccion(criadero, new Mineral(10000), new GasVespeno(0));
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        Extractor extractor = new Extractor(new Posicion(2, 1), new Volcan(new Posicion(2, 1)), mapa, jugador);
        jugador.agregarConstruccion(extractor, new Mineral(10000), new GasVespeno(0));
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        Zangano zangano = new Zangano(new Mineral(25));
        zangano.pasarTiempo();
        extractor.agregarZangano(zangano);
        extractor.agregarZangano(zangano);
        jugador.pasarTiempo();
        assertEquals(20, extractor.obtenerGas());    
    }

    @Test
    public void extractorConTresZanganosGeneraTreintaDeGas() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException, RequerimientosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Criadero criadero = new Criadero(new Posicion(1 , 2), mapa, jugador);
        jugador.agregarConstruccion(criadero, new Mineral(10000), new GasVespeno(0));
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        Extractor extractor = new Extractor(new Posicion(2, 1), new Volcan(new Posicion(2, 1)), mapa, jugador);
        jugador.agregarConstruccion(extractor, new Mineral(10000), new GasVespeno(0));
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        Zangano zangano = new Zangano(new Mineral(25));
        zangano.pasarTiempo();
        extractor.agregarZangano(zangano);
        extractor.agregarZangano(zangano);
        extractor.agregarZangano(zangano);
        jugador.pasarTiempo();
        assertEquals(30, extractor.obtenerGas());   
    }

    @Test
    public void extractorConCuatroZanganosGeneraTreintaDeGas() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException, RequerimientosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Criadero criadero = new Criadero(new Posicion(1 , 2), mapa, jugador);
        jugador.agregarConstruccion(criadero, new Mineral(10000), new GasVespeno(0));
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        Extractor extractor = new Extractor(new Posicion(2, 1), new Volcan(new Posicion(2, 1)), mapa, jugador);
        jugador.agregarConstruccion(extractor, new Mineral(10000), new GasVespeno(0));
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        Zangano zangano = new Zangano(new Mineral(25));
        zangano.pasarTiempo();
        extractor.agregarZangano(zangano);
        extractor.agregarZangano(zangano);
        extractor.agregarZangano(zangano);
        extractor.agregarZangano(zangano);
        jugador.pasarTiempo();
        assertEquals(30, extractor.obtenerGas());   
    }

    @Test
    public void extractorNoAgregaZanganosSiNoSeConstruyo() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException, RequerimientosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Criadero criadero = new Criadero(new Posicion(1 , 2), mapa, jugador);
        jugador.agregarConstruccion(criadero, new Mineral(10000), new GasVespeno(0));
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        Extractor extractor = new Extractor(new Posicion(2, 1), new Volcan(new Posicion(2, 1)), mapa, jugador);
        jugador.agregarConstruccion(extractor, new Mineral(10000), new GasVespeno(0));
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        Zangano zangano = new Zangano(new Mineral(25));
        zangano.pasarTiempo();
        extractor.agregarZangano(zangano);
        extractor.agregarZangano(zangano);
        extractor.agregarZangano(zangano);
        extractor.agregarZangano(zangano);
        jugador.pasarTiempo();
        assertEquals(0, extractor.obtenerGas());   
    }

    //caso de uso 10
    @Test
    public void seRegeneraTodaLaVidaDespuesDeAlgunosTurnos() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException {
        //given
        Extractor extractor = new Extractor(new Posicion(1, 1), new Volcan(new Posicion(1, 1)), new Mapa());
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();

        //when
        extractor.dañar(200);
        extractor.pasarTiempo();
        extractor.pasarTiempo();

        //then
        assertTrue(extractor.tieneVidaCompleta());
    }

    @Test
    public void seRegeneraLaVidaParcialmenteDespuesDeUnTurno() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException {
        //given
        Extractor extractor = new Extractor(new Posicion(1, 1), new Volcan(new Posicion(1, 1)), new Mapa());
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();

        //when
        extractor.dañar(200);
        extractor.pasarTiempo();

        //then
        assertFalse(extractor.tieneVidaCompleta());
    }

    // Caso de uso 15

    @Test
    public void unaVezAgotadoTodoElGasNoRecolectaMasDeEste() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException, RequerimientosInsuficientesException {
        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(0);
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Criadero criadero = new Criadero(new Posicion(1 , 2), mapa, jugador);
        jugador.agregarConstruccion(criadero, new Mineral(10000), new GasVespeno(0));
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        Extractor extractor = new Extractor(new Posicion(2, 1), new Volcan(new Posicion(2, 1)), mapa, jugador);
        jugador.agregarConstruccion(extractor, new Mineral(10000), gas);
        Zangano zangano = new Zangano(new Mineral(25));
        zangano.pasarTiempo();
        extractor.agregarZangano(zangano);
        extractor.agregarZangano(zangano);
        extractor.agregarZangano(zangano);
        jugador.agregarConstruccion(extractor, mineral, gas);

        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();

        for(int i = 0; i < 550; i += 1){
            jugador.pasarTiempo();
        }

        assertEquals(5000, extractor.obtenerGas());    
    }
}
