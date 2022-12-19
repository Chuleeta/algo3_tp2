package edu.fiuba.algo3.modelo.Individuos;

import edu.fiuba.algo3.modelo.Exceptions.CriaderoNoDisponibleException;
import edu.fiuba.algo3.modelo.Exceptions.PuertoEstelarNoDisponibleException;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import edu.fiuba.algo3.modelo.VidaZerg;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Exceptions.MenaOcupadaException;
import edu.fiuba.algo3.modelo.Recursos.Mena;
import edu.fiuba.algo3.modelo.Recursos.Volcan;

public class Zangano extends Individuo implements UnidadTierra{

    private int tiempoDeConstruccion;
    private int tiempo;
    private Mena mena;

    public Zangano(Mineral mineral) throws RequerimientosInsuficientesException {
        if (!mineral.invertir(25))
            throw new RequerimientosInsuficientesException();
        this.mena = null;
        this.vida = new VidaZerg(25);
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 1;
        this.tiempo = 0;
    }

    public Zangano(Mineral mineral, Posicion posicion) throws RequerimientosInsuficientesException {
        this(mineral);
        this.posicion = posicion;
    }

    public Zangano(Mineral mineral, Posicion posicion, Jugador jugador) throws RequerimientosInsuficientesException, CriaderoNoDisponibleException {
        this(mineral);
        this.posicion = posicion;
        this.jugador = jugador;
        if(this.jugador.validarCorrelativaCriadero()){
            throw new CriaderoNoDisponibleException();
        }
    }

    private void construir() {
        this.estado = new EstadoConstruido();
    }

    public void pasarTiempo() {
        this.tiempo += 1;
        if (estado.puedeConstruirse(this.tiempoDeConstruccion, this.tiempo )) construir();
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

    public boolean atacar(UnidadTierra unidad)
    {
        return false;
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

}
