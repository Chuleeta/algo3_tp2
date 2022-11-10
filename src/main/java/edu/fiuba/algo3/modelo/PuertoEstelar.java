package edu.fiuba.algo3.modelo;

public class PuertoEstelar extends Edificio {
    private VidaEscudoProtoss vidaYEscudo;

    public PuertoEstelar(Posicion posicion, Mapa mapa) {
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.mapa = mapa;
        tiempo = 0;
        TURNOS_PARA_CONSTRUIRSE = 10;
        this.vidaYEscudo = new VidaEscudoProtoss(600, 600);
    }

    @Override
    public void pasarTiempo() {
        this.vidaYEscudo.repararEscudo();
    }


    @Override
    public void construir()
    {

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
