package edu.fiuba.algo3.modelo;

public class PuertoEstelar extends Edificio {
    private VidaEscudoProtoss vidaYEscudo;

    public PuertoEstelar(Posicion posicion, Mapa mapa) {
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.zona = new ZonaEnergia(this.posicion);
        this.mapa = mapa;
        tiempo = 0;
        this.vidaYEscudo = new VidaEscudoProtoss(600, 600);
    }

    @Override
    public void pasarTiempo() {
        tiempo += 1;
        this.vidaYEscudo.repararEscudo();
        if (estado.puedeConstruirse(10, tiempo)) construir();
    }


    @Override
    public void construir()
    {
        this.estado = new EstadoConstruido();
    }

    @Override
    public boolean habita(Zona zona) {
        return true;
    }

    public void dañar(int daño){
        this.vidaYEscudo.dañar(daño);
    }

    public boolean tieneVidaCompleta() {
        return this.vidaYEscudo.tieneVidaCompleta();
    }

    public boolean tieneEscudoCompleto() {
        return this.vidaYEscudo.tieneEscudoCompleto();
    }

    @Override
    public boolean agregarAlMapa(Mineral mineral, GasVespeno gas) {
        if(mineral.invertir(150) && gas.invertir(150))
        {
            this.mapa.agregarEnListaConstruccion(this);
            return true;
        }
        return false;
    }
}
