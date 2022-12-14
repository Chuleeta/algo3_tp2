package edu.fiuba.algo3.modelo.Edificios;

import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.VolcanOcupadoException;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import edu.fiuba.algo3.modelo.Recursos.Volcan;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.VidaEscudoProtoss;
import edu.fiuba.algo3.modelo.Zonas.ZonaEnergia;
import edu.fiuba.algo3.modelo.Zonas.ZonaMoho;
import edu.fiuba.algo3.modelo.Zonas.ZonaNeutral;

public class Asimilador extends Edificio{

    private GasVespeno gas;
    private Volcan volcan;

    private VidaEscudoProtoss vidaYEscudo;
    public Asimilador(Posicion posicion, Volcan volcan, Mapa mapa) throws VolcanOcupadoException {
        this.volcan = volcan;
        this.volcan.ocupar();
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
        this.estado = this.estado.desarrollar(this, 6, tiempo);
    }

    public Integer obtenerGas()
    {
        return gas.getCantidad();
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
            this.gas = gas;
            return true;
        }
        return false;
    }

    @Override
    public void actualizar() {
        this.vidaYEscudo.regenerar();
        this.gas.agregarGas(volcan.extraerGas(20));
    }

    @Override
    public boolean estaOcupada(Posicion posicionDada) {
        return this.posicion.equals(posicionDada);
    }

}
