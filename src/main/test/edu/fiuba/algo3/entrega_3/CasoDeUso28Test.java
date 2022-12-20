package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.Edificios.Criadero;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
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
    public void testUnZealotSeVuelveInvisibleAlMatarTresUnidadesEnemigas() throws RequerimientosInsuficientesException{

        Mineral mineral = new Mineral(1200);
        GasVespeno gas = new GasVespeno(300);
        Mapa mapa = new Mapa();
        Zealot zealot = new Zealot(mineral, new Posicion(2, 2), mapa);
        Zerling zerlingUno = new Zerling(mineral, new Posicion(2,3), mapa);
        Zerling zerlingDos = new Zerling(mineral, new Posicion(1,2), mapa);
        Zerling zerlingTres = new Zerling(mineral, new Posicion(3,2), mapa);
        AmoSupremo amoSupremo = new AmoSupremo(mineral, gas, new Posicion(1, 3), mapa);

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
    public void testUnZealotSeVuelveInvisibleAlMatarUnaCombinacionDeTresEnemigos() throws RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Criadero criadero = new Criadero(new Posicion(3,2), jugador);
        Mineral mineral = new Mineral(1200);
        GasVespeno gas = new GasVespeno(300);
        Zealot zealot = new Zealot(mineral, new Posicion(2, 2), mapa);
        Zerling zerlingUno = new Zerling(mineral, new Posicion(2,3), mapa);
        Zerling zerlingDos = new Zerling(mineral, new Posicion(1,2), mapa);
        AmoSupremo amoSupremo = new AmoSupremo(mineral, gas, new Posicion(1, 3), mapa);

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
    public void testUnZealotInvisibleNoRecibeDañoSiUnAmoSupremoNoLoDetectaYLosEnemigosEstanFueraDeSuRango() throws RequerimientosInsuficientesException{

        Mineral mineral = new Mineral(1200);
        GasVespeno gas = new GasVespeno(1300);
        Mapa mapa = new Mapa();
        Posicion posi = new Posicion(2, 2);
        Zealot zealot = new Zealot(mineral, posi, mapa);
        Zerling zerlingUno = new Zerling(mineral, new Posicion(2,3), mapa);
        Zerling zerlingDos = new Zerling(mineral, new Posicion(1,2), mapa);
        Zerling zerlingTres = new Zerling(mineral, new Posicion(3,2), mapa);
        Hidralisco hidralisco = new Hidralisco(mineral, gas, new Posicion(6,7), mapa);
        AmoSupremo amoSupremo = new AmoSupremo(mineral, gas, new Posicion(8, 8), mapa);

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
    public void testUnZealotInvisibleRecibeDañoAunqueNoHayaAmoSupremoPorqueSuEnemigoEstaEnSuRango() throws RequerimientosInsuficientesException{

        Mineral mineral = new Mineral(1200);
        GasVespeno gas = new GasVespeno(1300);
        Mapa mapa = new Mapa();
        Posicion posi = new Posicion(2, 2);
        Zealot zealot = new Zealot(mineral, posi, mapa);
        Zerling zerlingUno = new Zerling(mineral, new Posicion(2,3), mapa);
        Zerling zerlingDos = new Zerling(mineral, new Posicion(1,2), mapa);
        Zerling zerlingTres = new Zerling(mineral, new Posicion(3,2), mapa);
        Hidralisco hidralisco = new Hidralisco(mineral, gas, new Posicion(2,1), mapa); //A rango del zealot

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
    public void testUnZealotInvisibleRecibeDañoSiUnAmoSupremoLoDetectaParaSusUnidadesAliadas() throws RequerimientosInsuficientesException{

        Mineral mineral = new Mineral(1200);
        GasVespeno gas = new GasVespeno(300);
        Mapa mapa = new Mapa();
        Zealot zealot = new Zealot(mineral, new Posicion(2, 2), mapa);
        Zerling zerlingUno = new Zerling(mineral, new Posicion(2,3), mapa);
        Zerling zerlingDos = new Zerling(mineral, new Posicion(1,2), mapa);
        Zerling zerlingTres = new Zerling(mineral, new Posicion(3,2), mapa);
        Zerling zerlingCuatro = new Zerling(mineral, new Posicion(2,1), mapa);
        AmoSupremo amoSupremo = new AmoSupremo(mineral, gas, new Posicion(1, 3), mapa);

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
