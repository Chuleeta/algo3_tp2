package edu.fiuba.algo3.modelo.Recursos;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Exceptions.VolcanOcupadoException;

public class Volcan {
    private Posicion posicion;
    private int gas;
    private boolean estaOcupado;

    public Volcan(Posicion posicion)
    {
        this.posicion = posicion;
        this.gas = 5000;
        this.estaOcupado = false;
    }

    public void ocupar() throws VolcanOcupadoException {
        if (this.estaOcupado) {
            throw new VolcanOcupadoException();
        }
        this.estaOcupado = true;
    }

    public int extraerGas(int cantidad) {
        if (this.gas >= cantidad)
        {
            this.gas -= cantidad;
            return cantidad;
        }
        return this.gas;
    }

    public Posicion getPosicion() {
        return this.posicion;
    }

    public boolean estaOcupado(Posicion posicionDada) {
        return this.posicion.equals(posicionDada);
    }
}
