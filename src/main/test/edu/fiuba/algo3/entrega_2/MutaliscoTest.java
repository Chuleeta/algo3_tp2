package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Edificios.NexoMineral;
import edu.fiuba.algo3.modelo.Exceptions.MenaOcupadaException;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Individuos.Mutalisco;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mena;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MutaliscoTest {

    // caso 22
    @Test
    public void mutaliscoNoGeneraDañoPorNoEstarConstruidoAun() throws MenaOcupadaException, RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException {
        Mapa mapa = new Mapa();
        NexoMineral nexo = new NexoMineral(new Posicion(1,1), new Mena(new Posicion(1,1)), mapa);
        Mineral mineral = new Mineral(150);
        GasVespeno gas = new GasVespeno(100);
        mapa.agregarConstruccion(nexo, mineral, gas);
        Mutalisco mutalisco = new Mutalisco(mineral, gas, new Posicion(3, 3));

        // EL tiempo de construccion es 4, con un solo tiempo no esta construido
        mutalisco.pasarTiempo();

        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mutalisco.atacarEdificio(nexo);
        //escudo completo
        assertTrue(nexo.tieneEscudoCompleto());
    }

    // caso 18
    @Test
    public void mutaliscoAtacaNexoMineral23VecesYGenera207UnidadesDeDaño() throws MenaOcupadaException, RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException {

        Mapa mapa = new Mapa();
        NexoMineral nexo = new NexoMineral(new Posicion(1,1), new Mena(new Posicion(1,1)), mapa);
        Mineral mineral = new Mineral(150);
        GasVespeno gas = new GasVespeno(100);
        Mutalisco mutalisco = new Mutalisco(mineral, gas, new Posicion(3,3));
        mapa.agregarConstruccion(nexo, mineral, gas);
        // tiempo de construccion
        mutalisco.pasarTiempo();
        mutalisco.pasarTiempo();
        mutalisco.pasarTiempo();
        mutalisco.pasarTiempo();
        mutalisco.pasarTiempo();
        mutalisco.pasarTiempo();
        mutalisco.pasarTiempo();

        // Su unidad de ataque es de 9, con 23 ataques son 207 de daño
        for (int i = 0; i < 23; i++)
            mutalisco.atacarEdificio(nexo);
        
        //SE TIENE QUE TERMINAR DE CONSTRUIR EL CRIADERO PARA REGENERARSE
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();

        //escudo dañado
        assertFalse(nexo.tieneEscudoCompleto());
        nexo.pasarTiempo();
        //sigue dañado
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
    public void mutaliscoNoAtacaNexoMineralPorqueEstaFueraDeRango() throws MenaOcupadaException, RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException {

        Mapa mapa = new Mapa();
        NexoMineral nexo = new NexoMineral(new Posicion(1,1), new Mena(new Posicion(1,1)), mapa);
        Mineral mineral = new Mineral(150);
        GasVespeno gas = new GasVespeno(100);
        Mutalisco mutalisco = new Mutalisco(mineral, gas, new Posicion(4,3));
        mapa.agregarConstruccion(nexo, mineral, gas);
        // tiempo de construccion
        mutalisco.pasarTiempo();
        mutalisco.pasarTiempo();
        mutalisco.pasarTiempo();
        mutalisco.pasarTiempo();
        mutalisco.pasarTiempo();
        mutalisco.pasarTiempo();
        mutalisco.pasarTiempo();

        // Su unidad de ataque es de 9, con 23 ataques son 207 de daño
        for (int i = 0; i < 23; i++)
            mutalisco.atacarEdificio(nexo);

        //escudo completo
        assertTrue(nexo.tieneEscudoCompleto());
    }
}
