package edu.fiuba.algo3.modelo.Edificios;

import edu.fiuba.algo3.modelo.EstadoConstruido;
import edu.fiuba.algo3.modelo.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.GasVespeno;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Mena;
import edu.fiuba.algo3.modelo.MenaOcupadaException;
import edu.fiuba.algo3.modelo.Mineral;
import edu.fiuba.algo3.modelo.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.VidaEscudoProtoss;
import edu.fiuba.algo3.modelo.Zona;
import edu.fiuba.algo3.modelo.ZonaEnergia;
import edu.fiuba.algo3.modelo.ZonaMoho;
import edu.fiuba.algo3.modelo.ZonaNeutral;

public class NexoMineral extends Edificio{
    private Mineral mineral;
    private Mena mena;
    private static int VIDA_ESCUDO_COMPLETO = 250;
    private VidaEscudoProtoss vidaYEscudo;

    public NexoMineral(Posicion posicion, Mena mena, Mapa mapa) throws MenaOcupadaException {
        this.mena = mena;
        this.mena.ocupar();
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.zona = new ZonaNeutral();
        this.mapa = mapa;
        this.vidaYEscudo = new VidaEscudoProtoss(VIDA_ESCUDO_COMPLETO, VIDA_ESCUDO_COMPLETO);
    }

    public void pasarTiempo() throws NoExisteEdificioCorrelativoException
    {
        tiempo += 1;
        this.estado = this.estado.desarrollar(this, 4, tiempo);
    }

    @Override
    public void construir()
    {
        estado = new EstadoConstruido();
        mapa.agregarZona(zona);
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
        this.mineral = mineral;
        if(mineral.invertir(50))
        {
            this.mapa.agregarEnListaConstruccion(this);
            return true;
        }
        return false;
    }

    @Override
    public void actualizar() {
        mineral.agregarMineral(mena.extraerMineral(100));
        this.vidaYEscudo.repararEscudo();
    }
}
