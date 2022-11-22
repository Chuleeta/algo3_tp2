package entrega_3;

import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Individuos.Devorador;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

public class DevoradorTest {
    Mineral mineral = new Mineral(150);
    GasVespeno gas = new GasVespeno(50);
    Posicion posicion = new Posicion(1,1);
    Mapa mapa = new Mapa();
    Devorador devorador = new Devorador(mineral, gas, posicion, mapa);

    public DevoradorTest() throws RequerimientosInsuficientesException {
    }
}
