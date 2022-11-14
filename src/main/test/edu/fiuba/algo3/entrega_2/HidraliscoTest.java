package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Edificios.NexoMineral;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class HidraliscoTest {

    // caso 22
    @Test
    public void hidraliscoNoGeneraDañoPorNoEstarConstruidoAun() throws MenaOcupadaException, RequerimientosInsuficientesException {

        NexoMineral nexo = new NexoMineral(new Posicion(1,1), new Mena(new Posicion(1,1)), new Mapa());
        Mineral mineral = new Mineral(75);
        GasVespeno gas = new GasVespeno(25);
        Hidralisco hidralisco = new Hidralisco(mineral, gas);

        // EL tiempo de construccion es 4, con un solo tiempo no esta construido
        hidralisco.pasarTiempo();
        hidralisco.atacarEdificio(nexo);
        //escudo completo
        assertTrue(nexo.tieneEscudoCompleto());
    }

    // caso 18
    @Test
    public void HidraliscoAtacaNexoMineral20VecesYGenera200UnidadesDeDaño() throws MenaOcupadaException, RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException {

        NexoMineral nexo = new NexoMineral(new Posicion(1,1), new Mena(new Posicion(1,1)), new Mapa());

        // tiempo de construccion
        Mineral mineral = new Mineral(75);
        GasVespeno gas = new GasVespeno(25);
        Hidralisco hidralisco = new Hidralisco(mineral, gas);
        hidralisco.pasarTiempo();
        hidralisco.pasarTiempo();
        hidralisco.pasarTiempo();
        hidralisco.pasarTiempo();
        // Su unidad de ataque es de 10, con 20 ataques son 200 de daño
        for (int i = 0; i < 20; i++)
            hidralisco.atacarEdificio(nexo);

        // SE TIENE QUE TERMINAR DE CONSTRUIR PARA QUE SE REGENERE
        nexo.pasarTiempo();
        nexo.pasarTiempo();
        nexo.pasarTiempo();
        nexo.pasarTiempo();

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
