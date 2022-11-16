package edu.fiuba.algo3.modelo.Individuos;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.VidaEscudoProtoss;
import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruccion;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

public class Dragon {
    private int rangoDeAtaque;
    private Posicion posicion;
    private VidaEscudoProtoss vida;
    private int unidadesDeDaño;
    private final int tiempoDeConstruccion;
    private int tiempo;
    private EstadoConstruccion estado;

    public Dragon(Mineral mineral, GasVespeno gas, Posicion posicion) throws RequerimientosInsuficientesException {
        if (!mineral.invertir(125) | !gas.invertir(50)) {
            throw new RequerimientosInsuficientesException();
        }
        this.unidadesDeDaño = 20;
        this.vida = new VidaEscudoProtoss(100, 80);
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 6;
        this.tiempo = 0;
        this.rangoDeAtaque = 4;
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
