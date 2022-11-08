package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Criadero;
import edu.fiuba.algo3.modelo.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NexoMineralTest {
    //Caso de uso 7
    @Test
    public void SeMinaUnMineralConUnNexoMineralYLoMinaExitosamente(){
        NexoMineral nexito = new NexoMineral();
        Mena mena = new Mena();

        assertTrue(nexito.minarMena(mena), 50); //no tenemos la menor idea de como se mina ni cuanto devuelve
    }
}
