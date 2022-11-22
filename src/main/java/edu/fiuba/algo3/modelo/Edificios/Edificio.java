package edu.fiuba.algo3.modelo.Edificios;

import edu.fiuba.algo3.modelo.Construccion;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruccion;
import edu.fiuba.algo3.modelo.Estados.EstadoDesactivado;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Ocupable;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Zonas.Zona;

public abstract class Edificio implements Construccion, Ocupable{

    public Mapa mapa;
    public Posicion posicion;
    public EstadoConstruccion estado;
    public Zona zona;
    public int tiempo;

    @Override
    public abstract void construir() throws NoExisteEdificioCorrelativoException;

    @Override
    public abstract void pasarTiempo() throws NoExisteEdificioCorrelativoException;

    public abstract void dañar(int daño);

    public abstract boolean tieneVidaCompleta();

    public void destruir()
    {
        this.mapa.destruirConstruccion(this);
    }

    public void desactivar()
    {
        this.estado = new EstadoDesactivado();
    }

    public abstract void actualizar();

    public Posicion posicion() {
        return posicion;
    }

    @Override
    public boolean estaOcupada(Posicion posicionDada) {
        return this.posicion.equals(posicionDada);
    }
}
