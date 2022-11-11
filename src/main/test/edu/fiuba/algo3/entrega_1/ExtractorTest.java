package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;

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
    public void extractorSinZanganosNoGeneraGas() throws VolcanOcupadoException {
        Extractor extractor = new Extractor(new Posicion(1, 1), new Volcan(new Posicion(1, 1)), new Mapa());
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        assertEquals(0, extractor.obtenerGas());    
    }


    @Test
    public void extractorConUnZanganoGeneraDiezDeGas() throws VolcanOcupadoException {
        //given
        Extractor extractor = new Extractor(new Posicion(1, 1), new Volcan(new Posicion(1, 1)), new Mapa());
        GasVespeno mockGasVespeno = mock(GasVespeno.class);
        EstadoConstruido estado = mock(EstadoConstruido.class);
        doCallRealMethod().when(estado).estaConstruido();
        when(estado.puedeConstruirse(6,6)).thenReturn(true);
        doCallRealMethod().when(mockGasVespeno).colectar(10);
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();

        //when
        extractor.agregarZangano();
        extractor.pasarTiempo();

        //then
        assertEquals(10, extractor.obtenerGas());    
    }

    @Test
    public void extractorConDosZanganosGeneraVeinteDeGas() throws VolcanOcupadoException {
        Extractor extractor = new Extractor(new Posicion(1, 1), new Volcan(new Posicion(1, 1)), new Mapa());
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.agregarZangano();
        extractor.agregarZangano();
        extractor.pasarTiempo();
        assertEquals(20, extractor.obtenerGas());    
    }

    @Test
    public void extractorConTresZanganosGeneraTreintaDeGas() throws VolcanOcupadoException {
        Extractor extractor = new Extractor(new Posicion(1, 1), new Volcan(new Posicion(1, 1)), new Mapa());
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.agregarZangano();
        extractor.agregarZangano();
        extractor.agregarZangano();
        extractor.pasarTiempo();
        assertEquals(30, extractor.obtenerGas());   
    }

    @Test
    public void extractorConCuatroZanganosGeneraTreintaDeGas() throws VolcanOcupadoException {
        Extractor extractor = new Extractor(new Posicion(1, 1), new Volcan(new Posicion(1, 1)), new Mapa());
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.agregarZangano();
        extractor.agregarZangano();
        extractor.agregarZangano();
        extractor.agregarZangano();
        extractor.pasarTiempo();
        assertEquals(30, extractor.obtenerGas());   
    }

    @Test
    public void extractorNoAgregaZanganosSiNoSeConstruyo() throws VolcanOcupadoException {
        Extractor extractor = new Extractor(new Posicion(1, 1), new Volcan(new Posicion(1, 1)), new Mapa());
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.agregarZangano();
        extractor.agregarZangano();
        extractor.agregarZangano();
        extractor.agregarZangano();
        extractor.pasarTiempo();
        assertEquals(0, extractor.obtenerGas());   
    }

    //caso de uso 10
    @Test
    public void seRegeneraTodaLaVidaDespuesDeAlgunosTurnos() throws VolcanOcupadoException {
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
    public void seRegeneraLaVidaParcialmenteDespuesDeUnTurno() throws VolcanOcupadoException {
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
        GasVespeno gas = new GasVespeno(10000);
        Mapa mapa = new Mapa();
        Criadero criadero = new Criadero(new Posicion(2, 1), mapa);
        mapa.agregarConstruccion(criadero, mineral, gas);
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();

        Extractor extractor = new Extractor(new Posicion(1, 1), new Volcan(new Posicion(1, 1)), mapa);
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
