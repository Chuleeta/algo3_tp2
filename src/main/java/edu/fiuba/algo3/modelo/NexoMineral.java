package edu.fiuba.algo3.modelo;

public class NexoMineral extends Edificio{

    private Mineral minerales;
    private Mena mena;
    private static int VIDA_ESCUDO_COMPLETO = 250;
    private VidaEscudoProtoss vidaYEscudo;

    public NexoMineral(Posicion posicion, Mena mena, Mapa mapa)
    {
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        minerales = new Mineral(0);
        this.mena = mena;
        this.mapa = mapa;
        this.vidaYEscudo = new VidaEscudoProtoss(VIDA_ESCUDO_COMPLETO, VIDA_ESCUDO_COMPLETO);
    }

    public void pasarTiempo(int cantidad)
    {
        tiempo += 1;
        if (estado.puedeConstruirse(4, tiempo)) construir();
        recogerMineral(cantidad);
        minerales.minarMena(mena);
        this.vidaYEscudo.repararEscudo();
    }

    @Override
    public void construir()
    {
        estado = new EstadoConstruido();
        zona = new ZonaNeutral();
        mapa.agregarZona(zona);
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
        this.vidaYEscudo.dañar(daño);
    }

    public boolean tieneVidaCompleta(){
        return this.vidaYEscudo.tieneVidaCompleta();
    }
    public boolean tieneEscudoCompleto(){
        return this.vidaYEscudo.tieneEscudoCompleto();
    }
}
