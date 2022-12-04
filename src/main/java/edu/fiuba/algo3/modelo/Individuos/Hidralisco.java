package edu.fiuba.algo3.modelo.Individuos;

import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.VidaZerg;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import javafx.geometry.Pos;

public class Hidralisco extends Individuo implements UnidadTierra{
    private final int tiempoDeConstruccion;
    private int tiempo;

    public Hidralisco(Mineral mineral, GasVespeno gas, Posicion posicionInicial, Mapa mapa) throws RequerimientosInsuficientesException {
        if (!mineral.invertir(75) | !gas.invertir(25)) {
            throw new RequerimientosInsuficientesException();
        }
        this.unidadesDeDaño = 10;
        this.mapa = mapa;
        this.vida = new VidaZerg(80);
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 4;
        this.tiempo = 0;
        posicion = posicionInicial;
        rangoDeAtaque = 4;
    }

    private void construir() {
        this.estado = new EstadoConstruido();
    }

    public void pasarTiempo() {
        this.tiempo += 1;
        if (estado.puedeConstruirse(this.tiempoDeConstruccion, this.tiempo )) construir();
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
        if (estado.estaConstruido() && estaDentroDelRango(unidad.posicion())) {
            unidad.recibirDaño(this.unidadesDeDaño);
            return true;
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
    public boolean estaHabilitado(UnidadTierra unidad) {
        return true;
    }

    @Override
    public boolean estaHabilitado(UnidadVoladora unidad) {
        return true;
    }
}
