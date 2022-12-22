package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.Jugador;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.AreaEspacial;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Edificios.NexoMineral;
import edu.fiuba.algo3.modelo.Exceptions.MenaOcupadaException;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Individuos.Devorador;
import edu.fiuba.algo3.modelo.Individuos.Scout;
import edu.fiuba.algo3.modelo.Individuos.Zerling;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mena;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DevoradorTest {
    // Caso de uso 27
    @Test
    public void devoradorNoGeneraDañoPorNoEstarConstruidoAun() throws MenaOcupadaException, RequerimientosInsuficientesException {

        NexoMineral nexo = new NexoMineral(new Posicion(1,1), new Mena(new Posicion(1,1)), new Mapa());
        Mineral mineral = new Mineral(150);
        GasVespeno gas = new GasVespeno(50);
        Devorador devorador = new Devorador(mineral, gas, new Posicion(8, 8), new Mapa());

        // EL tiempo de construccion es 4, con un solo tiempo no esta construido
        devorador.pasarTiempo();
        devorador.atacar(nexo);
        //escudo completo
        assertTrue(nexo.tieneEscudoCompleto());
    }

    @Test
    public void devoradorAtacaNexoMineral14vecesYDaña210unidades() throws MenaOcupadaException, RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        NexoMineral nexo = new NexoMineral(new Posicion(1,1), new Mena(new Posicion(1,1)), jugador);
        Mineral mineral = new Mineral(1000);
        GasVespeno gas = new GasVespeno(1000);
        Devorador devorador = new Devorador(mineral, gas, new Posicion(4, 1), new Mapa());
        devorador.pasarTiempo();
        devorador.pasarTiempo();
        devorador.pasarTiempo();
        devorador.pasarTiempo();
        // Su unidad de ataque es de 15, con 14 ataques son 210 de daño
        for (int i = 0; i < 14; i++)
            devorador.atacar(nexo);

        // SE TIENE QUE TERMINAR DE CONSTRUIR PARA QUE SE REGENERE
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();

        //escudo dañado
        assertFalse(nexo.tieneEscudoCompleto());
        nexo.pasarTiempo();
        //sigue dañado
        assertFalse(nexo.tieneEscudoCompleto());
        nexo.pasarTiempo();
        for (int i = 0;  i < 100; i++)
            nexo.pasarTiempo();
        //escudo completo
        assertTrue(nexo.tieneEscudoCompleto());
    }

    @Test
    public void devoradorNoAtacaNexoMineralPorqueEstaFueraDeRango() throws MenaOcupadaException, RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        NexoMineral nexo = new NexoMineral(new Posicion(1,1), new Mena(new Posicion(1,1)), jugador);
        Mineral mineral = new Mineral(1000);
        GasVespeno gas = new GasVespeno(1000);
        Devorador devorador = new Devorador(mineral, gas, new Posicion(1, 7), new Mapa());
        devorador.pasarTiempo();
        devorador.pasarTiempo();
        devorador.pasarTiempo();
        devorador.pasarTiempo();
        // Su unidad de ataque es de 15, con 14 ataques son 210 de daño
        for (int i = 0; i < 14; i++)
            devorador.atacar(nexo);

        //escudo completo porque esta fuera de rango
        assertTrue(nexo.tieneEscudoCompleto());
    }

    @Test
    public void devoradorNoPuedeAtacarTerrestrePeroSiVoladora() throws RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException {

        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(1000);
        Zerling zerling = new Zerling(mineral, new Posicion(1,2), new Mapa());
        Devorador devorador = new Devorador(mineral, gas, new Posicion(1,4), new Mapa());
        Scout scout = new Scout(mineral, gas, new Posicion(1,3), new Mapa());
        devorador.pasarTiempo();
        devorador.pasarTiempo();
        devorador.pasarTiempo();
        devorador.pasarTiempo();
        devorador.pasarTiempo();
        devorador.pasarTiempo();
        assertTrue(zerling.tieneVidaCompleta());
        assertTrue(scout.tieneVidaCompleta());
        for (int i = 0; i < 30; i++)
        {
            devorador.atacar(zerling);
            devorador.atacar(scout);
        }

        assertTrue(zerling.tieneVidaCompleta());
        assertFalse(scout.tieneVidaCompleta());
    }

    @Test
    public void devoradorPuedeMoverseAZonaEspacialYAOtroLado() throws RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException {

        Mapa mapa = new Mapa();
        mapa.agregarAreaEspacial(new AreaEspacial(0, 0, 10, 10));
        GasVespeno gas = new GasVespeno(10000);
        Mineral mineral = new Mineral(10000);
        Devorador devorador = new Devorador(mineral, gas, new Posicion(11,11), mapa);
        devorador.pasarTiempo();
        devorador.pasarTiempo();
        devorador.pasarTiempo();
        devorador.pasarTiempo();
        devorador.pasarTiempo();
        devorador.pasarTiempo();
        assertTrue(devorador.mover(new Posicion(12, 12)));
        assertTrue(devorador.mover(new Posicion(3, 3)));
    }
}
