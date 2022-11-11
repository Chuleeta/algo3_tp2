package edu.fiuba.algo3.modelo;

public class Asimilador extends Edificio{

    private static int VIDA_COMPLETA = 500;
    private GasVespeno gas;
    private Volcan volcan;

    //private int gas;
    private VidaEscudoProtoss vidaYEscudo;
    public Asimilador(Posicion posicion, Volcan volcan, Mapa mapa) throws VolcanOcupadoException {
        this.volcan = volcan;
        this.volcan.ocupar();
        gas = new GasVespeno(0);
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.mapa = mapa;
        this.vidaYEscudo = new VidaEscudoProtoss(450, 450);
        this.zona = new ZonaNeutral();

    }

    @Override
    public void construir() 
    {
        estado = new EstadoConstruido();
    }

    public void pasarTiempo() 
    {
        tiempo += 1;
        this.vidaYEscudo.repararEscudo();
        this.encapsularGas();
        if (estado.puedeConstruirse(6, tiempo)) construir();
    }

    public Integer obtenerGas()
    {
        return gas.getCantidad();
    }

    public void encapsularGas(){
        if(estado.estaActivado() ){
            gas.colectarGas(volcan);
            gas.colectarGas(volcan);  
        }//hay q cambiar esto
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

    @Override
    public boolean agregarAlMapa(Mineral mineral, GasVespeno gas) {
        if(mineral.invertir(100))
        {
            this.mapa.agregarEnListaConstruccion(this);
            return true;
        }
        return false;
    }

}
