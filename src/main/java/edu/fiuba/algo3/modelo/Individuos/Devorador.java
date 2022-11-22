package edu.fiuba.algo3.modelo.Individuos;

import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import edu.fiuba.algo3.modelo.VidaEscudoProtoss;
import edu.fiuba.algo3.modelo.VidaZerg;

public class Devorador extends Individuo implements UnidadVoladora {

    private final int tiempoDeConstruccion;
    private int tiempo;

    public Devorador(Mineral mineral, GasVespeno gas, Posicion posicion, Mapa mapa) throws RequerimientosInsuficientesException {
        if (!mineral.invertir(125) | !gas.invertir(50)) {
            throw new RequerimientosInsuficientesException();
        }
        this.mapa = mapa;
        this.unidadesDeDaño = 15;
        this.vida = new VidaZerg(200);
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 4;
        this.tiempo = 0;
        this.rangoDeAtaque = 5;
        this.posicion = posicion;
    }
    
    private void construir() {
        this.estado = new EstadoConstruido();
    }
    public void pasarTiempo() {
        this.tiempo += 1;
        if (estado.puedeConstruirse(this.tiempoDeConstruccion, this.tiempo )) construir();
    }

    public void elevar()
    {
        this.posicion.ascender();
    }

    public void bajar()
    {
        this.posicion.descender();
    }

    public boolean atacar(UnidadVoladora unidad)
    {
        if (estaDentroDelRango(unidad.posicion())) {
            unidad.recibirDaño(this.unidadesDeDaño);
            return true;
        }
        return false;
    }

    public boolean atacar(UnidadTierra unidad)
    {
        return false;
    }

    public boolean mover(Posicion posicion)
    {
        this.posicion = posicion;
        return true;
    }
}
