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

public class Mutalisco extends Individuo implements UnidadVoladora{

    private final int tiempoDeConstruccion;
    private int tiempo;

    public Mutalisco(Mineral mineral, GasVespeno gas, Posicion posicion, Mapa mapa) throws RequerimientosInsuficientesException {
        if (!mineral.invertir(100) | !gas.invertir(100)) {
            throw new RequerimientosInsuficientesException();
        }
        this.vida = new VidaZerg(120);
        this.mapa = mapa;
        this.unidadesDeDaño = 9;
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 7;
        this.tiempo = 0;
        rangoDeAtaque= 3;
        this.posicion = posicion;
    }


    private void construir() {
        this.estado = new EstadoConstruido();
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

    public boolean atacar(UnidadTierra unidad)
    {
        if (estado.estaConstruido() && estaDentroDelRango(unidad.posicion()) && estaHabilitadoParaAtacar(unidad)) {
            unidad.recibirDaño(this.unidadesDeDaño);
            return true;
        }
        return false;
    }

    public boolean atacar(UnidadVoladora unidad)
    {
        if (estado.estaConstruido() && estaDentroDelRango(unidad.posicion())) {
            unidad.recibirDaño(this.unidadesDeDaño);
            return true;
        }
        return false;
    }

    private boolean estaHabilitadoParaAtacar(UnidadTierra unidad) {
        return unidad.estaHabilitado(this);
    }

    public boolean mover(Posicion posicion)
    {
        this.posicion = posicion;
        return true;
    }

    public Guardian evolucionarAGurdian(Mineral mineral, GasVespeno gas) throws RequerimientosInsuficientesException
    {
        return new Guardian(mineral, gas, this.posicion, this.mapa);
    }

    public Devorador evolucionarADevorador(Mineral mineral, GasVespeno gas) throws RequerimientosInsuficientesException
    {
        return new Devorador(mineral, gas, this.posicion, this.mapa);
    }

}
