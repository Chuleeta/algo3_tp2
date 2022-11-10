package edu.fiuba.algo3.modelo;

public class NexoMineral extends Edificio{

    private Mineral minerales;
    private Mena mena;
    private static int VIDA_COMPLETA = 500;

    public NexoMineral(Posicion posicion, Mena mena, Mapa mapa)
    {
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        minerales = new Mineral(0);
        this.mena = mena;
        this.mapa = mapa;
        vida = 500;
    }

    public void pasarTiempo(int cantidad)
    {
        tiempo += 1;
        regenerarVida();
        if (estado.puedeConstruirse(4, tiempo)) construir();
        recogerMineral(cantidad);
    }

    public void pasarTiempo(){
        tiempo += 1;
        minerales.minarMena(mena);
    }

    @Override
    public void construir()
    {
        estado = new EstadoConstruido();
        zona = new ZonaNeutral();
        mapa.agregarZona(zona);
    }

    private void regenerarVida() {
        vida += 100;
        if(vida > VIDA_COMPLETA) vida = VIDA_COMPLETA;
    }

    public Integer obtenerMineral()
    {
        return minerales.getCantidad();
    }

    public void recogerMineral(int cantidad)
    {
        if(estado.estaConstruido()) minerales.colectar(cantidad);
    }

    @Override
    public boolean habita(Zona zona) {
        return this.zona.equals(zona);
    }

    public void dañar(int daño){
        vida -= daño;
    }

    public boolean tieneVidaCompleta(){
        return vida == VIDA_COMPLETA;
    }
}
