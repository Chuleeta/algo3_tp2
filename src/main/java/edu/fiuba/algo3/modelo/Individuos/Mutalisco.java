package edu.fiuba.algo3.modelo.Individuos;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruccion;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

public class Mutalisco {

    private final int unidadesDeDaño;
    private final int vida;
    private final int tiempoDeConstruccion;
    private int tiempo;
    private EstadoConstruccion estado;
    private int rangoDeAtaque;
    private Posicion posicion;

    public Mutalisco(Mineral mineral, GasVespeno gas, Posicion posicion) throws RequerimientosInsuficientesException {
        if (!mineral.invertir(100) | !gas.invertir(100)) {
            throw new RequerimientosInsuficientesException();
        }
        this.vida = 120;
        this.unidadesDeDaño = 9;
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 7;
        this.tiempo = 0;
        rangoDeAtaque= 3;
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
