package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Edificios.NexoMineral;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ZerlingTest {

    // caso 22
    @Test
    public void zerlingNoGeneraDañoPorNoEstarConstruidoAun() throws MenaOcupadaException, RequerimientosInsuficientesException {

        NexoMineral nexo = new NexoMineral(new Posicion(1,1), new Mena(new Posicion(1,1)), new Mapa());

        Mineral mineral = new Mineral(25);

        Zerling zerling = new Zerling(mineral);
        // EL tiempo de construccion es 2, con un solo tiempo no esta construido.
        zerling.pasarTiempo();
        zerling.atacarEdificio(nexo);

        //escudo completo
        assertTrue(nexo.tieneEscudoCompleto());

    }
    // caso 18
    @Test
    public void zerlingDañaNexoMineral50vecesYSon200UnidadesDeAtaque() throws MenaOcupadaException, RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException {
        Mapa mapa = new Mapa();
        NexoMineral nexo = new NexoMineral(new Posicion(1,1), new Mena(new Posicion(1,1)), mapa);

        // tiempo de construccion
        Mineral mineral = new Mineral(75);
        GasVespeno gas = new GasVespeno(0);
        mapa.agregarConstruccion(nexo, mineral, gas);

        Zerling zerling = new Zerling(mineral);
        zerling.pasarTiempo();
        zerling.pasarTiempo();
        // Su unidad de ataque es de 10, con 20 ataques son 200 de daño
        for (int i = 0; i < 50; i++)
            zerling.atacarEdificio(nexo);


        //SE CONSTRUYE EL NEXO PARA QUE SE PUEDA REGENERAR EL ESCUDO
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        //escudo dañado
        assertFalse(nexo.tieneEscudoCompleto());
        mapa.pasarTiempo();
        //sigue dañado
        assertFalse(nexo.tieneEscudoCompleto());
        mapa.pasarTiempo();
        //escudo completo
        assertTrue(nexo.tieneEscudoCompleto());

    }

}
