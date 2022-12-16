package edu.fiuba.algo3.modelo.Individuos;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

import java.util.HashMap;

public class Zealot extends Individuo implements UnidadTierra{
    private final int tiempoDeConstruccion;
    private int tiempo;
    private int asesinatos;
    public boolean invisible;
    private HashMap<Object, Vida> atacados;

    public Zealot(Mineral mineral, Posicion posicion, Mapa mapa) throws RequerimientosInsuficientesException {
        if (!mineral.invertir(100)) {
            throw new RequerimientosInsuficientesException();
        }
        atacados = new HashMap<>();
        asesinatos = 0;
        this.unidadesDeDaño = 8;
        this.vida = new VidaEscudoProtoss(100, 60);
        this.estado = new EstadoNoConstruido();
        this.tiempoDeConstruccion = 4;
        this.tiempo = 0;
        rangoDeAtaque = 1;
        invisible = false;
        this.posicion = posicion;
        this.mapa = mapa;
        crearJugadorPorDefecto();
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
        if (estado.estaConstruido() && estaDentroDelRango(unidad.posicion())) {
            unidad.recibirDaño(this.unidadesDeDaño);
            atacados.put(unidad, unidad.obtenerVida());
            if (atacados.get(unidad).vida <= 0){
                atacados.remove(unidad);
                contarAsesinato();
            }
            return true;
        }
        return false;
    }

    private void contarAsesinato() {
        asesinatos += 1;
        if(asesinatos == 3) invisible = true;
    }

    public boolean atacar(Edificio edificio){
        if (estado.estaConstruido() && estaDentroDelRango(edificio.posicion())) {
            edificio.dañar(this.unidadesDeDaño);
            atacados.put(edificio, edificio.obtenerVida());
            if(atacados.get(edificio).vida <= 0) {
                atacados.remove(edificio);
                contarAsesinato();
            }
            return true;
        }
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
        return mapa.laZonaEstaVigilada(posicion) || estaDentroDelRango(unidad.posicion());
    }

    @Override
    public boolean estaHabilitado(UnidadVoladora unidad) {
        return mapa.laZonaEstaVigilada(posicion) || estaDentroDelRango(unidad.posicion());
    }

}
