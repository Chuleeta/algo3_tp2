package edu.fiuba.algo3.modelo.Individuos;

import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Exceptions.AccesoNoDisponibleException;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.PuertoEstelarNoDisponibleException;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Exceptions.ReservaDeReproduccionNoDisponibleException;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.VidaZerg;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

public class Zerling extends Individuo implements UnidadTierra{
    private int tiempo;

    private int tiempoDeConstruccion;

    public Zerling(Mineral mineral, Posicion posicion, Mapa mapa) throws RequerimientosInsuficientesException {
        if (!mineral.invertir(25)) {
            throw new RequerimientosInsuficientesException();
        }
        this.posicion = posicion;
        this.unidadesDeDaño = 4;
        this.vida = new VidaZerg(35);
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 2;
        this.tiempo = 0;
        rangoDeAtaque = 1;
        this.mapa = mapa;
    }

    public Zerling(Posicion posicion, Jugador jugador) throws RequerimientosInsuficientesException, ReservaDeReproduccionNoDisponibleException, NoExisteEdificioCorrelativoException {
        this.posicion = posicion;
        this.unidadesDeDaño = 4;
        this.vida = new VidaZerg(35);
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 2;
        this.tiempo = 0;
        rangoDeAtaque = 1;
        this.mapa = jugador.getMapa();
        this.jugador = jugador;
        jugador.añadirUnidad();
        this.jugador.verificarEdificacionCorrelativa(this);
        if(!this.jugador.agregarIndividuo(this) || !this.mapa.agregarOcupable(this, posicion)){
            throw new RequerimientosInsuficientesException();
        }
    }

    public void pasarTiempo() {
        this.tiempo += 1;
        if (estado.puedeConstruirse(this.tiempoDeConstruccion, this.tiempo )) construir();
    }
    private void construir() {
        this.estado = new EstadoConstruido();
    }

    public boolean atacar(UnidadTierra unidad)
    {
        if (estado.estaConstruido() && estaDentroDelRango(unidad.posicion()) && estaHabilitadoParaAtacar(unidad)) {
            unidad.recibirDaño(this.unidadesDeDaño);
            return true;
        }
        return false;
    }

    private boolean estaHabilitadoParaAtacar(UnidadTierra unidad) {
        return unidad.estaHabilitado(this);
    }

    public boolean atacar(UnidadVoladora unidad)
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
    public boolean estaHabilitado(UnidadTierra unidad) {
        return true;
    }

    @Override
    public boolean estaHabilitado(UnidadVoladora unidad) {
        return true;
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
}
