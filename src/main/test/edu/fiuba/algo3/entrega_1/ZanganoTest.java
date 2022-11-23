package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Exceptions.MenaOcupadaException;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Individuos.Zangano;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Recursos.Mena;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
 public class ZanganoTest {
     //Caso de uso 7
     @Test
     public void SeMinaUnMineralConUnZanganoYLoMinaExitosamente() throws MenaOcupadaException, RequerimientosInsuficientesException {
         Zangano zanguini = new Zangano(new Mineral(25));
         Mena mena = new Mena(new Posicion(1,1));
         zanguini.pasarTiempo();
         zanguini.ocuparMena(mena);
         assertEquals(zanguini.minarMena(), 10);
     }
     //Caso de uso 22
     @Test
     public void zanganoNoPuedeMinarMenaSiNoEstaConstruido() throws MenaOcupadaException, RequerimientosInsuficientesException {
         Zangano zanguini = new Zangano(new Mineral(25));
         Mena mena = new Mena(new Posicion(1,1));
         zanguini.ocuparMena(mena);
         assertEquals(zanguini.minarMena(), 0);
     }
 }
