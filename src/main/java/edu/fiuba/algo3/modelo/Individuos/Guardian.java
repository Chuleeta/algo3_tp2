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

public class Guardian extends Individuo implements UnidadVoladora{
    private final int tiempoDeConstruccion;
    private int tiempo;

    public Guardian(Mineral mineral, GasVespeno gas, Posicion posicion, Mapa mapa) throws RequerimientosInsuficientesException {
        if (!mineral.invertir(50) | !gas.invertir(100)) {
            throw new RequerimientosInsuficientesException();
        }
        this.mapa = mapa;
        this.vida = new VidaZerg(100);
        this.unidadesDeDaño = 25;
        this.rangoDeAtaque = 10;
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 4;
        this.tiempo = 0;
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
        this.posicion = posicion;
        return true;
    }

}
