package edu.fiuba.algo3.modelo;

public class Asimilador extends Edificio{

    private static int VIDA_COMPLETA = 500;
    private GasVespeno gas;

    //private int gas;
    private VidaEscudoProtoss vidaYEscudo;
    public Asimilador(Posicion posicion, Mapa mapa)
    { 
        gas = new GasVespeno(0);
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.mapa = mapa;
        this.vidaYEscudo = new VidaEscudoProtoss(450, 450);
    }

    @Override
    public void construir() 
    {
        estado = new EstadoConstruido();
    }

    public void pasarTiempo() 
    {
        tiempo += 1;
        if (estado.puedeConstruirse(6, tiempo)) construir();
        encapsularGas();
        this.vidaYEscudo.repararEscudo();
    }

    public Integer obtenerGas()
    {
        return gas.getCantidad();
    }

    public void encapsularGas(){
        if(estado.estaConstruido()) gas.colectar(20);
    }

    @Override
    public boolean habita(Zona zona) {
        return true;
    }

    public void dañar(int daño){
        this.vidaYEscudo.dañar(daño);
    }

    private void regenerarVida() {
        vida += 100;
        if(vida > VIDA_COMPLETA) vida = VIDA_COMPLETA;
    }

    public boolean tieneVidaCompleta(){
        return this.vidaYEscudo.tieneVidaCompleta();
    }

    public boolean tieneEscudoCompleto(){
        return this.vidaYEscudo.tieneEscudoCompleto();
    }

}
