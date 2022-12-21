package edu.fiuba.algo3.modelo.Individuos;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruccion;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

public abstract class Individuo  implements Ocupable
{
    protected EstadoConstruccion estado;
    protected Vida vida;
    protected int unidadesDeDañoTerrestre;
    protected int unidadesDeDañoAereo;
    protected float rangoDeAtaque;
    protected Posicion posicion;
    protected Mapa mapa;
    protected Jugador jugador;

    public Posicion posicion()
    {
        return this.posicion;
    }

    @Override
    public boolean estaOcupada(Posicion posicionDada) {
        return this.posicion.posicionesIguales(posicionDada);
    }

    public Vida obtenerVida(){
        return vida;
    }

    public void recibirDaño(int cantidad)
    {
        vida.dañar(cantidad);
    }

    public boolean estaDentroDelRango(Posicion posicion) {
        return posicion.adentro(rangoDeAtaque, this.posicion);
    }

    public boolean atacar(Construccion edificio) {
        if (estado.estaConstruido()) {
            if (estaDentroDelRango(edificio.posicion())) {
                edificio.recibirAtaqueTerrestre(unidadesDeDañoTerrestre);
                return true;
            }
        }
        return false;
    }

    public boolean tieneVidaCompleta()
    {
        return vida.tieneVidaCompleta();
    }

    public abstract boolean atacar(Individuo individuo);

    public abstract boolean recibirAtaqueAereo(int unidades);

    public abstract boolean recibirAtaqueTerrestre(int unidades);

    public void crearJugadorPorDefecto() {
        jugador = new Jugador("Default Jugador", "Color Default", "Raza default", new Posicion(6,6), new Mapa(), 200);
    }

    public abstract void pasarTiempo();

    public Jugador mostrarJugador(){
        return this.jugador;
    }

    public boolean moverUnidad(Posicion nuevaPosicion) {
        System.out.println(nuevaPosicion.coordenadaX());
        this.posicion = nuevaPosicion;
        return true;
    }
    public abstract boolean agregarAlMapa(Mineral mineral, GasVespeno gas);

    public boolean validarCorrelativaEvolucion(Devorador devorador) {
        return false;
    }

    public boolean validarCorrelativaEvolucion(Guardian guardian) {
        return false;
    }
    
}
