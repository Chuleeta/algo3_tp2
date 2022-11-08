package edu.fiuba.algo3.modelo;


public class Criadero implements Construccion {

    private Posicion posicion;
    private EstadoConstruccion estado;
    private int larvas;

    public Criadero (Posicion posicion) {
        this.posicion = posicion;
        this.estado = new EstadoNoConstruido();
        this.larvas = 0;
    }

    public void pasarTiempo() {
        this.estado.pasarTiempo(this);
        this.estado = this.estado.construir(3);
    }

    public boolean llenoDeLarvas() {
        return (this.larvas == 3);
    }

    public Zangano engendrarZangano() throws CriaderoNoDisponibleException {
        if (this.larvas == 0) {
            throw new CriaderoNoDisponibleException();
        }
        this.larvas -= 1;
        return new Zangano();
    }

    @Override
    public void construir()
    {
        this.larvas = 3;
    }

    @Override
    public void actualizar()
    {
        if(this.larvas < 3)
            this.larvas += 1;
    }
    
}
