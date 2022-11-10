package edu.fiuba.algo3.modelo;

public class Pilon extends Edificio{

    private static int VIDA_ESCUDO_COMPLETA = 300;

    private VidaEscudoProtoss vidaYEscudo;

    public Pilon (Posicion posicion, Mapa mapa) {
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.mapa = mapa;
        tiempo = 0;
        TURNOS_PARA_CONSTRUIRSE = 5;
        this.vidaYEscudo = new VidaEscudoProtoss(300, 300);
    }
    public void pasarTiempo() {
        this.vidaYEscudo.repararEscudo();
    }


    @Override
    public void construir()
    {
        zona = new ZonaEnergia(this.posicion);
        mapa.agregarZona(this.zona);
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
}
