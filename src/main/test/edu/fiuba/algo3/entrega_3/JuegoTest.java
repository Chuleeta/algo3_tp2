package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.Jugador;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Edificios.Criadero;
import edu.fiuba.algo3.modelo.Edificios.Pilon;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Individuos.Dragon;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JuegoTest {
    @Test
    public void creoUnJuegoNuevoSinMapa(){
        assertThrows(RequerimientosInsuficientesException.class, ()->{ new Juego(null, null, null); });
    }

    @Test
    public void elJuegoTerminaCuandoSeDestruyenTodosLosEdificiosDeUnJugador() throws RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException{
        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(10000);
        Mapa mapa = new Mapa();
        Jugador jugadorUno = new Jugador("jugadorUno", "azul", "protoss", new Posicion(1,1), mapa, 200);
        Jugador jugadorDos = new Jugador("jugadorDos", "rojo", "zerg", new Posicion(100,100), mapa, 200);
        Pilon pilon = new Pilon(new Posicion(9,9), mapa, jugadorUno);
        jugadorUno.agregarConstruccion(pilon);
        Criadero criadero = new Criadero(new Posicion(9,20), mapa, jugadorDos);
        jugadorDos.agregarConstruccion(criadero);

        jugadorUno.pasarTiempo();
        jugadorUno.pasarTiempo();
        jugadorUno.pasarTiempo();
        jugadorUno.pasarTiempo();
        jugadorUno.pasarTiempo();
        jugadorDos.pasarTiempo();
        jugadorDos.pasarTiempo();
        jugadorDos.pasarTiempo();
        jugadorDos.pasarTiempo();
        jugadorDos.pasarTiempo();

        Juego juego = new Juego(mapa, jugadorUno, jugadorDos);

        Dragon dragon = new Dragon(mineral, gas, new Posicion(9,18), mapa, jugadorUno);
        jugadorUno.agregarIndividuo(dragon);
        
        assertTrue(juego.pasarTiempo());
        assertTrue(juego.pasarTiempo());
        assertTrue(juego.pasarTiempo());
        assertTrue(juego.pasarTiempo());
        assertTrue(juego.pasarTiempo());
        assertTrue(juego.pasarTiempo());

        for (int i = 0; i < 40; i++)
            dragon.atacar(criadero);

        assertFalse(juego.pasarTiempo());
    }

    // @Test
    // public void creoUnJuegoNuevoSinEdificios(){
        
    // }
    
    // @Test
    // public void creoUnJuegoNuevoSinEdificios(){
        
    // }
}
