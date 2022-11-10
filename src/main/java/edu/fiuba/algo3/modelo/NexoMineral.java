package edu.fiuba.algo3.modelo;

public class NexoMineral extends Edificio{

    private int minerales;
    private Mena mena;
    private static int VIDA_ESCUDO_COMPLETO = 250;
    private VidaEscudoProtoss vidaYEscudo;

    public NexoMineral(Posicion posicion, Mena mena, Mapa mapa)
    {
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.minerales = 0;
        this.mena = mena;
        this.mapa = mapa;
        this.vidaYEscudo = new VidaEscudoProtoss(VIDA_ESCUDO_COMPLETO, VIDA_ESCUDO_COMPLETO);
        TURNOS_PARA_CONSTRUIRSE = 4;
    }

    public void pasarTiempo() 
    {
        tiempo += 1;
        this.vidaYEscudo.repararEscudo();
    }

    @Override
    public void construir()
    {
        this.minerales = 0;
        return;
    }

    public int recogerMineral()
    {
        return this.minerales;
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
