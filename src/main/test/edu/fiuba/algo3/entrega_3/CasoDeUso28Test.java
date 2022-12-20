package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.Edificios.Acceso;
import edu.fiuba.algo3.modelo.Edificios.Criadero;
import edu.fiuba.algo3.modelo.Edificios.Guarida;
import edu.fiuba.algo3.modelo.Edificios.Pilon;
import edu.fiuba.algo3.modelo.Edificios.ReservaDeReproduccion;
import edu.fiuba.algo3.modelo.Exceptions.AccesoNoDisponibleException;
import edu.fiuba.algo3.modelo.Exceptions.GuaridaNoDisponibleException;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Exceptions.ReservaDeReproduccionNoDisponibleException;
import edu.fiuba.algo3.modelo.Individuos.*;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CasoDeUso28Test {
    @Test
    public void testUnZealotSeVuelveInvisibleAlMatarTresUnidadesEnemigas() throws RequerimientosInsuficientesException, ReservaDeReproduccionNoDisponibleException, NoExisteEdificioCorrelativoException, AccesoNoDisponibleException, RecursosInsuficientesException{

        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Jugador jugadorProtoss = new Jugador("jugadorUno", "azul", "protoss", new Posicion(10,10), mapa, 200);
        jugador.incrementarMineral(1000);
        jugador.incrementarGas(1000);
        jugadorProtoss.incrementarMineral(1000);
        jugadorProtoss.incrementarGas(1000);
        Criadero criadero = new Criadero(new Posicion(1, 1), jugador);
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        ReservaDeReproduccion reservaDeReproduccion = new ReservaDeReproduccion(new Posicion(4, 2), jugador);
        for(int i=0; i<20; i++)
            reservaDeReproduccion.pasarTiempo();
        Pilon pilon = new Pilon(new Posicion(10, 11), jugadorProtoss);
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        Acceso acceso = new Acceso(new Posicion(11,11), jugadorProtoss);
        Zealot zealot = new Zealot(new Posicion(2, 2), jugadorProtoss);
        Zerling zerlingUno = new Zerling(new Posicion(2,3), jugador);
        Zerling zerlingDos = new Zerling(new Posicion(1,2), jugador);
        Zerling zerlingTres = new Zerling(new Posicion(3,2), jugador);
        AmoSupremo amoSupremo = new AmoSupremo(new Posicion(1, 3), jugador);

        for (int i = 0; i < 5; i ++){
            zealot.pasarTiempo();
            zerlingUno.pasarTiempo();
            zerlingDos.pasarTiempo();
            zerlingTres.pasarTiempo();
            amoSupremo.pasarTiempo();
        }

        assertFalse(zealot.invisible);

        for (int i = 0; i < 5; i++){
            zealot.atacar(zerlingUno);
        }
        for (int i = 0; i < 5; i++){
            zealot.atacar(zerlingDos);
        }
        for (int i = 0; i < 5; i++){
            zealot.atacar(zerlingTres);
        }

        assertTrue(zealot.invisible);

    }

    @Test
    public void testUnZealotSeVuelveInvisibleAlMatarUnaCombinacionDeTresEnemigos() throws RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException, RecursosInsuficientesException, AccesoNoDisponibleException, ReservaDeReproduccionNoDisponibleException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Jugador jugadorProtoss = new Jugador("jugadorUno", "azul", "protoss", new Posicion(10,10), mapa, 200);
        jugador.incrementarMineral(1000);
        jugador.incrementarGas(1000);
        jugadorProtoss.incrementarMineral(1000);
        jugadorProtoss.incrementarGas(1000);
        Criadero criadero = new Criadero(new Posicion(3,2), jugador);
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        ReservaDeReproduccion reservaDeReproduccion = new ReservaDeReproduccion(new Posicion(4, 2), jugador);
        for(int i=0; i<20; i++)
            reservaDeReproduccion.pasarTiempo();
        Pilon pilon = new Pilon(new Posicion(10, 11), jugadorProtoss);
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        Acceso acceso = new Acceso(new Posicion(11,11), jugadorProtoss);
        Zealot zealot = new Zealot(new Posicion(2, 2), jugadorProtoss);
        Zerling zerlingUno = new Zerling(new Posicion(2,3), jugador);
        Zerling zerlingDos = new Zerling(new Posicion(1,2), jugador);
        AmoSupremo amoSupremo = new AmoSupremo(new Posicion(1, 3), jugador);

        for (int i = 0; i < 5; i ++){
            zealot.pasarTiempo();
            zerlingUno.pasarTiempo();
            zerlingDos.pasarTiempo();
            criadero.pasarTiempo();
            amoSupremo.pasarTiempo();
        }

        assertFalse(zealot.invisible);

        for (int i = 0; i < 5; i++){
            zealot.atacar(zerlingUno);
        }
        for (int i = 0; i < 5; i++){
            zealot.atacar(zerlingDos);
        }
        for (int i = 0; i < 63; i++){
            zealot.atacar(criadero);
        }

        assertTrue(zealot.invisible);

    }

    @Test
    public void testUnZealotInvisibleNoRecibeDañoSiUnAmoSupremoNoLoDetectaYLosEnemigosEstanFueraDeSuRango() throws RequerimientosInsuficientesException, RecursosInsuficientesException, ReservaDeReproduccionNoDisponibleException, NoExisteEdificioCorrelativoException, GuaridaNoDisponibleException, AccesoNoDisponibleException{

        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Jugador jugadorProtoss = new Jugador("jugadorUno", "azul", "protoss", new Posicion(10,10), mapa, 200);
        jugador.incrementarMineral(1000);
        jugador.incrementarGas(1000);
        jugadorProtoss.incrementarMineral(1000);
        jugadorProtoss.incrementarGas(1000);
        Criadero criadero = new Criadero(new Posicion(3,3), jugador);
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        ReservaDeReproduccion reservaDeReproduccion = new ReservaDeReproduccion(new Posicion(4, 2), jugador);
        for(int i=0; i<20; i++)
            reservaDeReproduccion.pasarTiempo();
        Pilon pilon = new Pilon(new Posicion(10, 11), jugadorProtoss);
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        Guarida guarida = new Guarida(new Posicion(3, 4), jugador);
        Acceso acceso = new Acceso(new Posicion(11,11), jugadorProtoss);
        Zealot zealot = new Zealot(new Posicion(2, 2), jugadorProtoss);
        Zerling zerlingUno = new Zerling(new Posicion(2,3), jugador);
        Zerling zerlingDos = new Zerling(new Posicion(1,2), jugador);
        Zerling zerlingTres = new Zerling(new Posicion(3,2), jugador);
        Hidralisco hidralisco = new Hidralisco(new Posicion(4,4), jugador);
        AmoSupremo amoSupremo = new AmoSupremo(new Posicion(8, 8), jugador);

        for (int i = 0; i < 5; i ++){
            zealot.pasarTiempo();
            zerlingUno.pasarTiempo();
            zerlingDos.pasarTiempo();
            zerlingTres.pasarTiempo();
            hidralisco.pasarTiempo();
            amoSupremo.pasarTiempo();
        }

        for (int i = 0; i < 5; i++){
            zealot.atacar(zerlingUno);
        }
        for (int i = 0; i < 5; i++){
            zealot.atacar(zerlingDos);
        }
        for (int i = 0; i < 5; i++){
            zealot.atacar(zerlingTres);
        }

        assertTrue(zealot.invisible);

        zealot.recibirDaño(60);         //Le quito el escudo
        hidralisco.atacar(zealot);              //Hidralisco no esta en el rango de zealot.

        assertTrue(zealot.tieneVidaCompleta()); //Zealot invisible, no hay amo supremo en rango y el hidralisco no esta en rango de zealot, no recibio daño

    }

    @Test
    public void testUnZealotInvisibleRecibeDañoAunqueNoHayaAmoSupremoPorqueSuEnemigoEstaEnSuRango() throws RequerimientosInsuficientesException, RecursosInsuficientesException, NoExisteEdificioCorrelativoException, GuaridaNoDisponibleException, ReservaDeReproduccionNoDisponibleException, AccesoNoDisponibleException{

        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Jugador jugadorProtoss = new Jugador("jugadorUno", "azul", "protoss", new Posicion(10,10), mapa, 200);
        jugador.incrementarMineral(1000);
        jugador.incrementarGas(1000);
        jugadorProtoss.incrementarMineral(1000);
        jugadorProtoss.incrementarGas(1000);
        Criadero criadero = new Criadero(new Posicion(3,3), jugador);
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        ReservaDeReproduccion reservaDeReproduccion = new ReservaDeReproduccion(new Posicion(4, 2), jugador);
        for(int i=0; i<20; i++)
            reservaDeReproduccion.pasarTiempo();
        Pilon pilon = new Pilon(new Posicion(10, 11), jugadorProtoss);
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        Guarida guarida = new Guarida(new Posicion(3, 4), jugador);
        Acceso acceso = new Acceso(new Posicion(11,11), jugadorProtoss);
        Zealot zealot = new Zealot(new Posicion(2, 2), jugadorProtoss);
        Zerling zerlingUno = new Zerling(new Posicion(2,3), jugador);
        Zerling zerlingDos = new Zerling(new Posicion(1,2), jugador);
        Zerling zerlingTres = new Zerling(new Posicion(3,2), jugador);
        Hidralisco hidralisco = new Hidralisco(new Posicion(2,1), jugador);
        
        for (int i = 0; i < 5; i ++){
            zealot.pasarTiempo();
            zerlingUno.pasarTiempo();
            zerlingDos.pasarTiempo();
            zerlingTres.pasarTiempo();
            hidralisco.pasarTiempo();
        }

        for (int i = 0; i < 5; i++){
            zealot.atacar(zerlingUno);
        }
        for (int i = 0; i < 5; i++){
            zealot.atacar(zerlingDos);
        }
        for (int i = 0; i < 5; i++){
            zealot.atacar(zerlingTres);
        }

        assertTrue(zealot.invisible);

        zealot.recibirDaño(60);         //Le quito el escudo
        hidralisco.atacar(zealot);

        assertFalse(zealot.tieneVidaCompleta()); //Hidralisco esta en rango de zealot y le quita vida aunque sea invisible. No hay vida completa

    }

    @Test
    public void testUnZealotInvisibleRecibeDañoSiUnAmoSupremoLoDetectaParaSusUnidadesAliadas() throws RequerimientosInsuficientesException, RecursosInsuficientesException, NoExisteEdificioCorrelativoException, AccesoNoDisponibleException, ReservaDeReproduccionNoDisponibleException{
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Jugador jugadorProtoss = new Jugador("jugadorUno", "azul", "protoss", new Posicion(10,10), mapa, 200);
        jugador.incrementarMineral(1000);
        jugador.incrementarGas(1000);
        jugadorProtoss.incrementarMineral(1000);
        jugadorProtoss.incrementarGas(1000);
        Criadero criadero = new Criadero(new Posicion(3,3), jugador);
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        ReservaDeReproduccion reservaDeReproduccion = new ReservaDeReproduccion(new Posicion(4, 2), jugador);
        for(int i=0; i<20; i++)
            reservaDeReproduccion.pasarTiempo();
        Pilon pilon = new Pilon(new Posicion(10, 11), jugadorProtoss);
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        Guarida guarida = new Guarida(new Posicion(3, 4), jugador);
        Acceso acceso = new Acceso(new Posicion(11,11), jugadorProtoss);
        Zealot zealot = new Zealot(new Posicion(2, 2), jugadorProtoss);
        Zerling zerlingUno = new Zerling(new Posicion(2,3), jugador);
        Zerling zerlingDos = new Zerling(new Posicion(1,2), jugador);
        Zerling zerlingTres = new Zerling(new Posicion(3,2), jugador);
        Zerling zerlingCuatro = new Zerling(new Posicion(2,1), jugador);
        AmoSupremo amoSupremo = new AmoSupremo(new Posicion(1, 3), jugador);

        for (int i = 0; i < 5; i ++){
            zealot.pasarTiempo();
            zerlingUno.pasarTiempo();
            zerlingDos.pasarTiempo();
            zerlingTres.pasarTiempo();
            zerlingCuatro.pasarTiempo();
            amoSupremo.pasarTiempo();
        }

        for (int i = 0; i < 5; i++){
            zealot.atacar(zerlingUno);
        }
        for (int i = 0; i < 5; i++){
            zealot.atacar(zerlingDos);
        }
        for (int i = 0; i < 5; i++){
            zealot.atacar(zerlingTres);
        }

        assertTrue(zealot.invisible);

        for (int i = 0; i < 16; i++){           //Itero 16 veces porque el daño es de 4 y el escudo del zealot es de 60
            zerlingCuatro.atacar(zealot);       //Ataco para sacar escudo del Zealot
        }

        assertFalse(zealot.tieneVidaCompleta()); //Zealot visible por amo supremo se quedo sin escudo y se debilito

    }

}
