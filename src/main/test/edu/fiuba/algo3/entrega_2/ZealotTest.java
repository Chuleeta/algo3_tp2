package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ZealotTest {

    @Test
    public void ZealotAtacaCriadero25VecesYGenera200UnidadesDeAtaque() {

        Criadero criadero = new Criadero(new Posicion(1,1), new Mapa());

        Zealot zealot = new Zealot();

        // Su unidad de ataque es de 8, con 25 ataques son 200 de daño
        for (int i = 0; i < 25; i++)
            zealot.atacarEdificio(criadero);

        //vida incompleta
        assertFalse(criadero.tieneVidaCompleta());
        criadero.pasarTiempo();
        //sigue incompleta
        assertFalse(criadero.tieneVidaCompleta());
        criadero.pasarTiempo();
        //vida completa
        assertTrue(criadero.tieneVidaCompleta());

    }
}
