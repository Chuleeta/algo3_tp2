package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AsimiladorTest {
    
    @Test
    public void asimiladorConsigueVeinteDeGasEnUnTurno() throws VolcanOcupadoException {
        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(10000);
        Mapa mapa = new Mapa();
        Asimilador asimilador = new Asimilador(new Posicion(1, 1), new Volcan(new Posicion(1, 1)), mapa);
        mapa.agregarConstruccion(asimilador, mineral, gas);

        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();

        mapa.pasarTiempo();
 
        assertEquals(20, asimilador.obtenerGas());
    }

    @Test
    public void asimiladorEmpiezaSinGasRecolectado() throws VolcanOcupadoException {
        Asimilador asimilador = new Asimilador(new Posicion(1, 1), new Volcan(new Posicion(1, 1)), new Mapa());
        assertEquals(0, asimilador.obtenerGas());    
    }

    @Test
    public void asimiladorConsigueVeinteDeGasEnCadaTurno() throws VolcanOcupadoException {
        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(10000);
        Mapa mapa = new Mapa();
        Asimilador asimilador = new Asimilador(new Posicion(1, 1), new Volcan(new Posicion(1, 1)), mapa);
        mapa.agregarConstruccion(asimilador, mineral, gas);

        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();

        mapa.pasarTiempo();
        assertEquals(20, asimilador.obtenerGas());
        mapa.pasarTiempo();
        assertEquals(40, asimilador.obtenerGas());
        mapa.pasarTiempo();
        assertEquals(60, asimilador.obtenerGas());
        mapa.pasarTiempo();
        assertEquals(80, asimilador.obtenerGas());
        mapa.pasarTiempo();
        assertEquals(100, asimilador.obtenerGas());
    }

    // Caso de uso 11
    @Test
    public void recibeDañoYElEscudoYSeRecuperaConElTiempoHastaEstarCompleto() throws VolcanOcupadoException {
        Asimilador asimilador = new Asimilador(new Posicion(1, 1), new Volcan(new Posicion(1, 1)), new Mapa());
        asimilador.dañar(450);
        assertFalse(asimilador.tieneEscudoCompleto());
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        assertTrue(asimilador.tieneEscudoCompleto());
    }

    // Caso de uso 12
    @Test
    public void recibeDañoElEscudoYSeRecuperaPeroLaVidaNo() throws VolcanOcupadoException {
        Asimilador asimilador = new Asimilador(new Posicion(1, 1), new Volcan(new Posicion(1, 1)), new Mapa());
        asimilador.dañar(500);
        assertFalse(asimilador.tieneEscudoCompleto());
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        assertTrue(asimilador.tieneEscudoCompleto());
        assertFalse(asimilador.tieneVidaCompleta());
    }

}
