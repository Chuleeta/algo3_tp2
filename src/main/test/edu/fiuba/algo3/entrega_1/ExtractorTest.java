package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Edificios.Criadero;
import edu.fiuba.algo3.modelo.Edificios.Extractor;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.VolcanOcupadoException;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import edu.fiuba.algo3.modelo.Recursos.Volcan;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doCallRealMethod;

public class ExtractorTest {
    
    // Caso de uso 4
    @Test
    public void extractorSinZanganosNoGeneraGas() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException {
        Mapa mapa  = new Mapa();
        Criadero criadero = new Criadero(new Posicion(1 , 2), mapa);
        mapa.agregarConstruccion(criadero, new Mineral(10000), new GasVespeno(0));
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        Extractor extractor = new Extractor(new Posicion(1, 1), new Volcan(new Posicion(1, 1)), mapa);
        mapa.agregarConstruccion(extractor, new Mineral(10000), new GasVespeno(0));
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        assertEquals(0, extractor.obtenerGas());
    }


    @Test
    public void extractorConUnZanganoGeneraDiezDeGas() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException {
        //given
        Mapa mapa  = new Mapa();
        Criadero criadero = new Criadero(new Posicion(1 , 2), mapa);
        mapa.agregarConstruccion(criadero, new Mineral(10000), new GasVespeno(0));
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        Extractor extractor = new Extractor(new Posicion(1, 1), new Volcan(new Posicion(1, 1)), mapa);
        mapa.agregarConstruccion(extractor, new Mineral(10000), new GasVespeno(0));
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();

        //when
        extractor.agregarZangano();
        mapa.pasarTiempo();

        //then
        assertEquals(10, extractor.obtenerGas());    
    }

    @Test
    public void extractorConDosZanganosGeneraVeinteDeGas() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException {
        Mapa mapa  = new Mapa();
        Criadero criadero = new Criadero(new Posicion(1 , 2), mapa);
        mapa.agregarConstruccion(criadero, new Mineral(10000), new GasVespeno(0));
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        Extractor extractor = new Extractor(new Posicion(1, 1), new Volcan(new Posicion(1, 1)), mapa);
        mapa.agregarConstruccion(extractor, new Mineral(10000), new GasVespeno(0));
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        extractor.agregarZangano();
        extractor.agregarZangano();
        mapa.pasarTiempo();
        assertEquals(20, extractor.obtenerGas());    
    }

    @Test
    public void extractorConTresZanganosGeneraTreintaDeGas() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException {
        Mapa mapa  = new Mapa();
        Criadero criadero = new Criadero(new Posicion(1 , 2), mapa);
        mapa.agregarConstruccion(criadero, new Mineral(10000), new GasVespeno(0));
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        Extractor extractor = new Extractor(new Posicion(1, 1), new Volcan(new Posicion(1, 1)), mapa);
        mapa.agregarConstruccion(extractor, new Mineral(10000), new GasVespeno(0));
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        extractor.agregarZangano();
        extractor.agregarZangano();
        extractor.agregarZangano();
        mapa.pasarTiempo();
        assertEquals(30, extractor.obtenerGas());   
    }

    @Test
    public void extractorConCuatroZanganosGeneraTreintaDeGas() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException {
        Mapa mapa  = new Mapa();
        Criadero criadero = new Criadero(new Posicion(1 , 2), mapa);
        mapa.agregarConstruccion(criadero, new Mineral(10000), new GasVespeno(0));
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        Extractor extractor = new Extractor(new Posicion(1, 1), new Volcan(new Posicion(1, 1)), mapa);
        mapa.agregarConstruccion(extractor, new Mineral(10000), new GasVespeno(0));
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        extractor.agregarZangano();
        extractor.agregarZangano();
        extractor.agregarZangano();
        extractor.agregarZangano();
        mapa.pasarTiempo();
        assertEquals(30, extractor.obtenerGas());   
    }

    @Test
    public void extractorNoAgregaZanganosSiNoSeConstruyo() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException {
        Mapa mapa  = new Mapa();
        Criadero criadero = new Criadero(new Posicion(1 , 2), mapa);
        mapa.agregarConstruccion(criadero, new Mineral(10000), new GasVespeno(0));
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        Extractor extractor = new Extractor(new Posicion(1, 1), new Volcan(new Posicion(1, 1)), mapa);
        mapa.agregarConstruccion(extractor, new Mineral(10000), new GasVespeno(0));
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        extractor.agregarZangano();
        extractor.agregarZangano();
        extractor.agregarZangano();
        extractor.agregarZangano();
        mapa.pasarTiempo();
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
    public void unaVezAgotadoTodoElGasNoRecolectaMasDeEste() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException {
        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(0);
        Mapa mapa  = new Mapa();
        Criadero criadero = new Criadero(new Posicion(1 , 2), mapa);
        mapa.agregarConstruccion(criadero, new Mineral(10000), new GasVespeno(0));
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        Extractor extractor = new Extractor(new Posicion(1, 1), new Volcan(new Posicion(1, 1)), mapa);
        mapa.agregarConstruccion(extractor, new Mineral(10000), gas);
        extractor.agregarZangano();
        extractor.agregarZangano();
        extractor.agregarZangano();
        mapa.agregarConstruccion(extractor, mineral, gas);

        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();

        for(int i = 0; i < 550; i += 1){
            mapa.pasarTiempo();
        }

        assertEquals(5000, extractor.obtenerGas());    
    }
}
