package edu.fiuba.algo3.modelo.Recursos;

import edu.fiuba.algo3.modelo.Ocupable;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruccion;
import edu.fiuba.algo3.modelo.Exceptions.MenaOcupadaException;

public class Mena implements Ocupable{
    private Posicion posicion;
    private int minerales;
    private boolean estaOcupado;

    public Mena(Posicion posicion)
    {
        this.posicion = posicion;
        this.minerales = 2000;
        this.estaOcupado = false;
    }

    public int extraerMineral(int cantidad)
    {
        if (this.minerales >= cantidad)
        {
            this.minerales -= cantidad; 
            return cantidad;
        }
        return this.minerales;   
    }

    public void ocupar() throws MenaOcupadaException {
        if (this.estaOcupado) {
            throw new MenaOcupadaException();
        }
        this.estaOcupado = true;
    }

    public boolean estaOcupada(Posicion posicionDada) {
        return this.posicion.equals(posicionDada);
    }

    public Posicion getPosicion() {
        return this.posicion;
    }
}
