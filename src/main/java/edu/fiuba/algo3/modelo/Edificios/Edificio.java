package edu.fiuba.algo3.modelo.Edificios;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruccion;
import edu.fiuba.algo3.modelo.Estados.EstadoDesactivado;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Zonas.Zona;

public abstract class Edificio implements Construccion, Ocupable {

    public Mapa mapa;
    public Posicion posicion;
    public EstadoConstruccion estado;
    public Zona zona;
    public Vida vida;
    public int tiempo;
    protected Jugador jugador;

    @Override
    public abstract void construir() throws NoExisteEdificioCorrelativoException;

    @Override
    public abstract void pasarTiempo() throws NoExisteEdificioCorrelativoException;

    public void dañar(int daño){
        this.vida.dañar(daño);
        if(this.vida.verificarSiEstaMuerto()){
            destruir();
        }
    }

    public abstract boolean tieneVidaCompleta();

    public void destruir() {
        this.jugador.destruirConstruccion(this);
        this.mapa.destruirConstruccion(this);
    }

    public void desactivar() {
        this.estado = new EstadoDesactivado();
    }

    public abstract void actualizar();

    public Posicion posicion() {
        return posicion;
    }

    public Vida obtenerVida(){ return vida;}

    @Override
    public boolean estaOcupada(Posicion posicionDada) {
        return this.posicion.equals(posicionDada);
    }

    // public void crearJugadorPorDefecto() {
    //     jugador = new Jugador("Default Jugador", "Color Default", "Raza default", new Posicion(6, 6), mapa, 200);
    // }
    public Posicion mostrarPosicion () {
        return posicion;
    }

    public Jugador mostrarJugador() {
        return jugador;
    }

}
