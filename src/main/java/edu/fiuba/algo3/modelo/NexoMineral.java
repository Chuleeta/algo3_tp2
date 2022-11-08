package edu.fiuba.algo3.modelo;

public class NexoMineral implements Construccion{
    private Posicion posicion;
    private EstadoConstruccion estado;
    private int minerales;
    private Mena mena;
    private Mapa mapa;

    public NexoMineral(Posicion posicion, Mena mena, Mapa mapa)
    {
        this.posicion = posicion;
        this.estado = new EstadoNoConstruido();
        this.minerales = 0;
        this.mena = mena;
        this.mapa = mapa;
    }

    public void pasarTiempo() 
    {
        this.estado.pasarTiempo(this);
        this.estado = this.estado.construir(3);
    }

    @Override
    public void actualizar()
    {
        this.minerales += this.mena.minarMena();
    }

    @Override
    public void construir()
    {
        this.minerales = 0;
        return;
    }

    public int recogerMineral()
    {
        return this.minerales;
    }


    @Override
    public boolean habita(ZonaMoho zona) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean habita(ZonaEnergia zona) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean habita(ZonaNeutral zona) {
        // TODO Auto-generated method stub
        return false;
    }
}
