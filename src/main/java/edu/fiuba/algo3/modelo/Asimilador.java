package edu.fiuba.algo3.modelo;

public class Asimilador extends Edificio{

    private static int VIDA_COMPLETA = 500;
    private GasVespeno gas;

    public Asimilador(Posicion posicion, Mapa mapa)
    { 
        gas = new GasVespeno(0);
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.mapa = mapa;
        vida = 900;
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
        vida -= daño;
    }

    private void regenerarVida() {
        vida += 100;
        if(vida > VIDA_COMPLETA) vida = VIDA_COMPLETA;
    }

    public boolean tieneVidaCompleta(){
        return vida == VIDA_COMPLETA;
    }

}
