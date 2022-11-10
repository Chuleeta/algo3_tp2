package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Pilon;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.PuertoEstelar;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PuertoEstelarTest {

    @Test
    public void recibeDañoYElEscudoYSeRecuperaConElTiempoHastaEstarCompleto() {
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

}
