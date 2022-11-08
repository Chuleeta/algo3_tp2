package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Criadero;
import edu.fiuba.algo3.modelo.Extractor;
import edu.fiuba.algo3.modelo.Message;
import edu.fiuba.algo3.modelo.Posicion;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExtractorTest {
    
    // Caso de uso 4
    @Test
    public void extractorSinZanganosNoGeneraGas() 
    {
        Extractor extractor = new Extractor(new Posicion(1, 1));
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        assertEquals(0, extractor.obtenerGas());    
    }


    @Test
    public void extractorConUnZanganoGeneraDiezDeGas() 
    {
        Extractor extractor = new Extractor(new Posicion(1, 1));
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.agregarZangano();
        extractor.pasarTiempo();
        assertEquals(10, extractor.obtenerGas());    
    }

    @Test
    public void extractorConDosZanganosGeneraVeinteDeGas() 
    {
        Extractor extractor = new Extractor(new Posicion(1, 1));
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
    public void extractorConTresZanganosGeneraTreintaDeGas() 
    {
        Extractor extractor = new Extractor(new Posicion(1, 1));
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
    public void extractorConCuatroZanganosGeneraTreintaDeGas() 
    {
        Extractor extractor = new Extractor(new Posicion(1, 1));
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
    public void extractorNoAgregaZanganosSiNoSeConstruyo() 
    {
        Extractor extractor = new Extractor(new Posicion(1, 1));
        extractor.pasarTiempo();
        extractor.pasarTiempo();
        extractor.agregarZangano();
        extractor.agregarZangano();
        extractor.agregarZangano();
        extractor.agregarZangano();
        extractor.pasarTiempo();
        assertEquals(0, extractor.obtenerGas());   
    }


}
