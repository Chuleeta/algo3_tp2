package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Edificios.Asimilador;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.VolcanOcupadoException;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import edu.fiuba.algo3.modelo.Recursos.Volcan;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AsimiladorTest {
    
    @Test
    public void asimiladorConsigueVeinteDeGasEnUnTurno() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException {
        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(0);
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
        Mapa mapa = new Mapa();
        GasVespeno gas = new GasVespeno(0);
        Asimilador asimilador = new Asimilador(new Posicion(1, 1), new Volcan(new Posicion(1, 1)), mapa);
        mapa.agregarConstruccion(asimilador, new Mineral(10000), gas);
        assertEquals(0, asimilador.obtenerGas());    
    }

    @Test
    public void asimiladorConsigueVeinteDeGasEnCadaTurno() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException {
        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(0);
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
    public void recibeDañoYElEscudoYSeRecuperaConElTiempoHastaEstarCompleto() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException {
        Mapa mapa = new Mapa();
        Asimilador asimilador = new Asimilador(new Posicion(1, 1), new Volcan(new Posicion(1, 1)), mapa);
        mapa.agregarConstruccion(asimilador, new Mineral(10000), new GasVespeno(10000));
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        asimilador.dañar(450);
        assertFalse(asimilador.tieneEscudoCompleto());

        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        assertTrue(asimilador.tieneEscudoCompleto());
    }

    // Caso de uso 12
    @Test
    public void recibeDañoElEscudoYSeRecuperaPeroLaVidaNo() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException {
        Mapa mapa = new Mapa();
        Asimilador asimilador = new Asimilador(new Posicion(1, 1), new Volcan(new Posicion(1, 1)), mapa);
        mapa.agregarConstruccion(asimilador, new Mineral(10000), new GasVespeno(10000));
        asimilador.dañar(500);
        assertFalse(asimilador.tieneEscudoCompleto());
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();

        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        assertTrue(asimilador.tieneEscudoCompleto());
        assertFalse(asimilador.tieneVidaCompleta());
    }

}
