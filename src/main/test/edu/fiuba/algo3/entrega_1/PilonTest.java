package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PilonTest {
    // Caso de uso 11
    @Test
    public void recibeDañoYElEscudoYSeRecuperaConElTiempoHastaEstarCompleto() {

        Pilon pilon = new Pilon(new Posicion(1, 1), new Mapa());
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.dañar(200);
        assertFalse(pilon.tieneEscudoCompleto());
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        assertTrue(pilon.tieneEscudoCompleto());

    }
}
