package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Criadero;
import edu.fiuba.algo3.modelo.Message;
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
        Extractor extractor = new Extractor();
        extractor.pasarTiempo();
        assertEquals(0, extractor.obtenerGas());    
    }

    @Test
    public void extractorConUnZanganoGeneraDiezDeGas() 
    {
        Extractor extractor = new Extractor();
        extractor.agregarZangano();
        extractor.pasarTiempo();
        assertEquals(10, extractor.obtenerGas());    
    }

    
}
