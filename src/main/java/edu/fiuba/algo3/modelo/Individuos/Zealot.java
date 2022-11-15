package edu.fiuba.algo3.modelo.Individuos;

import edu.fiuba.algo3.modelo.VidaEscudoProtoss;
import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruccion;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

public class Zealot {
    private VidaEscudoProtoss vida;
    private int unidadesDeDaño;
    private final int tiempoDeConstruccion;
    private int tiempo;
    private EstadoConstruccion estado;
    public Zealot(Mineral mineral) throws RequerimientosInsuficientesException {
        if (!mineral.invertir(100)) {
            throw new RequerimientosInsuficientesException();
        }
        this.unidadesDeDaño = 8;
        this.vida = new VidaEscudoProtoss(100, 60);
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 4;
        this.tiempo = 0;
    }
    private void construir() {
        this.estado = new EstadoConstruido();
    }
    public void pasarTiempo() {
        this.tiempo += 1;
        if (estado.puedeConstruirse(this.tiempoDeConstruccion, this.tiempo )) construir();
    }
    public void atacarEdificio(Edificio edificio) {
        if (this.estado.estaConstruido()) {
            edificio.dañar(this.unidadesDeDaño);
        }
    }
}
