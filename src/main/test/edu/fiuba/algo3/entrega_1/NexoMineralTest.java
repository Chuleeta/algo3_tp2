package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Criadero;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Mena;
import edu.fiuba.algo3.modelo.Message;
import edu.fiuba.algo3.modelo.NexoMineral;
import edu.fiuba.algo3.modelo.Posicion;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NexoMineralTest {
    //Caso de uso 7
    @Test
    public void SeMinaUnMineralConUnNexoMineralYLoMinaExitosamente(){
        NexoMineral nexito = new NexoMineral(new Posicion(1, 1), new Mena(new Posicion(1, 1)), new Mapa());
        nexito.pasarTiempo(50);
        nexito.pasarTiempo(50);
        nexito.pasarTiempo(50);
        nexito.pasarTiempo(50);
        //nexito.pasarTiempo();
        assertEquals(nexito.obtenerMineral(), 50); //no tenemos la menor idea de como se mina ni cuanto devuelve
    }

    @Test
    public void nexoMineralNoConstruidoNoMina(){
        NexoMineral nexito = new NexoMineral(new Posicion(1, 1), new Mena(new Posicion(1, 1)), new Mapa());
        nexito.pasarTiempo(50);
        nexito.pasarTiempo(50);
        nexito.pasarTiempo(50);
        assertEquals(nexito.obtenerMineral(), 0); //no tenemos la menor idea de como se mina ni cuanto devuelve
    }

    @Test
    public void nexoMineralNoMinaMenaAgotada(){
        NexoMineral nexito = new NexoMineral(new Posicion(1, 1), new Mena(new Posicion(1, 1)), new Mapa());
        nexito.pasarTiempo(50);
        nexito.pasarTiempo(50);
        nexito.pasarTiempo(50);
        nexito.pasarTiempo(50);
        for (int i = 0; i < 70; i++)
        {
            nexito.pasarTiempo();
        }
        assertEquals(nexito.obtenerMineral(), 2050); //no tenemos la menor idea de como se mina ni cuanto devuelve
    }
}
