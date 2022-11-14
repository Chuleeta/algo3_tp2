package edu.fiuba.algo3.modelo.Edificios;

import edu.fiuba.algo3.modelo.EstadoConstruido;
import edu.fiuba.algo3.modelo.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.GasVespeno;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Mineral;
import edu.fiuba.algo3.modelo.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.VidaEscudoProtoss;
import edu.fiuba.algo3.modelo.Volcan;
import edu.fiuba.algo3.modelo.VolcanOcupadoException;
import edu.fiuba.algo3.modelo.Zona;
import edu.fiuba.algo3.modelo.ZonaEnergia;
import edu.fiuba.algo3.modelo.ZonaMoho;
import edu.fiuba.algo3.modelo.ZonaNeutral;

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

    public void pasarTiempo() throws NoExisteEdificioCorrelativoException 
    {
        tiempo += 1;
        //if (estado.puedeConstruirse(6, tiempo)) construir();
        this.estado = this.estado.desarrollar(this, 6, tiempo);
    }

    public Integer obtenerGas()
    {
        return gas.getCantidad();
    }

    public void encapsularGas(){
        //if(estado.estaActivado() ){
            gas.colectarGas(volcan);
            gas.colectarGas(volcan);
        //}//hay q cambiar esto
    }

    @Override
    public boolean habita(ZonaNeutral zona) {
        return true;
    }

    @Override
    public boolean habita(ZonaMoho zona) {
        return false;
    }

    @Override
    public boolean habita(ZonaEnergia zona) {
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

    @Override
    public void actualizar() {
        this.vidaYEscudo.repararEscudo();
        this.encapsularGas();
    }

}
