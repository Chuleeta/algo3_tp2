package edu.fiuba.algo3.modelo.Individuos;

import edu.fiuba.algo3.modelo.Exceptions.CriaderoNoDisponibleException;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import edu.fiuba.algo3.modelo.VidaZerg;
import edu.fiuba.algo3.modelo.Edificios.Extractor;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Exceptions.MenaOcupadaException;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mena;
import edu.fiuba.algo3.modelo.Recursos.Volcan;

public class Zangano extends Individuo{

    private int tiempoDeConstruccion;
    private int tiempo;
    private Mena mena;
    private Extractor extractor;

    public Zangano(Mineral mineral) throws RequerimientosInsuficientesException {
        if (!mineral.invertir(25))
            throw new RequerimientosInsuficientesException();
        this.mena = null;
        this.vida = new VidaZerg(25);
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 1;
        this.unidadesDeDañoAereo = 0;
        this.unidadesDeDañoTerrestre = 0;
        this.tiempo = 0;
    }

    public Zangano(Mineral mineral, Posicion posicion) throws RequerimientosInsuficientesException {
        this(mineral);
        this.posicion = posicion;
    }


    public Zangano(Posicion posicion, Jugador jugador) throws RequerimientosInsuficientesException, CriaderoNoDisponibleException, NoExisteEdificioCorrelativoException {
        this.vida = new VidaZerg(25);
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 1;
        this.unidadesDeDañoAereo = 0;
        this.unidadesDeDañoTerrestre = 0;
        this.tiempo = 0;
        this.posicion = posicion;
        this.jugador = jugador;
        this.mapa = jugador.getMapa();
        jugador.añadirUnidad();
        this.jugador.verificarEdificacionCorrelativa(this);
        if(!this.jugador.agregarIndividuo(this) || !this.mapa.agregarOcupable(this, posicion)){
            throw new RequerimientosInsuficientesException();
        }
    }

    private void construir() {
        this.estado = new EstadoConstruido();
    }

    @Override
    public boolean recibirAtaqueAereo(int unidades) {
        return false;
    }

    @Override
    public boolean recibirAtaqueTerrestre(int unidades, Posicion posicionAtacante) {
        vida.dañar(unidades);
        return true;
    }

    public void pasarTiempo() {
        this.tiempo += 1;
        if (estado.puedeConstruirse(this.tiempoDeConstruccion, this.tiempo )) construir();
        if (this.jugador != null) this.jugador.añadirMineral(minarMena());
    }
    public void ocuparMena(Mena mena) throws MenaOcupadaException {
        if (this.estado.estaConstruido()) {
            mena.ocupar();
            this.mena = mena;
        }
    }
    public int minarMena() {
        if (this.mena != null && this.estado.estaConstruido()) {
            int extraido = this.mena.extraerMineral(10);
            return extraido;
        }
        return 0;
    }
    public int extraerGas(Volcan volcan) {
        if (this.estado.estaConstruido()) {
            return volcan.extraerGas(10);
        }
        return 0;
    }

    public boolean atacar(Individuo individuo)
    {
        return false;
    }

    public boolean mover(Posicion posicion)
    {
        if(mapa.enAreaEspacial(posicion))
            return false;
        this.posicion = posicion;
        return true;
    }

    @Override
    public boolean moverUnidad(Posicion nuevaPosicion) {
        if (this.estado.estaConstruido()) {
            if(super.moverUnidad(nuevaPosicion)){
                if (this.mena != null) {
                    this.mena.desocupar();
                    this.mena = null;
                }
                if (this.extractor != null) {
                    this.extractor.desocupar(this);
                    this.extractor = null;
                }
                try {
                    mapa.inyectarRecurso(this);
                    return true;
                } catch (MenaOcupadaException e) {
                    this.mena = null;
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean agregarAlMapa(Mineral mineral, GasVespeno gas) {
        if(mineral.invertir(25)&& gas.invertir(0))
        {
            this.jugador.agregarEnListaIndividuo(this);
            return true;
        }
        return false;
    }

    @Override
    public String getSpray(){
        return "/imagenes/zangano.png";
    }

    public void ocuparExtractor(Extractor extractorTrabajando) {
        this.extractor = extractorTrabajando;
    }

}
