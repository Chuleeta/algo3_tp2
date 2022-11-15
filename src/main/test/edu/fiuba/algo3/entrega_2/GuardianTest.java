package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Edificios.NexoMineral;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GuardianTest {
    // caso 22
    @Test
    public void guardianNoGeneraDañoPorNoEstarConstruidoAun() throws MenaOcupadaException, RequerimientosInsuficientesException {

        NexoMineral nexo = new NexoMineral(new Posicion(1,1), new Mena(new Posicion(1,1)), new Mapa());
        Mineral mineral = new Mineral(50);
        GasVespeno gas = new GasVespeno(100);
        Guardian guardian = new Guardian(mineral, gas);

        // EL tiempo de construccion es 4, con un solo tiempo no esta construido
        guardian.pasarTiempo();
        guardian.atacarEdificio(nexo);
        //escudo completo
        assertTrue(nexo.tieneEscudoCompleto());
    }

    // caso 18
    @Test
    public void guardianAtacaNexoMineral8vecesYDaña200unidades() throws MenaOcupadaException, RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException {
        Mapa mapa = new Mapa();
        NexoMineral nexo = new NexoMineral(new Posicion(1,1), new Mena(new Posicion(1,1)), mapa);
        Mineral mineral = new Mineral(100);
        GasVespeno gas = new GasVespeno(100);
        mapa.agregarConstruccion(nexo, mineral, gas);
        Guardian guardian = new Guardian(mineral, gas);
        guardian.pasarTiempo();
        guardian.pasarTiempo();
        guardian.pasarTiempo();
        guardian.pasarTiempo();
        // Su unidad de ataque es de 25, con 8 ataques son 200 de daño
        for (int i = 0; i < 8; i++)
            guardian.atacarEdificio(nexo);

        // SE TIENE QUE TERMINAR DE CONSTRUIR PARA QUE SE REGENERE
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();

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
