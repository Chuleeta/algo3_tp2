package edu.fiuba.algo3.modelo;

public class Asimilador extends Edificio{

    private static int VIDA_COMPLETA = 500;
    private int gas;
    private VidaEscudoProtoss vidaYEscudo;
    public Asimilador(Posicion posicion, Mapa mapa)
    { 
        this.gas = 0;
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.mapa = mapa;
        TURNOS_PARA_CONSTRUIRSE = 6;
        this.vidaYEscudo = new VidaEscudoProtoss(450, 450);
    }

    @Override
    public void construir() 
    {

    }

    public void pasarTiempo() 
    {
        this.vidaYEscudo.repararEscudo();
    }

    public Integer obtenerGas() 
    {
        return this.gas;
    }

    @Override
    public boolean habita(Zona zona) {
        return true;
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
