package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Edificios.NexoMineral;
import edu.fiuba.algo3.modelo.Exceptions.MenaOcupadaException;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Individuos.Dragon;
import edu.fiuba.algo3.modelo.Individuos.Guardian;
import edu.fiuba.algo3.modelo.Individuos.Zerling;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mena;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ZerlingTest {

    // caso 22
    @Test
    public void zerlingNoGeneraDañoPorNoEstarConstruidoAun() throws MenaOcupadaException, RequerimientosInsuficientesException {

        NexoMineral nexo = new NexoMineral(new Posicion(1,1), new Mena(new Posicion(1,1)), new Mapa());

        Mineral mineral = new Mineral(25);

        Zerling zerling = new Zerling(mineral, new Posicion(1, 2), new Mapa());
        // EL tiempo de construccion es 2, con un solo tiempo no esta construido.
        zerling.pasarTiempo();
        zerling.atacar(nexo);

        //escudo completo
        assertTrue(nexo.tieneEscudoCompleto());

    }
    // caso 18
    @Test
    public void zerlingDañaNexoMineral50vecesYSon200UnidadesDeAtaque() throws MenaOcupadaException, RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException {
        Mapa mapa = new Mapa();
        Jugador jugador= new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        NexoMineral nexo = new NexoMineral(new Posicion(2,2), new Mena(new Posicion(2,2)), mapa, jugador);

        // tiempo de construccion
        Mineral mineral = new Mineral(75);
        GasVespeno gas = new GasVespeno(0);
        jugador.agregarConstruccion(nexo, mineral, gas);

        Zerling zerling = new Zerling(mineral, new Posicion(1,2), mapa);
        jugador.agregarIndividuo(zerling);

        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        // Su unidad de ataque es de 4, con 20 ataques son 200 de daño
        for (int i = 0; i < 50; i++)
            zerling.atacar(nexo);


        //SE CONSTRUYE EL NEXO PARA QUE SE PUEDA REGENERAR EL ESCUDO
        jugador.pasarTiempo();

        //escudo dañado
        assertFalse(nexo.tieneEscudoCompleto());

        jugador.pasarTiempo();
        //escudo completo
        assertTrue(nexo.tieneEscudoCompleto());

    }

    // caso 23
    @Test
    public void zerlingNoAtacaNexoMineralPorqueEstaFueraDeRango() throws MenaOcupadaException, RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        NexoMineral nexo = new NexoMineral(new Posicion(2,2), new Mena(new Posicion(2,1)), mapa, jugador);

        // tiempo de construccion
        Mineral mineral = new Mineral(75);
        GasVespeno gas = new GasVespeno(0);
        jugador.agregarConstruccion(nexo, mineral, gas);

        Zerling zerling = new Zerling(mineral, new Posicion(3,3), mapa);
        jugador.agregarIndividuo(zerling);
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        // Su unidad de ataque es de 10, con 20 ataques son 200 de daño
        for (int i = 0; i < 50; i++)
            zerling.atacar(nexo);

        //escudo completo
        assertTrue(nexo.tieneEscudoCompleto());

    }

    @Test
    public void zerlingNoPuedeAtacarUnidadVoladoraPeroSiTerrestre() throws RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException {

        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(1000);
        Zerling zerling = new Zerling(mineral, new Posicion(1,2), new Mapa());
        Guardian guardian = new Guardian(mineral, gas, new Posicion(1,2), new Mapa());
        Dragon dragon = new Dragon(mineral,gas, new Posicion(1,2), new Mapa());
        zerling.pasarTiempo();
        zerling.pasarTiempo();
        zerling.pasarTiempo();
        zerling.pasarTiempo();
        zerling.pasarTiempo();
        zerling.pasarTiempo();
        assertTrue(dragon.tieneVidaCompleta());
        assertTrue(guardian.tieneVidaCompleta());
        for (int i = 0; i < 30; i++)
        {
            zerling.atacar(dragon);
            zerling.atacar(guardian);
        }


        assertFalse(dragon.tieneVidaCompleta());
        assertTrue(guardian.tieneVidaCompleta());
    }

    @Test
    public void zealotNoPuedeMoverseAZonaEspacialPeroSiAOtroLado() throws RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException {

        Mapa mapa = new Mapa();
        mapa.agregarAreaEspacial(new AreaEspacial(0, 0, 10, 10));
        Mineral mineral = new Mineral(10000);
        Zerling zerling = new Zerling(mineral, new Posicion(11,11), mapa);
        zerling.pasarTiempo();
        zerling.pasarTiempo();
        zerling.pasarTiempo();
        zerling.pasarTiempo();
        zerling.pasarTiempo();
        zerling.pasarTiempo();
        assertTrue(zerling.mover(new Posicion(12, 12)));
        assertFalse(zerling.mover(new Posicion(3, 3)));
    }

}
