package edu.fiuba.algo3.modelo.Individuos;

import edu.fiuba.algo3.modelo.Exceptions.AccesoNoDisponibleException;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.VidaEscudoProtoss;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

public class Dragon extends Individuo {
    private final int tiempoDeConstruccion;
    private int tiempo;

    public Dragon(Mineral mineral, GasVespeno gas, Posicion posicion, Mapa mapa) throws RequerimientosInsuficientesException {
        if (!mineral.invertir(125) | !gas.invertir(50)) {
            throw new RequerimientosInsuficientesException();
        }
        this.mapa = mapa;
        this.unidadesDeDañoTerrestre = 20;
        this.unidadesDeDañoAereo = 20;
        this.vida = new VidaEscudoProtoss(100, 80);
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 6;
        this.tiempo = 0;
        this.rangoDeAtaque = 4;
        this.posicion = posicion;
    }

    public Dragon(Posicion posicion, Jugador jugador) throws RequerimientosInsuficientesException, AccesoNoDisponibleException, NoExisteEdificioCorrelativoException {
        this.mapa = jugador.getMapa();
        this.vida = new VidaEscudoProtoss(100, 80);
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 6;
        this.tiempo = 0;
        this.rangoDeAtaque = 4;
        this.posicion = posicion;
        this.jugador = jugador;
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
    public boolean recibirAtaqueTerrestre(int unidades) {
        vida.dañar(unidades);
        return true;
    }

    public void pasarTiempo() {
        this.tiempo += 1;
        if (estado.puedeConstruirse(this.tiempoDeConstruccion, this.tiempo )) construir();
    }

    public boolean atacar(Individuo individuo)
    {
        if (estado.estaConstruido() && estaDentroDelRango(individuo.posicion())) {
            if (individuo.recibirAtaqueTerrestre(unidadesDeDañoTerrestre)){
                return true;
            }
            return individuo.recibirAtaqueAereo(unidadesDeDañoAereo);
        }
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
    public boolean agregarAlMapa(Mineral mineral, GasVespeno gas) {
        if(mineral.invertir(125)&& gas.invertir(50))
        {
            this.jugador.agregarEnListaIndividuo(this);
            return true;
        }
        return false;
    }
}
