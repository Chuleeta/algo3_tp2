package edu.fiuba.algo3.modelo.Individuos;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruccion;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

public class Guardian {
    private int vida;
    private int unidadesDeDaño;
    private int rangoDeAtaque;
    private final int tiempoDeConstruccion;
    private int tiempo;
    private EstadoConstruccion estado;
    private Posicion posicion;

    public Guardian(Mineral mineral, GasVespeno gas, Posicion posicion) throws RequerimientosInsuficientesException {
        if (!mineral.invertir(50) | !gas.invertir(100)) {
            throw new RequerimientosInsuficientesException();
        }
        this.vida = 100;
        this.unidadesDeDaño = 25;
        this.rangoDeAtaque = 10;
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 4;
        this.tiempo = 0;
        this.posicion = posicion;
    }

    private void construir() {
        this.estado = new EstadoConstruido();
    }
    public void pasarTiempo() {
        this.tiempo += 1;
        if (estado.puedeConstruirse(this.tiempoDeConstruccion, this.tiempo )) construir();
    }
    public void atacarEdificio(Edificio edificio) {
        if (estado.estaConstruido()) {
            if (estaDentroDelRango(edificio.posicion())) {
                edificio.dañar(unidadesDeDaño);
            }
        }
    }
    private boolean estaDentroDelRango(Posicion posicion) {
        return posicion.adentro(this.rangoDeAtaque, this.posicion);
    }

}
