package edu.fiuba.algo3.modelo.Individuos;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Exceptions.AccesoNoDisponibleException;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

import java.util.HashMap;

public class Zealot extends Individuo{
    private final int tiempoDeConstruccion;
    private int tiempo;
    private int asesinatos;
    public boolean invisible;
    private HashMap<Object, Vida> atacados;

    public Zealot(Mineral mineral, Posicion posicion, Mapa mapa) throws RequerimientosInsuficientesException {
        if (!mineral.invertir(100)) {
            throw new RequerimientosInsuficientesException();
        }
        atacados = new HashMap<>();
        asesinatos = 0;
        this.unidadesDeDañoTerrestre = 8;
        this.unidadesDeDañoAereo = 0;
        this.vida = new VidaEscudoProtoss(100, 60);
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 4;
        this.tiempo = 0;
        rangoDeAtaque = 1;
        invisible = false;
        this.posicion = posicion;
        this.mapa = mapa;
    }

    public Zealot(Posicion posicion, Jugador jugador) throws RequerimientosInsuficientesException, AccesoNoDisponibleException, NoExisteEdificioCorrelativoException {
        atacados = new HashMap<>();
        asesinatos = 0;
        this.vida = new VidaEscudoProtoss(100, 60);
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 4;
        this.unidadesDeDañoTerrestre = 8;
        this.unidadesDeDañoAereo = 0;
        this.tiempo = 0;
        rangoDeAtaque = 1;
        invisible = false;
        this.posicion = posicion;
        this.mapa = jugador.getMapa();
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

    public void pasarTiempo() {
        this.tiempo += 1;
        if (estado.puedeConstruirse(this.tiempoDeConstruccion, this.tiempo )) construir();
    }

    public boolean atacar(Individuo unidad)
    {
        if (estado.estaConstruido() && estaDentroDelRango(unidad.posicion())) {
            if (unidad.recibirAtaqueTerrestre(unidadesDeDañoTerrestre)){
                atacados.put(unidad, unidad.obtenerVida());
                if(atacados.get(unidad).vida <= 0) {
                    atacados.remove(unidad);
                    contarAsesinato();
                }
                return true;
            }
        }
        return false;
    }

    private void contarAsesinato() {
        asesinatos += 1;
        if(asesinatos == 3) invisible = true;
    }

    public boolean atacar(Edificio edificio){
        if (estado.estaConstruido() && estaDentroDelRango(edificio.posicion())) {
            edificio.dañar(unidadesDeDañoTerrestre);
            atacados.put(edificio, edificio.obtenerVida());
            if(atacados.get(edificio).vida <= 0) {
                atacados.remove(edificio);
                contarAsesinato();
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean recibirAtaqueAereo(int unidades) {
        return false;
    }

    @Override
    public boolean recibirAtaqueTerrestre(int unidades) {
        if(!mapa.laZonaEstaVigilada(posicion) && invisible) return false;
        vida.dañar(unidades);
        return true;
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
        if(mineral.invertir(100)&& gas.invertir(0))
        {
            this.jugador.agregarEnListaIndividuo(this);
            return true;
        }
        return false;
    }

}
