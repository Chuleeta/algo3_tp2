package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DragonTest {
    // caso 22
    @Test
    public void dragonNoGeneraDañoPorNoEstarConstruidoAun() throws MenaOcupadaException, RequerimientosInsuficientesException {

        NexoMineral nexo = new NexoMineral(new Posicion(1,1), new Mena(new Posicion(1,1)), new Mapa());
        Mineral mineral = new Mineral(125);
        GasVespeno gas = new GasVespeno(50);
        Dragon dragon = new Dragon(mineral, gas);

        // EL tiempo de construccion es 4, con un solo tiempo no esta construido
        dragon.pasarTiempo();
        dragon.atacarEdificio(nexo);
        //escudo completo
        assertTrue(nexo.tieneEscudoCompleto());
    }
    // caso 18
    @Test
    public void dragonAtaca10VecesACriaderoYSon200UnidadesDeAtaque() throws RequerimientosInsuficientesException {

        Criadero criadero = new Criadero(new Posicion(1,1), new Mapa());
        Mineral mineral = new Mineral(125);
        GasVespeno gas = new GasVespeno(50);
        Dragon dragon = new Dragon(mineral, gas);
        dragon.pasarTiempo();
        dragon.pasarTiempo();
        dragon.pasarTiempo();
        dragon.pasarTiempo();
        dragon.pasarTiempo();
        dragon.pasarTiempo();

        // Su unidad de ataque es de 20, con 10 ataques son 200 de daño

        for (int i = 0; i < 10; i++)
            dragon.atacarEdificio(criadero);

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
