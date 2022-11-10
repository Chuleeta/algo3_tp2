package edu.fiuba.algo3.modelo;

public abstract class Edificio implements Construccion{

    public Mapa mapa;
    public Posicion posicion;
    public EstadoConstruccion estado;
    public Zona zona;
    public int tiempo;

    @Override
    public abstract void construir();

    //@Override
    //public abstract void actualizar();

    @Override
    public abstract boolean habita(Zona zona);

    @Override
    public abstract void pasarTiempo();

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

    public boolean estaActivado()
    {
        return this.estado.estaActivado();
    }
}
