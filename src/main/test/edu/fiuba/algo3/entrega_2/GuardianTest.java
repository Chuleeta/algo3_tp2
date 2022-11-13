package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GuardianTest {
    @Test
    public void guardianAtacaNexoMineral8vecesYDaña200unidades() throws MenaOcupadaException {
        NexoMineral nexo = new NexoMineral(new Posicion(1,1), new Mena(new Posicion(1,1)), new Mapa());

        Guardian guardian = new Guardian();
        // Su unidad de ataque es de 25, con 8 ataques son 200 de daño
        for (int i = 0; i < 8; i++)
            guardian.atacarEdificio(nexo);

        //escudo dañado
        assertFalse(nexo.tieneEscudoCompleto());
        nexo.pasarTiempo();
        //sigue dañado
        assertFalse(nexo.tieneEscudoCompleto());
        nexo.pasarTiempo();
        //escudo completo
        assertTrue(nexo.tieneEscudoCompleto());

    }
}
