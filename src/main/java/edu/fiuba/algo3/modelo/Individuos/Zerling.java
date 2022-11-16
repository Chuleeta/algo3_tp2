package edu.fiuba.algo3.modelo.Individuos;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruccion;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

public class Zerling {
    private final Posicion posicion;
    private final int rangoDeAtaque;
    private int tiempo;
    private int vida;
    private int unidadesDeDaño;

    private EstadoConstruccion estado;
    private int tiempoDeConstruccion;

    public Zerling(Mineral mineral, Posicion posicion) throws RequerimientosInsuficientesException {
        if (!mineral.invertir(25)) {
            throw new RequerimientosInsuficientesException();
        }
        this.posicion = posicion;
        this.unidadesDeDaño = 4;
        this.vida = 35;
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 2;
        this.tiempo = 0;
        rangoDeAtaque = 1;
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

    public void pasarTiempo() {
        this.tiempo += 1;
        if (estado.puedeConstruirse(this.tiempoDeConstruccion, this.tiempo )) construir();
    }
    private void construir() {
        this.estado = new EstadoConstruido();
    }
}
