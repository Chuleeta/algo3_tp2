package edu.fiuba.algo3.modelo.Individuos;

import edu.fiuba.algo3.modelo.Estados.EstadoConstruccion;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Exceptions.MenaOcupadaException;
import edu.fiuba.algo3.modelo.Recursos.Mena;
import edu.fiuba.algo3.modelo.Recursos.Volcan;

public class Zangano {

    private int tiempoDeConstruccion;
    private int tiempo;
    private int vida;
    private Mena mena;
    private int minerales;
    private EstadoConstruccion estado;

    public Zangano() {
        this.mena = null;
        this.vida = 25;
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 1;
        this.tiempo = 0;
        
    }
    private void construir() {
        this.estado = new EstadoConstruido();
    }
    public void pasarTiempo() {
        this.tiempo += 1;
        if (estado.puedeConstruirse(this.tiempoDeConstruccion, this.tiempo )) construir();
    }
    public void ocuparMena(Mena mena) throws MenaOcupadaException {
        if (this.estado.estaConstruido()) {
            mena.ocupar();
            this.mena = mena;
        }
    }
    public int minarMena() throws MenaOcupadaException {
        if (this.mena != null && this.estado.estaConstruido()) {
            int extraido = this.mena.extraerMineral(10);
            this.minerales += extraido;
            return extraido;
        }
        return 0;
    }
    public int extraerGas(Volcan volcan) {
        if (this.estado.estaConstruido()) {
            return volcan.extraerGas(10);
        }
        return 0;
    }

}
