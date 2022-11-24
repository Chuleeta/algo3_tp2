package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.Edificios.Criadero;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Individuos.AmoSupremo;
import edu.fiuba.algo3.modelo.Individuos.Zealot;
import edu.fiuba.algo3.modelo.Individuos.Zerling;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CasoDeUso28Test {
    @Test
    public void testUnZealotSeVuelveInvisibleAlMatarTresUnidadesEnemigas() throws RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException {

        Mineral mineral = new Mineral(1200);
        GasVespeno gas = new GasVespeno(300);
        Mapa mapa = new Mapa();
        Zealot zealot = new Zealot(mineral, new Posicion(2, 2), mapa);
        Zerling zerlingUno = new Zerling(mineral, new Posicion(2,3), mapa);
        Zerling zerlingDos = new Zerling(mineral, new Posicion(1,2), mapa);
        Zerling zerlingTres = new Zerling(mineral, new Posicion(3,2), mapa);
        AmoSupremo amoSupremo = new AmoSupremo(mineral, gas, new Posicion(1, 3), mapa);

        for (int i = 0; i < 7; i ++){
            mapa.pasarTiempo();
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
    public void testUnZealotSeVuelveInvisibleAlMatarUnaCombinacionDeTresEnemigos() throws RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException {

        Mineral mineral = new Mineral(1200);
        GasVespeno gas = new GasVespeno(300);
        Mapa mapa = new Mapa();
        //Criadero criadero = new Criadero(new Posicion(3,2), mapa);
        Zealot zealot = new Zealot(mineral, new Posicion(2, 2), mapa);
        Zerling zerlingUno = new Zerling(mineral, new Posicion(2,3), mapa);
        Zerling zerlingDos = new Zerling(mineral, new Posicion(1,2), mapa);
        Criadero criadero = new Criadero(new Posicion(3,2), mapa);
        AmoSupremo amoSupremo = new AmoSupremo(mineral, gas, new Posicion(1, 3), mapa);

        for (int i = 0; i < 7; i ++){
            mapa.pasarTiempo();
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


}
