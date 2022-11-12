package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class HidraliscoTest {
    @Test
    public void HidraliscoDañaNexoMineral() throws MenaOcupadaException {

        NexoMineral nexo = new NexoMineral(new Posicion(1,1), new Mena(new Posicion(1,1)), new Mapa());

        // tiempo de construccion

        Hidralisco hidralisco = new Hidralisco();
        // Su unidad de ataque es de 4, con 50 ataques son 200 de daño
        for (int i = 0; i < 20; i++)
            hidralisco.atacarEdificio(nexo);

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
