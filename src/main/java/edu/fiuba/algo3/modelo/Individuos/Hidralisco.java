package edu.fiuba.algo3.modelo.Individuos;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruccion;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

public class Hidralisco {
    private final int tiempoDeConstruccion;
    private int tiempo;
    private int vida;
    private int unidadesDeDaño;
    private EstadoConstruccion estado;

    private Posicion posicion;
    private int rangoDeAtaque;

    public Hidralisco(Mineral mineral, GasVespeno gas, Posicion posicionInicial) throws RequerimientosInsuficientesException {
        if (!mineral.invertir(75) | !gas.invertir(25)) {
            throw new RequerimientosInsuficientesException();
        }
        this.unidadesDeDaño = 10;
        this.vida = 80;
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 4;
        this.tiempo = 0;
        posicion = posicionInicial;
        rangoDeAtaque = 4;
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
