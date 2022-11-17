package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Edificios.NexoMineral;
import edu.fiuba.algo3.modelo.Exceptions.MenaOcupadaException;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Individuos.Guardian;
import edu.fiuba.algo3.modelo.Individuos.Scout;
import edu.fiuba.algo3.modelo.Individuos.Zerling;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mena;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

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
        Guardian guardian = new Guardian(mineral, gas, new Posicion(8, 8), new Mapa());

        // EL tiempo de construccion es 4, con un solo tiempo no esta construido
        guardian.pasarTiempo();
        guardian.atacar(nexo);
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
        Guardian guardian = new Guardian(mineral, gas, new Posicion(8, 8), new Mapa());
        guardian.pasarTiempo();
        guardian.pasarTiempo();
        guardian.pasarTiempo();
        guardian.pasarTiempo();
        // Su unidad de ataque es de 25, con 8 ataques son 200 de daño
        for (int i = 0; i < 8; i++)
            guardian.atacar(nexo);

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

    // caso 23
    @Test
    public void guardianNoAtacaNexoMineralPorqueEstaFueraDeRango() throws MenaOcupadaException, RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException {
        Mapa mapa = new Mapa();
        NexoMineral nexo = new NexoMineral(new Posicion(1,1), new Mena(new Posicion(1,1)), mapa);
        Mineral mineral = new Mineral(100);
        GasVespeno gas = new GasVespeno(100);
        mapa.agregarConstruccion(nexo, mineral, gas);
        Guardian guardian = new Guardian(mineral, gas, new Posicion(1, 12), new Mapa());
        guardian.pasarTiempo();
        guardian.pasarTiempo();
        guardian.pasarTiempo();
        guardian.pasarTiempo();
        // Su unidad de ataque es de 25, con 8 ataques son 200 de daño
        for (int i = 0; i < 8; i++)
            guardian.atacar(nexo);

        //escudo completo porque esta fuera de rango
        assertTrue(nexo.tieneEscudoCompleto());

    }

    @Test
    public void guardianNoPuedeAtacarUnidadVoladoraPeroSiTerrestre() throws RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException {

        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(1000);
        Zerling zerling = new Zerling(mineral, new Posicion(1,2), new Mapa());
        Guardian guardian = new Guardian(mineral, gas, new Posicion(1,2), new Mapa());
        Scout scout = new Scout(mineral, gas, new Posicion(1,2), new Mapa());
        guardian.pasarTiempo();
        guardian.pasarTiempo();
        guardian.pasarTiempo();
        guardian.pasarTiempo();
        guardian.pasarTiempo();
        guardian.pasarTiempo();
        assertTrue(zerling.tieneVidaCompleta());
        assertTrue(scout.tieneVidaCompleta());
        for (int i = 0; i < 10; i++)
        {
            guardian.atacar(zerling);
            guardian.atacar(scout);
        }


        assertFalse(zerling.tieneVidaCompleta());
        assertTrue(scout.tieneVidaCompleta());
    }

    @Test
    public void guardianPuedeMoverseAZonaEspacialYAOtroLado() throws RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException {

        Mapa mapa = new Mapa();
        mapa.agregarAreaEspacial(new AreaEspacial(0, 0, 10, 10));
        GasVespeno gas = new GasVespeno(10000);
        Mineral mineral = new Mineral(10000);
        Guardian guardian = new Guardian(mineral, gas, new Posicion(11,11), mapa);
        guardian.pasarTiempo();
        guardian.pasarTiempo();
        guardian.pasarTiempo();
        guardian.pasarTiempo();
        guardian.pasarTiempo();
        guardian.pasarTiempo();
        assertTrue(guardian.mover(new Posicion(12, 12)));
        assertTrue(guardian.mover(new Posicion(3, 3)));
    }
}

