package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Edificios.Criadero;
import edu.fiuba.algo3.modelo.Edificios.Extractor;
import edu.fiuba.algo3.modelo.Exceptions.CriaderoNoDisponibleException;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.Exceptions.VolcanOcupadoException;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import edu.fiuba.algo3.modelo.Recursos.Volcan;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CriaderoTest {

    //Comienzo caso de uso 1
    @Test
    public void criaderoSeInicializaConTresLarvas() throws NoExisteEdificioCorrelativoException 
    {
        Criadero criadero = new Criadero(new Posicion(1, 1), new Mapa());
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        assertTrue(criadero.llenoDeLarvas());    
    }

    @Test
    public void seEngendraUnZanganoEnCriaderoYDisminuyeLarva() throws CriaderoNoDisponibleException, NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Criadero criadero = new Criadero(new Posicion(2,2), new Mapa());
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        Mineral mineral = new Mineral(25);
        criadero.engendrarZangano(mineral);
        assertFalse(criadero.llenoDeLarvas());
    }

    @Test
    public void seRegeneraUnaLarvaLuegoDeUnTiempo() throws CriaderoNoDisponibleException, NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Criadero criadero = new Criadero(new Posicion(2,2), new Mapa());
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        Mineral mineral = new Mineral(25);
        criadero.engendrarZangano(mineral);
        criadero.pasarTiempo();
        assertTrue(criadero.llenoDeLarvas());
    }

    @Test
    public void noSePuedeEngendrarMasDeTresZanganos() throws CriaderoNoDisponibleException, NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Criadero criadero = new Criadero(new Posicion(2,2), new Mapa());
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        Mineral mineral1 = new Mineral(25);
        Mineral mineral2 = new Mineral(25);
        Mineral mineral3 = new Mineral(25);
        Mineral mineral4 = new Mineral(25);
        criadero.engendrarZangano(mineral1);
        criadero.engendrarZangano(mineral2);
        criadero.engendrarZangano(mineral3);
        assertThrows(CriaderoNoDisponibleException.class, ()->{ criadero.engendrarZangano(mineral4); });
    }

    @Test
    public void seConsumenTodasLasLarvasYSeRegeneranDespsDeTresTurnos() throws CriaderoNoDisponibleException, NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        Criadero criadero = new Criadero(new Posicion(2,2), new Mapa());
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        Mineral mineral1 = new Mineral(25);
        Mineral mineral2 = new Mineral(25);
        Mineral mineral3 = new Mineral(25);
        criadero.engendrarZangano(mineral1);
        criadero.engendrarZangano(mineral2);
        criadero.engendrarZangano(mineral3);
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        assertTrue(criadero.llenoDeLarvas());
    }

    // Caso de uso 2

    @Test
    public void seConstruyeEnElCuartoTurno() throws NoExisteEdificioCorrelativoException {
        Criadero criadero = new Criadero(new Posicion(2,2), new Mapa());
        criadero.pasarTiempo();
        assertFalse(criadero.llenoDeLarvas());
        criadero.pasarTiempo();
        assertFalse(criadero.llenoDeLarvas());
        criadero.pasarTiempo();
        assertFalse(criadero.llenoDeLarvas());
        criadero.pasarTiempo();
        assertEquals(criadero.getLarvas().size(), 3);
    }

    @Test
    public void noSeEngendraZanganoSinConstruirseCriadero() throws CriaderoNoDisponibleException{
        Mineral mineral = new Mineral(25);
        Criadero criadero = new Criadero(new Posicion(2,2), new Mapa());
        assertThrows(CriaderoNoDisponibleException.class, ()->{ criadero.engendrarZangano(mineral); });
    }

    //caso de uso 10
    @Test
    public void seRegeneraTodaLaVidaDespuesDeAlgunosTurnos() throws NoExisteEdificioCorrelativoException{
        //given
        Criadero criadero = new Criadero(new Posicion(1,1), new Mapa());
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();

        //when
        criadero.dañar(200);
        criadero.pasarTiempo();
        criadero.pasarTiempo();

        //then
        assertTrue(criadero.tieneVidaCompleta());
    }

    @Test
    public void seRegeneraLaVidaParcialmenteDespuesDeUnTurno() throws NoExisteEdificioCorrelativoException{
        //given
        Criadero criadero = new Criadero(new Posicion(1,1), new Mapa());
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();

        //when
        criadero.dañar(200);
        criadero.pasarTiempo();

        //then
        assertFalse(criadero.tieneVidaCompleta());
    }

    //Caso de uso 13
    @Test
    public void seDestruyeUnCriaderoYSePuedeConstruirIgualArribaDelMoho() throws VolcanOcupadoException, NoExisteEdificioCorrelativoException {
        Mineral mineral = new Mineral(10000);
        GasVespeno gas = new GasVespeno(10000);
        Mapa mapa = new Mapa();
        Criadero criadero = new Criadero(new Posicion(9, 9), mapa);
        mapa.agregarConstruccion(criadero, mineral, gas);
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();              
        mapa.pasarTiempo();
        criadero.dañar(550);
        assertTrue(mapa.agregarConstruccion(new Extractor(new Posicion(9, 14), new Volcan(new Posicion(1, 1)), mapa), mineral, gas));
    }
}

