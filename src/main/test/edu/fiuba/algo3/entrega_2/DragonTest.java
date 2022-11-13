package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DragonTest {

    @Test
    public void dragonAtaca10VecesACriaderoYSon200UnidadesDeAtaque() {

        Criadero criadero = new Criadero(new Posicion(1,1), new Mapa());

        Dragon dragon = new Dragon();

        // Su unidad de ataque es de 20, con 10 ataques son 200 de daño

        for (int i = 0; i < 10; i++)
            dragon.atacarEdificio(criadero);

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
