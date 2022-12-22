package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Edificios.Acceso;
import edu.fiuba.algo3.modelo.Edificios.Criadero;
import edu.fiuba.algo3.modelo.Edificios.Espiral;
import edu.fiuba.algo3.modelo.Edificios.Guarida;
import edu.fiuba.algo3.modelo.Edificios.NexoMineral;
import edu.fiuba.algo3.modelo.Edificios.Pilon;
import edu.fiuba.algo3.modelo.Edificios.ReservaDeReproduccion;
import edu.fiuba.algo3.modelo.Exceptions.AccesoNoDisponibleException;
import edu.fiuba.algo3.modelo.Exceptions.EspiralNoDisponibleException;
import edu.fiuba.algo3.modelo.Exceptions.MenaOcupadaException;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Exceptions.ReservaDeReproduccionNoDisponibleException;
import edu.fiuba.algo3.modelo.Individuos.Dragon;
import edu.fiuba.algo3.modelo.Individuos.Guardian;
import edu.fiuba.algo3.modelo.Individuos.Mutalisco;
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
    public void zerlingDañaNexoMineral50vecesYSon200UnidadesDeAtaque() throws MenaOcupadaException, RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException, RecursosInsuficientesException, ReservaDeReproduccionNoDisponibleException {
        Mapa mapa = new Mapa();
        Jugador jugador= new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        NexoMineral nexo = new NexoMineral(new Posicion(2,2), new Mena(new Posicion(2,2)), jugador);

        jugador.incrementarMineral(1000);
        jugador.incrementarGas(1000);
        Criadero criadero = new Criadero(new Posicion(1, 1), jugador);
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        ReservaDeReproduccion reservaDeReproduccion = new ReservaDeReproduccion(new Posicion(3, 2), jugador);
        for(int i=0; i<20; i++)
            reservaDeReproduccion.pasarTiempo();

        Zerling zerling = new Zerling(new Posicion(1,2), jugador);

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
        for (int i = 0;  i < 100; i++)
            jugador.pasarTiempo();
        //escudo completo
        assertTrue(nexo.tieneEscudoCompleto());

    }

    // caso 23
    @Test
    public void zerlingNoAtacaNexoMineralPorqueEstaFueraDeRango() throws MenaOcupadaException, RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException, RecursosInsuficientesException, ReservaDeReproduccionNoDisponibleException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        NexoMineral nexo = new NexoMineral(new Posicion(2,2), new Mena(new Posicion(2,1)), jugador);

        // tiempo de construccion
        jugador.incrementarMineral(1000);
        jugador.incrementarGas(1000);
        Criadero criadero = new Criadero(new Posicion(1, 1), jugador);
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        ReservaDeReproduccion reservaDeReproduccion = new ReservaDeReproduccion(new Posicion(3, 2), jugador);
        for(int i=0; i<20; i++)
            reservaDeReproduccion.pasarTiempo();

        Zerling zerling = new Zerling(new Posicion(1,10), jugador);
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
    public void zerlingNoPuedeAtacarUnidadVoladoraPeroSiTerrestre() throws RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException, RecursosInsuficientesException, ReservaDeReproduccionNoDisponibleException, EspiralNoDisponibleException, AccesoNoDisponibleException {

        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Jugador jugadorProtoss = new Jugador("jugadorDos", "azul", "protoss", new Posicion(20,10), mapa, 200);
        jugador.incrementarMineral(1000);
        jugador.incrementarGas(1000);
        Criadero criadero = new Criadero(new Posicion(1, 1), jugador);
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        ReservaDeReproduccion reservaDeReproduccion = new ReservaDeReproduccion(new Posicion(3, 2), jugador);
        for(int i=0; i<20; i++)
            reservaDeReproduccion.pasarTiempo();
        Guarida guarida = new Guarida(new Posicion(2,3), jugador);
        for(int i=0; i<20; i++)
            guarida.pasarTiempo();
        Espiral espiral = new Espiral(new Posicion(3,3), jugador);
        for(int i=0; i<20; i++)
            espiral.pasarTiempo();
        Pilon pilon = new Pilon(new Posicion(8, 8), jugadorProtoss);
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        jugadorProtoss.incrementarMineral(1000);
        jugadorProtoss.incrementarGas(1000);
        Acceso acceso = new Acceso(new Posicion(9, 9), jugadorProtoss);
        Zerling zerling = new Zerling(new Posicion(1,2), jugador);
        Mutalisco mutaltemporal = new Mutalisco(new Posicion(1,5), jugador);
        Guardian guardian = new Guardian(new Posicion(1,5), jugador);
        Dragon dragon = new Dragon(new Posicion(1,3), jugadorProtoss);
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
    public void zealotNoPuedeMoverseAZonaEspacialPeroSiAOtroLado() throws RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException, ReservaDeReproduccionNoDisponibleException, RecursosInsuficientesException {

        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        mapa.agregarAreaEspacial(new AreaEspacial(0, 0, 10, 10));
        jugador.incrementarMineral(1000);
        jugador.incrementarGas(1000);
        Criadero criadero = new Criadero(new Posicion(1, 1), jugador);
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        ReservaDeReproduccion reservaDeReproduccion = new ReservaDeReproduccion(new Posicion(3, 2), jugador);
        for(int i=0; i<20; i++)
            reservaDeReproduccion.pasarTiempo();
        Zerling zerling = new Zerling(new Posicion(11,11), jugador);
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
