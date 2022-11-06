package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Criadero;
import edu.fiuba.algo3.modelo.Message;
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
        Criadero criadero = new Criadero();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        assertTrue(criadero.llenoDeLarvas());    
    }

    @Test
    public void seEngendraUnZanganoEnCriaderoYDisminuyeLarva() 
    {
        Criadero criadero = new Criadero();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.engendrarZangano();
        assertFalse(criadero.llenoDeLarvas());
    }

    @Test
    public void seRegeneraUnaLarvaLuegoDeUnTiempo() 
    {
        Criadero criadero = new Criadero();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.engendrarZangano();
        criadero.pasarTiempo();
        assertTrue(criadero.llenoDeLarvas());
    }

    @Test
    public void noSePuedeEngendrarMasDeTresZanganos() {
        Criadero criadero = new Criadero();
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
    public void seConsumenTodasLasLarvasYSeRegeneranDespsDeTresTurnos() {
        Criadero criadero = new Criadero();
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
        Criadero criadero = new Criadero();
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
