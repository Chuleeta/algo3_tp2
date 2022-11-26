package edu.fiuba.algo3.modelo.Individuos;

import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import edu.fiuba.algo3.modelo.VidaZerg;
import edu.fiuba.algo3.modelo.Zonas.Zona;
import edu.fiuba.algo3.modelo.Zonas.ZonaVigilada;

public class AmoSupremo extends Individuo implements UnidadVoladora {
    private final int tiempoDeConstruccion;
    private int tiempo;

    public AmoSupremo(Mineral mineral, GasVespeno gas, Posicion posicion, Mapa mapa) throws RequerimientosInsuficientesException {
        if (!mineral.invertir(50) | !gas.invertir(0)) {
            throw new RequerimientosInsuficientesException();
        }
        this.vida = new VidaZerg(200);
        this.mapa = mapa;
        this.unidadesDeDaño = 0;
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 5;
        this.tiempo = 0;
        rangoDeAtaque= 0;
        this.posicion = posicion;
        crearJugadorPorDefecto();
    }

    public AmoSupremo(Mineral mineral, GasVespeno gas, Posicion posicion, Mapa mapa, Jugador jugador) throws RequerimientosInsuficientesException {
        this(mineral, gas, posicion, mapa);
        this.jugador = jugador;
    }

    private void construir() {
        this.estado = new EstadoConstruido();
        mapa.agregarZona(new ZonaVigilada(posicion));
        jugador.incrementarCapacidadDePoblacion(5);
    }
    public void pasarTiempo() {
        this.tiempo += 1;
        if (estado.puedeConstruirse(this.tiempoDeConstruccion, this.tiempo )) construir();
    }

    public void bajar()
    {
        this.posicion.descender();
    }

    public void elevar()
    {
        this.posicion.ascender();
    }

    public boolean atacar(UnidadTierra unidad) { return false; }

    public boolean atacar(UnidadVoladora unidad) { return false; }

    public boolean mover(Posicion posicion)
    {
        this.posicion = posicion;
        return true;
    }

    public void destruir() {
        jugador.decrementarCapacidadDePoblacion(5);
    }
}
