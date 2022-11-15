package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Edificios.Acceso;
import edu.fiuba.algo3.modelo.Edificios.Pilon;
import edu.fiuba.algo3.modelo.Edificios.PuertoEstelar;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PuertoEstelarTest {

    //Caso de uso 11
    @Test
    public void recibeDañoYElEscudoYSeRecuperaConElTiempoHastaEstarCompleto() throws NoExisteEdificioCorrelativoException {
        Mapa mapa = new Mapa();
        Pilon pilon = new Pilon(new Posicion(1, 3), mapa);
        mapa.agregarConstruccion(pilon, new Mineral(10000), new GasVespeno(10000));
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        Acceso acceso = new Acceso(new Posicion(1, 2), mapa);
        mapa.agregarConstruccion(acceso, new Mineral(10000), new GasVespeno(10000));
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        PuertoEstelar puertoEstelar = new PuertoEstelar(new Posicion(1, 1), mapa);
        puertoEstelar.pasarTiempo();
        puertoEstelar.pasarTiempo();
        puertoEstelar.pasarTiempo();
        puertoEstelar.pasarTiempo();
        puertoEstelar.pasarTiempo();
        puertoEstelar.pasarTiempo();
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
        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(10000);
        Pilon pilon = new Pilon(new Posicion(1, 1), mapa);
        mapa.agregarConstruccion(pilon, mineral, gas);

        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        Acceso acceso = new Acceso(new Posicion(0,0), mapa);
        mapa.agregarConstruccion(acceso, mineral, gas);

        PuertoEstelar puertoEstelar = new PuertoEstelar(new Posicion(2, 2), mapa);
        mapa.agregarConstruccion(puertoEstelar, mineral, gas);
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        puertoEstelar.dañar(700);
        assertFalse(puertoEstelar.tieneEscudoCompleto());
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();

        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
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
