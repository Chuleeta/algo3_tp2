package edu.fiuba.algo3.modelo.Individuos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Vida;
import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruccion;

public abstract class Individuo 
{
    protected EstadoConstruccion estado;
    protected Vida vida;
    protected int unidadesDeDaño;
    protected float rangoDeAtaque;
    protected Posicion posicion;
    protected Mapa mapa;
    protected Jugador jugador;

    public Posicion posicion()
    {
        return this.posicion;
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

    public boolean atacar(Edificio edificio) {
        if (estado.estaConstruido()) {
            if (estaDentroDelRango(edificio.posicion())) {
                edificio.dañar(unidadesDeDaño);
                return true;
            }
        }
        return false;
    }

    public boolean tieneVidaCompleta()
    {
        return vida.tieneVidaCompleta();
    }

    public abstract boolean atacar(UnidadTierra unidad);
    public abstract boolean atacar(UnidadVoladora unidad);
    public void crearJugadorPorDefecto() {
        jugador = new Jugador("Default Jugador", "Color Default", "Raza default", new Posicion(6,6), new Mapa(), 200);
    }

    public abstract void pasarTiempo();

    public Jugador mostrarJugador(){
        return this.jugador;
    }
}
