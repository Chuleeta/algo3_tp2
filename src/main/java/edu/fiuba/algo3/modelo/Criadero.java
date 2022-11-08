package edu.fiuba.algo3.modelo;


public class Criadero implements Construccion {

    private Posicion posicion;
    private EstadoConstruccion estado;
    private int larvas;
    private Mapa mapa;
    private ZonaMoho zona;
    private int tiempo;

    public Criadero (Posicion posicion, Mapa mapa) {
        this.posicion = posicion;
        this.estado = new EstadoNoConstruido();
        this.larvas = 0;
        this.mapa = mapa;
        this.tiempo = 0;
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
        this.zona = new ZonaMoho(this.posicion);
        this.mapa.agregarZona(this.zona);
    }

    @Override
    public void actualizar()
    {
        this.tiempo += 1;
        if(this.tiempo % 2 == 0)
            this.zona.propagar();
        if(this.larvas < 3)
            this.larvas += 1;
    }

    @Override
    public boolean habita(ZonaMoho zona) {
        return true;
    }

    @Override
    public boolean habita(ZonaEnergia zona) {
        return true;
    }

    @Override
    public boolean habita(ZonaNeutral zona) {
        return true;
    }
    
}
