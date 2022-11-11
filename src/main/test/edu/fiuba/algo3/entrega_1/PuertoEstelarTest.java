package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PuertoEstelarTest {

    //Caso de uso 11
    @Test
    public void recibeDañoYElEscudoYSeRecuperaConElTiempoHastaEstarCompleto() throws NoExisteEdificioCorrelativoException {
        PuertoEstelar puertoEstelar = new PuertoEstelar(new Posicion(1, 1), new Mapa());
        puertoEstelar.pasarTiempo();
        puertoEstelar.pasarTiempo();
        puertoEstelar.pasarTiempo();
        puertoEstelar.pasarTiempo();
        puertoEstelar.dañar(200);
        assertFalse(puertoEstelar.tieneEscudoCompleto());
        puertoEstelar.pasarTiempo();
        puertoEstelar.pasarTiempo();
        assertTrue(puertoEstelar.tieneEscudoCompleto());
    }

    //Caso de uso 12
    @Test
    public void recibeDañoElEscudoYSeRecuperaPeroLaVidaNo() throws NoExisteEdificioCorrelativoException {
        Mapa mapa = new Mapa();
        Acceso acceso = new Acceso(new Posicion(0,0), mapa);
        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(10000);
        mapa.agregarConstruccion(acceso, mineral, gas);
        PuertoEstelar puertoEstelar = new PuertoEstelar(new Posicion(1, 1), mapa);
        puertoEstelar.pasarTiempo();
        puertoEstelar.pasarTiempo();
        puertoEstelar.pasarTiempo();
        puertoEstelar.pasarTiempo();
        puertoEstelar.dañar(700);
        assertFalse(puertoEstelar.tieneEscudoCompleto());
        puertoEstelar.pasarTiempo();
        puertoEstelar.pasarTiempo();
        puertoEstelar.pasarTiempo();
        puertoEstelar.pasarTiempo();
        puertoEstelar.pasarTiempo();
        puertoEstelar.pasarTiempo();
        assertTrue(puertoEstelar.tieneEscudoCompleto());
        assertFalse(puertoEstelar.tieneVidaCompleta());
    }
    @Test
    public void noSePuedeConstruirPuertoEstelarSiNoHayAcceso() throws NoExisteEdificioCorrelativoException {
        //given
        PuertoEstelar puerto = new PuertoEstelar(new Posicion(1,1), new Mapa());
        puerto.pasarTiempo();
        puerto.pasarTiempo();
        puerto.pasarTiempo();
        puerto.pasarTiempo();
        puerto.pasarTiempo();
        puerto.pasarTiempo();
        puerto.pasarTiempo();
        puerto.pasarTiempo();
        puerto.pasarTiempo();
        assertThrows(NoExisteEdificioCorrelativoException.class, () ->{ puerto.pasarTiempo();});
    }

}
