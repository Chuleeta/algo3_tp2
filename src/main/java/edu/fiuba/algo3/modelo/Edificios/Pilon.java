package edu.fiuba.algo3.modelo.Edificios;

import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.VidaEscudoProtoss;
import edu.fiuba.algo3.modelo.Zonas.ZonaEnergia;
import edu.fiuba.algo3.modelo.Zonas.ZonaMoho;
import edu.fiuba.algo3.modelo.Zonas.ZonaNeutral;

public class Pilon extends Edificio{


    private VidaEscudoProtoss vidaYEscudo;

    public Pilon (Posicion posicion, Mapa mapa) {
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.mapa = mapa;
        tiempo = 0;
        this.zona = mapa.getZonaNeutral();
        this.vidaYEscudo = new VidaEscudoProtoss(300, 300);
    }
    public void pasarTiempo() throws NoExisteEdificioCorrelativoException {
        tiempo += 1;
        // if (estado.puedeConstruirse(5, tiempo)) construir();
        // this.vidaYEscudo.repararEscudo();
        this.estado = this.estado.desarrollar(this, 5, tiempo);
    }


    @Override
    public void construir()
    {
        this.zona = new ZonaEnergia(this.posicion);
        mapa.agregarZona(this.zona);
        this.estado = new EstadoConstruido();
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

    public boolean tieneVidaCompleta() {
        return this.vidaYEscudo.tieneVidaCompleta();
    }

    public boolean tieneEscudoCompleto() {
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

    public void destruir()
    {
        this.mapa.destruirZona(this.zona);
        this.mapa.destruirConstruccion(this);
    }
    @Override
    public void actualizar() {
        this.vidaYEscudo.regenerar();
    }
}
