package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AsimiladorTest {
    
    @Test
    public void asimiladorConsigueVeinteDeGasEnUnTurno() 
    {
        Asimilador asimilador = new Asimilador(new Posicion(1, 1), new Mapa());
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        assertEquals(20, asimilador.obtenerGas());    
    }

    @Test
    public void asimiladorEmpiezaSinGasRecolectado() 
    {
        Asimilador asimilador = new Asimilador(new Posicion(1, 1), new Mapa());
        assertEquals(0, asimilador.obtenerGas());    
    }

    @Test
    public void asimiladorConsigueVeinteDeGasEnCadaTurno() 
    {
        Asimilador asimilador = new Asimilador(new Posicion(1, 1), new Mapa());
        asimilador.pasarTiempo();
        assertEquals(0, asimilador.obtenerGas());
        asimilador.pasarTiempo();
        assertEquals(0, asimilador.obtenerGas());
        asimilador.pasarTiempo();
        assertEquals(0, asimilador.obtenerGas());
        asimilador.pasarTiempo();
        assertEquals(0, asimilador.obtenerGas());
        asimilador.pasarTiempo();
        assertEquals(0, asimilador.obtenerGas());
        asimilador.pasarTiempo();
        assertEquals(0, asimilador.obtenerGas());
        asimilador.pasarTiempo();
        assertEquals(20, asimilador.obtenerGas());
        asimilador.pasarTiempo();
        assertEquals(40, asimilador.obtenerGas()); 
        asimilador.pasarTiempo();
        assertEquals(60, asimilador.obtenerGas()); 
        asimilador.pasarTiempo();
        assertEquals(80, asimilador.obtenerGas());     
    }

    // Caso de uso 11
    @Test
    public void recibeDañoYElEscudoYSeRecuperaConElTiempoHastaEstarCompleto() {

        Asimilador asimilador = new Asimilador(new Posicion(1, 1), new Mapa());
        asimilador.dañar(450);
        assertFalse(asimilador.tieneEscudoCompleto());
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        assertTrue(asimilador.tieneEscudoCompleto());

    }

}
