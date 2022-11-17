package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Edificios.NexoMineral;
import edu.fiuba.algo3.modelo.Exceptions.MenaOcupadaException;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Individuos.Guardian;
import edu.fiuba.algo3.modelo.Individuos.Hidralisco;
import edu.fiuba.algo3.modelo.Individuos.Zerling;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mena;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class HidraliscoTest {

    // caso 22
    @Test
    public void hidraliscoNoGeneraDañoPorNoEstarConstruidoAun() throws MenaOcupadaException, RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException {
        Mapa mapa = new Mapa();
        NexoMineral nexo = new NexoMineral(new Posicion(1,1), new Mena(new Posicion(1,1)), mapa);
        Mineral mineral = new Mineral(125);
        GasVespeno gas = new GasVespeno(25);
        Hidralisco hidralisco = new Hidralisco(mineral, gas, new Posicion(3,3), new Mapa());
        mapa.agregarConstruccion(nexo, mineral, gas);
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();

        // EL tiempo de construccion es 4, con un solo tiempo no esta construido
        hidralisco.pasarTiempo();
        hidralisco.atacar(nexo);
        //escudo completo
        assertTrue(nexo.tieneEscudoCompleto());
    }

    // caso 18
    @Test
    public void HidraliscoAtacaNexoMineral20VecesYGenera200UnidadesDeDaño() throws MenaOcupadaException, RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException {
        Mapa mapa = new Mapa();
        NexoMineral nexo = new NexoMineral(new Posicion(1,1), new Mena(new Posicion(1,1)), mapa);

        // tiempo de construccion
        Mineral mineral = new Mineral(125);
        GasVespeno gas = new GasVespeno(25);
        Hidralisco hidralisco = new Hidralisco(mineral, gas, new Posicion(3,3), new Mapa());
        mapa.agregarConstruccion(nexo, mineral, gas);
        hidralisco.pasarTiempo();
        hidralisco.pasarTiempo();
        hidralisco.pasarTiempo();
        hidralisco.pasarTiempo();
        // Su unidad de ataque es de 10, con 20 ataques son 200 de daño
        for (int i = 0; i < 20; i++)
            hidralisco.atacar(nexo);

        // SE TIENE QUE TERMINAR DE CONSTRUIR PARA QUE SE REGENERE
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();

        //escudo dañado
        assertFalse(nexo.tieneEscudoCompleto());
        mapa.pasarTiempo();
        //sigue dañado
        assertFalse(nexo.tieneEscudoCompleto());
        mapa.pasarTiempo();
        //escudo completo
        assertTrue(nexo.tieneEscudoCompleto());

    }
    // caso 23
    @Test
    public void hidraliscoNoAtacaNexoMineralPorqueEstaFueraDeRango() throws MenaOcupadaException, RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException {
        Mapa mapa = new Mapa();
        NexoMineral nexo = new NexoMineral(new Posicion(1,1), new Mena(new Posicion(1,1)), mapa);

        // tiempo de construccion
        Mineral mineral = new Mineral(125);
        GasVespeno gas = new GasVespeno(25);
        Hidralisco hidralisco = new Hidralisco(mineral, gas, new Posicion(10,10), new Mapa());
        mapa.agregarConstruccion(nexo, mineral, gas);
        hidralisco.pasarTiempo();
        hidralisco.pasarTiempo();
        hidralisco.pasarTiempo();
        hidralisco.pasarTiempo();
        // Su unidad de ataque es de 10, con 20 ataques son 200 de daño
        for (int i = 0; i < 20; i++)
            hidralisco.atacar(nexo);

        assertTrue(nexo.tieneEscudoCompleto());

    }

    @Test
    public void hidraliscoPuedeAtacarUnidadVoladoraYTerrestre() throws RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException {

        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(1000);
        Zerling zerling = new Zerling(mineral, new Posicion(1,2), new Mapa());
        Guardian guardian = new Guardian(mineral, gas, new Posicion(1,2), new Mapa());
        Hidralisco hidral = new Hidralisco(mineral, gas, new Posicion(1,2), new Mapa());
        hidral.pasarTiempo();
        hidral.pasarTiempo();
        hidral.pasarTiempo();
        hidral.pasarTiempo();
        hidral.pasarTiempo();
        hidral.pasarTiempo();
        assertTrue(zerling.tieneVidaCompleta());
        assertTrue(guardian.tieneVidaCompleta());
        for (int i = 0; i < 10; i++)
        {
            hidral.atacar(zerling);
            hidral.atacar(guardian);
        }


        assertFalse(zerling.tieneVidaCompleta());
        assertFalse(guardian.tieneVidaCompleta());
    }

    @Test
    public void hidraliscoNoPuedeMoverseAZonaEspacialPeroSiAOtroLado() throws RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException {

        Mapa mapa = new Mapa();
        mapa.agregarAreaEspacial(new AreaEspacial(0, 0, 10, 10));
        GasVespeno gas = new GasVespeno(10000);
        Mineral mineral = new Mineral(10000);
        Hidralisco hidra = new Hidralisco(mineral, gas, new Posicion(11,11), mapa);
        hidra.pasarTiempo();
        hidra.pasarTiempo();
        hidra.pasarTiempo();
        hidra.pasarTiempo();
        hidra.pasarTiempo();
        hidra.pasarTiempo();
        assertTrue(hidra.mover(new Posicion(12, 12)));
        assertFalse(hidra.mover(new Posicion(3, 3)));
    }

}
