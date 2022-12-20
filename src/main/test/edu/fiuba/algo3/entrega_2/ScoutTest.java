package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Edificios.Criadero;
import edu.fiuba.algo3.modelo.Edificios.NexoMineral;
import edu.fiuba.algo3.modelo.Exceptions.MenaOcupadaException;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.RecursosInsuficientesException;
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

public class ScoutTest {

    // caso 22
    @Test
    public void scoutNoGeneraDañoPorNoEstarConstruidoAun() throws MenaOcupadaException, RequerimientosInsuficientesException {

        NexoMineral nexo = new NexoMineral(new Posicion(1,1), new Mena(new Posicion(1,1)), new Mapa());
        Mineral mineral = new Mineral(300);
        GasVespeno gas = new GasVespeno(150);
        Scout scout = new Scout(mineral, gas, new Posicion(1, 5), new Mapa());

        // EL tiempo de construccion es 4, con un solo tiempo no esta construido
        scout.pasarTiempo();
        scout.atacar(nexo);
        //escudo completo
        assertTrue(nexo.tieneEscudoCompleto());
    }
    // caso 18
    @Test
    public void scoutAtaca10VecesACriaderoYSon200UnidadesDeAtaque() throws RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Criadero criadero = new Criadero(new Posicion(1,1), jugador);
        Mineral mineral = new Mineral(300);
        GasVespeno gas = new GasVespeno(150);
        Scout scout = new Scout(mineral, gas, new Posicion(1,5), new Mapa());
        scout.pasarTiempo();
        scout.pasarTiempo();
        scout.pasarTiempo();
        scout.pasarTiempo();
        scout.pasarTiempo();
        scout.pasarTiempo();
        scout.pasarTiempo();
        scout.pasarTiempo();
        scout.pasarTiempo();

        // Su unidad de ataque a tierra es de 8, con 25 ataques son 200 de daño

        for (int i = 0; i < 25; i++)
            scout.atacar(criadero);

        //SE TIENE QUE TERMINAR DE CONSTRUIR EL CRIADERO PARA REGENERARSE
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();

        //vida incompleta
        assertFalse(criadero.tieneVidaCompleta());
        criadero.pasarTiempo();

        //sigue incompleta
        assertFalse(criadero.tieneVidaCompleta());
        criadero.pasarTiempo();

        //vida completa
        assertTrue(criadero.tieneVidaCompleta());

    }

    // caso 23
    @Test
    public void scoutNoAtacaCriaderolPorqueEstaFueraDeRango() throws RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        Criadero criadero = new Criadero(new Posicion(1,1), jugador);
        Mineral mineral = new Mineral(300);
        GasVespeno gas = new GasVespeno(150);
        Scout scout = new Scout(mineral, gas, new Posicion(1,6), new Mapa());
        scout.pasarTiempo();
        scout.pasarTiempo();
        scout.pasarTiempo();
        scout.pasarTiempo();
        scout.pasarTiempo();
        scout.pasarTiempo();
        scout.pasarTiempo();
        scout.pasarTiempo();
        scout.pasarTiempo();

        // Su unidad de ataque a tierra es de 8, con 25 ataques son 200 de daño

        for (int i = 0; i < 25; i++)
            scout.atacar(criadero);

        //vida completa
        assertTrue(criadero.tieneVidaCompleta());

    }

    @Test
    public void scoutPuedeAtacarUnidadVoladoraYTerrestre() throws RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException {

        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(1000);
        Zerling zerling = new Zerling(mineral, new Posicion(1,2), new Mapa());
        Guardian guardian = new Guardian(mineral, gas, new Posicion(1,2), new Mapa());
        Scout scout = new Scout(mineral, gas, new Posicion(1,2), new Mapa());
        scout.pasarTiempo();
        scout.pasarTiempo();
        scout.pasarTiempo();
        scout.pasarTiempo();
        scout.pasarTiempo();
        scout.pasarTiempo();
        scout.pasarTiempo();
        scout.pasarTiempo();
        scout.pasarTiempo();
        assertTrue(zerling.tieneVidaCompleta());
        assertTrue(guardian.tieneVidaCompleta());
        for (int i = 0; i < 10; i++)
        {
            scout.atacar(zerling);
            scout.atacar(guardian);
        }


        assertFalse(zerling.tieneVidaCompleta());
        assertFalse(guardian.tieneVidaCompleta());
    }

    @Test
    public void scoutPuedeMoverseAZonaEspacialYAOtroLado() throws RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException {

        Mapa mapa = new Mapa();
        mapa.agregarAreaEspacial(new AreaEspacial(0, 0, 10, 10));
        GasVespeno gas = new GasVespeno(10000);
        Mineral mineral = new Mineral(10000);
        Scout scout = new Scout(mineral, gas, new Posicion(11,11), mapa);
        scout.pasarTiempo();
        scout.pasarTiempo();
        scout.pasarTiempo();
        scout.pasarTiempo();
        scout.pasarTiempo();
        scout.pasarTiempo();
        assertTrue(scout.mover(new Posicion(12, 12)));
        assertTrue(scout.mover(new Posicion(3, 3)));
    }
}
