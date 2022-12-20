package edu.fiuba.algo3.modelo.Individuos;

import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Exceptions.GuaridaNoDisponibleException;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Exceptions.ReservaDeReproduccionNoDisponibleException;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.VidaZerg;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import javafx.geometry.Pos;

public class Hidralisco extends Individuo{
    private final int tiempoDeConstruccion;
    private int tiempo;

    public Hidralisco(Mineral mineral, GasVespeno gas, Posicion posicionInicial, Mapa mapa) throws RequerimientosInsuficientesException {
        if (!mineral.invertir(75) | !gas.invertir(25)) {
            throw new RequerimientosInsuficientesException();
        }
        this.unidadesDeDañoTerrestre = 10;
        this.unidadesDeDañoAereo = 10;
        this.mapa = mapa;
        this.vida = new VidaZerg(80);
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 4;
        this.tiempo = 0;
        posicion = posicionInicial;
        rangoDeAtaque = 4;
    }

    public Hidralisco(Posicion posicion, Jugador jugador) throws RequerimientosInsuficientesException, GuaridaNoDisponibleException {
        this(jugador.invertirMineral(), jugador.invertirGas(), posicion, jugador.getMapa());
        this.jugador = jugador;
        if(!this.jugador.validarCorrelativaGuarida()){
            throw new GuaridaNoDisponibleException();
        }
        jugador.añadirUnidad();
        jugador.agregarIndividuo(this);
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
            if(individuo.recibirAtaqueTerrestre(unidadesDeDañoTerrestre)){
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
}
