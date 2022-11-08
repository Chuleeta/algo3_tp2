package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Criadero;
import edu.fiuba.algo3.modelo.CriaderoVacioNoEngendraException;
import edu.fiuba.algo3.modelo.Message;
import edu.fiuba.algo3.modelo.Posicion;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CriaderoTest {

    //Comienzo caso de uso 1

    @Test
    public void criaderoSeInicializaConTresLarvas() 
    {
        Criadero criadero = new Criadero(new Posicion(1, 1));
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        assertTrue(criadero.llenoDeLarvas());    
    }

    @Test
    public void seEngendraUnZanganoEnCriaderoYDisminuyeLarva() throws CriaderoVacioNoEngendraException 
    {
        Criadero criadero = new Criadero(new Posicion(2,2));
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.engendrarZangano();
        assertFalse(criadero.llenoDeLarvas());
    }

    @Test
    public void seRegeneraUnaLarvaLuegoDeUnTiempo() throws CriaderoVacioNoEngendraException 
    {
        Criadero criadero = new Criadero(new Posicion(2,2));
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.engendrarZangano();
        criadero.pasarTiempo();
        assertTrue(criadero.llenoDeLarvas());
    }

    @Test
    public void noSePuedeEngendrarMasDeTresZanganos() throws CriaderoVacioNoEngendraException {
        Criadero criadero = new Criadero(new Posicion(2,2));
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.engendrarZangano();
        criadero.engendrarZangano();
        criadero.engendrarZangano();
        assertThrows(CriaderoVacioNoEngendraException.class, ()->{ criadero.engendrarZangano(); });
    }

    @Test
    public void seConsumenTodasLasLarvasYSeRegeneranDespsDeTresTurnos() throws CriaderoVacioNoEngendraException {
        Criadero criadero = new Criadero(new Posicion(2,2));
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.engendrarZangano();
        criadero.engendrarZangano();
        criadero.engendrarZangano();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        assertTrue(criadero.llenoDeLarvas());
    }

    // Caso de uso 2

    @Test
    public void seConstruyeEnElCuartoTurno() {
        Criadero criadero = new Criadero(new Posicion(2,2));
        criadero.pasarTiempo();
        assertFalse(criadero.llenoDeLarvas());
        criadero.pasarTiempo();
        assertFalse(criadero.llenoDeLarvas());
        criadero.pasarTiempo();
        assertFalse(criadero.llenoDeLarvas());
        criadero.pasarTiempo();
        assertTrue(criadero.llenoDeLarvas());
    }

}

