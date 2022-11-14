package edu.fiuba.algo3.modelo.Edificios;

import edu.fiuba.algo3.modelo.Construccion;
import edu.fiuba.algo3.modelo.EstadoConstruccion;
import edu.fiuba.algo3.modelo.EstadoDesactivado;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Zona;
import edu.fiuba.algo3.modelo.ZonaEnergia;
import edu.fiuba.algo3.modelo.ZonaMoho;
import edu.fiuba.algo3.modelo.ZonaNeutral;

public abstract class Edificio implements Construccion{

    public Mapa mapa;
    public Posicion posicion;
    public EstadoConstruccion estado;
    public Zona zona;
    public int tiempo;

    @Override
    public abstract void construir() throws NoExisteEdificioCorrelativoException;

    //@Override
    //public abstract void actualizar();

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

    // public boolean estaActivado()
    // {
    //     return this.estado.estaActivado();
    // }

    public abstract void actualizar();
}
