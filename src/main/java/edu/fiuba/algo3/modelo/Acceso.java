package edu.fiuba.algo3.modelo;

public class Acceso extends Edificio{

    private static int VIDA_COMPLETA = 500;
    private VidaEscudoProtoss vidaYEscudo;

    public Acceso(Posicion posicion, Mapa mapa)
    {
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.mapa = mapa;
        tiempo = 0;
        this.zona = new ZonaEnergia(this.posicion);
        this.vidaYEscudo = new VidaEscudoProtoss(500, 500);
    }

    public void pasarTiempo() {
        this.tiempo += 1;
        if (estado.puedeConstruirse(8, tiempo)) construir();
        this.vidaYEscudo.repararEscudo();
    }

    @Override
    public void construir()
    {
        estado = new EstadoConstruido();
    }

    @Override
    public boolean habita(Zona zona) {
        if(this.mapa.hayMohoEnPosicion(this.posicion)) return false;
        if(!this.zona.getClass().equals(zona.getClass())) return false;
        return zona.abarca(posicion);
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

    @Override
    public boolean agregarAlMapa(Mineral mineral, GasVespeno gas) {
        if(mineral.invertir(150))
        {
            this.mapa.agregarEnListaConstruccion(this);
            return true;
        }
        return false;
    }

}
