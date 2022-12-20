package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Edificios.Criadero;
import edu.fiuba.algo3.modelo.Edificios.Espiral;
import edu.fiuba.algo3.modelo.Edificios.Guarida;
import edu.fiuba.algo3.modelo.Edificios.NexoMineral;
import edu.fiuba.algo3.modelo.Edificios.ReservaDeReproduccion;
import edu.fiuba.algo3.modelo.Exceptions.EspiralNoDisponibleException;
import edu.fiuba.algo3.modelo.Exceptions.MenaOcupadaException;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Individuos.Guardian;
import edu.fiuba.algo3.modelo.Individuos.Mutalisco;
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
    // @Test
    // public void guardianNoGeneraDañoPorNoEstarConstruidoAun() throws MenaOcupadaException, RequerimientosInsuficientesException, RecursosInsuficientesException, EspiralNoDisponibleException, NoExisteEdificioCorrelativoException {
    //     Mapa mapa = new Mapa();
    //     Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(0,1), mapa, 200);
    //     jugador.incrementarMineral(1000);
    //     jugador.incrementarGas(1000);
    //     Criadero criadero = new Criadero(new Posicion(1,1), jugador);
    //     jugador.pasarTiempo();
    //     jugador.pasarTiempo();
    //     jugador.pasarTiempo();
    //     jugador.pasarTiempo();
    //     ReservaDeReproduccion reserva = new ReservaDeReproduccion(new Posicion(1,3), jugador);
    //     for (int i = 0; i < 20; i++)
    //         jugador.pasarTiempo();
    //     Guarida guarida = new Guarida(new Posicion(2,3), jugador);
    //     for (int i = 0; i < 20; i++)
    //         jugador.pasarTiempo();
    //     Espiral espiral = new Espiral(new Posicion(3, 3), jugador);
    //     for (int i = 0; i < 20; i++)
    //         jugador.pasarTiempo();
    //     Mutalisco muta = new Mutalisco(new Posicion(8, 8), jugador);
    //     for (int i = 0; i < 20; i++)
    //         jugador.pasarTiempo();
    //     NexoMineral nexo = new NexoMineral(new Posicion(5,1), new Mena(new Posicion(5,1)), jugador);
    //     Guardian guardian = new Guardian(new Posicion(8, 8), jugador);

    //     // EL tiempo de construccion es 4, con un solo tiempo no esta construido
    //     guardian.pasarTiempo();
    //     guardian.atacar(nexo);
    //     //escudo completo
    //     assertTrue(nexo.tieneEscudoCompleto());
    // }

    // caso 18
    @Test
    public void guardianAtacaNexoMineral8vecesYDaña200unidades() throws MenaOcupadaException, RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        NexoMineral nexo = new NexoMineral(new Posicion(1,1), new Mena(new Posicion(2,1)), jugador);
        Guardian guardian = new Guardian(new Mineral(200), new GasVespeno(200), new Posicion(8, 8), mapa);
        guardian.pasarTiempo();
        guardian.pasarTiempo();
        guardian.pasarTiempo();
        guardian.pasarTiempo();
        // Su unidad de ataque es de 25, con 8 ataques son 200 de daño
        for (int i = 0; i < 8; i++)
            guardian.atacar(nexo);

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
        //escudo completo
        assertTrue(nexo.tieneEscudoCompleto());
    }

    // caso 23
    @Test
    public void guardianNoAtacaNexoMineralPorqueEstaFueraDeRango() throws MenaOcupadaException, RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        NexoMineral nexo = new NexoMineral(new Posicion(1,2), new Mena(new Posicion(1,2)), jugador);
        Guardian guardian = new Guardian(new Mineral(100), new GasVespeno(150), new Posicion(2, 13), new Mapa());
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

