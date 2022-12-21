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
import edu.fiuba.algo3.modelo.Zonas.ZonaVigilada;

public class AmoSupremo extends Individuo {
    private final int tiempoDeConstruccion;
    private int tiempo;

    public AmoSupremo(Mineral mineral, GasVespeno gas, Posicion posicion, Mapa mapa) throws RequerimientosInsuficientesException {
        if (!mineral.invertir(50) | !gas.invertir(0)) {
            throw new RequerimientosInsuficientesException();
        }
        this.vida = new VidaZerg(200);
        this.mapa = mapa;
        this.unidadesDeDañoTerrestre = 0;
        this.unidadesDeDañoAereo = 0;
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 5;
        this.tiempo = 0;
        rangoDeAtaque= 0;
        this.posicion = posicion;
    }

    public AmoSupremo(Posicion posicion, Jugador jugador) throws RequerimientosInsuficientesException {
        this.vida = new VidaZerg(200);
        this.mapa = jugador.getMapa();
        this.estado = new EstadoNoConstruido();
        this.unidadesDeDañoTerrestre = 0;
        this.unidadesDeDañoAereo = 0;
        this.tiempoDeConstruccion = 5;
        this.tiempo = 0;
        rangoDeAtaque= 0;
        this.posicion = posicion;
        this.jugador = jugador;
        jugador.añadirUnidad();
        if(!this.jugador.agregarIndividuo(this) || !this.mapa.agregarOcupable(this, posicion)){
            throw new RequerimientosInsuficientesException();
        }
    }

    private void construir() {
        this.estado = new EstadoConstruido();
        mapa.agregarZona(new ZonaVigilada(posicion));
        jugador.incrementarCapacidadDePoblacion(5);
    }

    @Override
    public boolean recibirAtaqueAereo(int unidades) {
        return false;
    }

    @Override
    public boolean recibirAtaqueTerrestre(int unidades, Posicion posicionAtacante) {
        return false;
    }

    public void pasarTiempo() {
        this.tiempo += 1;
        if (estado.puedeConstruirse(this.tiempoDeConstruccion, this.tiempo )) construir();
    }

    public boolean atacar(Individuo individuo) { return false; }

    public boolean mover(Posicion posicion)
    {
        this.posicion = posicion;
        return true;
    }

    public void destruir() {
        jugador.decrementarCapacidadDePoblacion(5);
    }

    @Override
    public boolean agregarAlMapa(Mineral mineral, GasVespeno gas) {
        if(mineral.invertir(50)&& gas.invertir(0))
        {
            this.jugador.agregarEnListaIndividuo(this);
            return true;
        }
        return false;
    }

    @Override
    public String getSpray(){
        return "/imagenes/amosupremo.png";
    }
}
