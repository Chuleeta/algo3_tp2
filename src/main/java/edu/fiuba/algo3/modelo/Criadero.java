package edu.fiuba.algo3.modelo;


public class Criadero {

    private Posicion posicion;
    private EstadoConstruccion estado;
    private int larvas;

    public Criadero (Posicion posicion) {
        this.posicion = posicion;
        this.estado = new EstadoNoConstruido();
        this.larvas = 0;
    }

    public void pasarTiempo() {
        this.larvas = this.estado.pasarTiempo(this.larvas);
        this.estado = this.estado.construir();
    }

    public boolean llenoDeLarvas() {
        return (this.larvas == 3);
    }

    public Zangano engendrarZangano() throws CriaderoVacioNoEngendraException {
        if (this.larvas == 0) {
            throw new CriaderoVacioNoEngendraException();
        }
        this.larvas -= 1;
        return new Zangano();
    }
    
}
