package edu.fiuba.algo3.modelo.Edificios;

import edu.fiuba.algo3.modelo.EstadoConstruido;
import edu.fiuba.algo3.modelo.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.GasVespeno;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Mineral;
import edu.fiuba.algo3.modelo.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.VidaZerg;
import edu.fiuba.algo3.modelo.Zona;
import edu.fiuba.algo3.modelo.ZonaEnergia;
import edu.fiuba.algo3.modelo.ZonaMoho;
import edu.fiuba.algo3.modelo.ZonaNeutral;

public class Guarida extends Edificio{
    private static int VIDA_COMPLETA = 1250;
    private VidaZerg vida;

    public Guarida(Posicion posicion, Mapa mapa)
    {
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.mapa = mapa;
        this.zona = new ZonaMoho(this.posicion);
        tiempo = 0;
        this.vida = new VidaZerg(VIDA_COMPLETA);
    }

    public void pasarTiempo() throws NoExisteEdificioCorrelativoException {
        tiempo += 1;
        //regenerarVida();
        //if (estado.puedeConstruirse(12, tiempo)) construir();
        this.estado = this.estado.desarrollar(this, 12, tiempo);
    }

    // private void regenerarVida() {
    //     this.vida.regenerarVida();
    // }

    @Override
    public void construir() throws NoExisteEdificioCorrelativoException {
        if (!this.mapa.verificarEdificacionCorrelativa(new ReservaDeReproduccion(new Posicion(0,0), new Mapa()))){
            throw new NoExisteEdificioCorrelativoException();
        }
        estado = new EstadoConstruido();
    }

    @Override
    public boolean habita(ZonaNeutral zona) {
        return false;
    }

    @Override
    public boolean habita(ZonaMoho zona) {
        return zona.abarca(posicion);
    }

    @Override
    public boolean habita(ZonaEnergia zona) {
        return false;
    }

    public void dañar(int daño){
        this.vida.dañar(daño);
    }

    public boolean tieneVidaCompleta(){
        return this.vida.tieneVidaCompleta();
    }

    @Override
    public boolean agregarAlMapa(Mineral mineral, GasVespeno gas) {
        if(mineral.invertir(200)&& gas.invertir(100))
        {
            this.mapa.agregarEnListaConstruccion(this);
            return true;
        }
        return false;
    }

    @Override
    public void actualizar() {
        this.vida.regenerarVida();
        
    }
}
