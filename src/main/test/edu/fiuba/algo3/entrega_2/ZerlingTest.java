package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ZerlingTest {

    @Test
    public void zerlingDañaNexoMineral50vecesYSon200UnidadesDeAtaque() throws MenaOcupadaException {

        NexoMineral nexo = new NexoMineral(new Posicion(1,1), new Mena(new Posicion(1,1)), new Mapa());

        // tiempo de construccion

        Zerling zerling = new Zerling();
        // Su unidad de ataque es de 10, con 20 ataques son 200 de daño
        for (int i = 0; i < 50; i++)
            zerling.atacarEdificio(nexo);

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
