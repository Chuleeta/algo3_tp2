package edu.fiuba.algo3.modelo.Individuos;

import edu.fiuba.algo3.modelo.Exceptions.EspiralNoDisponibleException;
import edu.fiuba.algo3.modelo.Exceptions.GuaridaNoDisponibleException;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.VidaZerg;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

public class Devorador extends Individuo implements UnidadVoladora{

    private final int tiempoDeConstruccion;
    private int tiempo;

    public Devorador (Mineral mineral, GasVespeno gas, Posicion posicion, Mapa mapa) throws RequerimientosInsuficientesException{
        if (!mineral.invertir(150) | !gas.invertir(50)) {
            throw new RequerimientosInsuficientesException();
        }
        this.mapa = mapa;
        this.vida = new VidaZerg(100);
        this.unidadesDeDaño = 15;
        this.rangoDeAtaque = 5;
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 4;
        this.tiempo = 0;
        this.posicion = posicion;
    }

    public Devorador(Posicion posicion, Jugador jugador) throws RequerimientosInsuficientesException, EspiralNoDisponibleException {
        this.mapa = jugador.getMapa();
        this.vida = new VidaZerg(100);
        this.unidadesDeDaño = 15;
        this.rangoDeAtaque = 5;
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 4;
        this.tiempo = 0;
        this.posicion = posicion;
        this.jugador = jugador;
        Individuo correlativo = this.jugador.validarCorrelativaGuardian(posicion);
        if(correlativo == null){
            throw new EspiralNoDisponibleException();
        }
        this.jugador.eliminarIndividuo(correlativo);
        jugador.añadirUnidad();
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

    @Override
    public boolean mover(Posicion posicion) {
        this.posicion = posicion;
        return true;
    }

    @Override
    public boolean atacar(UnidadTierra unidad) {
        return false;
    }

    @Override
    public boolean atacar(UnidadVoladora unidad) {
        if (estado.estaConstruido() && estaDentroDelRango(unidad.posicion())) {
            unidad.recibirDaño(this.unidadesDeDaño);
            return true;
        }
        return false;
    }

    @Override
    public boolean agregarAlMapa(Mineral mineral, GasVespeno gas) {
        if(mineral.invertir(150)&& gas.invertir(50))
        {
            this.jugador.agregarEnListaIndividuo(this);
            return true;
        }
        return false;
    }
}
