package edu.fiuba.algo3.modelo;

public class NexoMineral implements Construccion{
    private Posicion posicion;
    private EstadoConstruccion estado;
    private int minerales;
    private Mena mena;

    public NexoMineral(Posicion posicion, Mena mena)
    {
        this.posicion = posicion;
        this.estado = new EstadoNoConstruido();
        this.minerales = 0;
        this.mena = mena;
    }

    public void pasarTiempo() 
    {
        this.estado.pasarTiempo(this);
        this.estado = this.estado.construir(3);
    }

    public void actualizar()
    {
        this.minerales += this.mena.minarMena();
    }

    public void construir()
    {
        this.minerales = 0;
        return;
    }

    public int recogerMineral()
    {
        return this.minerales;
    }
}
