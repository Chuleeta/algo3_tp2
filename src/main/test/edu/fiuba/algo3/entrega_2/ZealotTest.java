package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ZealotTest {
    // caso 22
    @Test
    public void zealotNoGeneraDañoPorNoEstarConstruidoAun() throws MenaOcupadaException, RequerimientosInsuficientesException {

        NexoMineral nexo = new NexoMineral(new Posicion(1,1), new Mena(new Posicion(1,1)), new Mapa());
        Mineral mineral = new Mineral(100);
        Zealot zealot = new Zealot(mineral);

        // EL tiempo de construccion es 4, con un solo tiempo no esta construido
        zealot.pasarTiempo();
        zealot.atacarEdificio(nexo);
        //escudo completo
        assertTrue(nexo.tieneEscudoCompleto());
    }
    // caso 18
    @Test
    public void ZealotAtacaCriadero25VecesYGenera200UnidadesDeAtaque() throws RequerimientosInsuficientesException {

        Criadero criadero = new Criadero(new Posicion(1,1), new Mapa());
        Mineral mineral = new Mineral(100);
        Zealot zealot = new Zealot(mineral);
        zealot.pasarTiempo();
        zealot.pasarTiempo();
        zealot.pasarTiempo();
        zealot.pasarTiempo();
        // Su unidad de ataque es de 8, con 25 ataques son 200 de daño
        for (int i = 0; i < 25; i++)
            zealot.atacarEdificio(criadero);

        //vida incompleta
        assertFalse(criadero.tieneVidaCompleta());
        criadero.pasarTiempo();
        //sigue incompleta
        assertFalse(criadero.tieneVidaCompleta());
        criadero.pasarTiempo();
        //vida completa
        assertTrue(criadero.tieneVidaCompleta());
    }
}
