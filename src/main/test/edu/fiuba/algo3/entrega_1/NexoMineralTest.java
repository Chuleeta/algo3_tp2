package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Edificios.NexoMineral;
import edu.fiuba.algo3.modelo.Exceptions.MenaOcupadaException;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mena;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NexoMineralTest {
    //Caso de uso 7
    @Test
    public void SeMinaUnMineralConUnNexoMineralYLoMinaExitosamente() throws MenaOcupadaException, NoExisteEdificioCorrelativoException {
        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(10000);
        Mapa mapa = new Mapa();
        NexoMineral nexito = new NexoMineral(new Posicion(1, 1), new Mena(new Posicion(1, 1)), mapa);
        mapa.agregarConstruccion(nexito, mineral, gas);
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        assertEquals(mineral.getCantidad(), 10050); 
    }

    @Test
    public void nexoMineralNoConstruidoNoMina() throws MenaOcupadaException, NoExisteEdificioCorrelativoException {
        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(10000);
        Mapa mapa = new Mapa();
        NexoMineral nexito = new NexoMineral(new Posicion(1, 1), new Mena(new Posicion(1, 1)), mapa);
        mapa.agregarConstruccion(nexito, mineral, gas);
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();

        assertEquals(mineral.getCantidad(), 9950); 
    }

    @Test
    public void nexoMineralNoMinaMenaAgotada() throws MenaOcupadaException, NoExisteEdificioCorrelativoException {
        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(10000);
        Mapa mapa = new Mapa();
        NexoMineral nexito = new NexoMineral(new Posicion(1, 1), new Mena(new Posicion(1, 1)), mapa);
        mapa.agregarConstruccion(nexito, mineral, gas);
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        for(int i = 0; i<60; i+=1){
            mapa.pasarTiempo();
        }
        assertEquals(mineral.getCantidad(), 11950); //no tenemos la menor idea de como se mina ni cuanto devuelve
    }

    // Caso de uso 11
    @Test
    public void recibeDañoYElEscudoYSeRecuperaConElTiempoHastaEstarCompleto() throws MenaOcupadaException, NoExisteEdificioCorrelativoException {

        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(10000);
        Mapa mapa = new Mapa();
        NexoMineral nexito = new NexoMineral(new Posicion(1, 1), new Mena(new Posicion(1, 1)), mapa);
        mapa.agregarConstruccion(nexito, mineral, gas);
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        nexito.dañar(200);
        assertFalse(nexito.tieneEscudoCompleto());
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        assertTrue(nexito.tieneEscudoCompleto());
    }

    // Caso de uso 12
    @Test
    public void recibeDañoElEscudoYSeRecuperaPeroLaVidaNo() throws MenaOcupadaException, NoExisteEdificioCorrelativoException {

        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(10000);
        Mapa mapa = new Mapa();
        NexoMineral nexito = new NexoMineral(new Posicion(1, 1), new Mena(new Posicion(1, 1)), mapa);
        mapa.agregarConstruccion(nexito, mineral, gas);
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        nexito.dañar(300);
        assertFalse(nexito.tieneEscudoCompleto());
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        assertTrue(nexito.tieneEscudoCompleto());
        assertFalse(nexito.tieneVidaCompleta());
    }
}
