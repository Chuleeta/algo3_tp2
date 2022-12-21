package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Edificios.Acceso;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;

import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccesoTest {

    //Caso de uso 12
    @Test
    public void recibeDañoYElEscudoYSeRecuperaConElTiempoHastaEstarCompleto() throws NoExisteEdificioCorrelativoException {
        Acceso acceso = new Acceso(new Posicion(1, 1), new Mapa());
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.dañar(500);
        assertFalse(acceso.tieneEscudoCompleto());
        for (int i = 0; i < 100; i++)
            acceso.pasarTiempo();
        assertTrue(acceso.tieneEscudoCompleto());
    }

    //Caso de uso 12
    @Test
    public void recibeDañoElEscudoYSeRecuperaPeroLaVidaNo() throws NoExisteEdificioCorrelativoException {
        Acceso acceso = new Acceso(new Posicion(1, 1), new Mapa());
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.dañar(505);
        assertFalse(acceso.tieneEscudoCompleto());
        for (int i = 0; i < 100; i++)
            acceso.pasarTiempo();

        assertTrue(acceso.tieneEscudoCompleto());
        assertFalse(acceso.tieneVidaCompleta());
    }

}
