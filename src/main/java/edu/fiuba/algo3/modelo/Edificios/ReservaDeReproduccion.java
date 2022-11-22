package edu.fiuba.algo3.modelo.Edificios;

import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Larva;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.VidaZerg;
import edu.fiuba.algo3.modelo.Individuos.Zerling;
import edu.fiuba.algo3.modelo.Zonas.ZonaEnergia;
import edu.fiuba.algo3.modelo.Zonas.ZonaMoho;
import edu.fiuba.algo3.modelo.Zonas.ZonaNeutral;

public class ReservaDeReproduccion extends Edificio{
    private static int VIDA_COMPLETA = 1000;
    private VidaZerg vida;

    public ReservaDeReproduccion(Posicion posicion, Mapa mapa)
    {
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.mapa = mapa;
        zona = new ZonaMoho(this.posicion);
        tiempo = 0;
        this.vida = new VidaZerg(VIDA_COMPLETA);
    }

    public void pasarTiempo() throws NoExisteEdificioCorrelativoException
    {
        tiempo += 1;
        // this.vida.regenerarVida();
        // if (estado.puedeConstruirse(12, tiempo)) construir();
        this.estado = this.estado.desarrollar(this, 12, tiempo);
    }


    @Override
    public void construir()
    {
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
        if(mineral.invertir(150))
        {
            this.mapa.agregarEnListaConstruccion(this);
            return true;
        }
        return false;
    }
    public Zerling generarZerling(Mineral mineral, Larva larva) throws RequerimientosInsuficientesException {
            return new Zerling(mineral, this.posicion.clone(), this.mapa);
    }

    @Override
    public void actualizar() {
        this.vida.regenerar();
    }

    // @Override
    // public boolean estaOcupada(Posicion posicionDada) {
    //     return this.posicion.equals(posicionDada);
    // }


}
