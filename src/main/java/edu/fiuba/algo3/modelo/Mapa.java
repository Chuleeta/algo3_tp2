package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

import edu.fiuba.algo3.modelo.Edificios.Asimilador;
import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.Extractor;
import edu.fiuba.algo3.modelo.Edificios.NexoMineral;
import edu.fiuba.algo3.modelo.Exceptions.MenaOcupadaException;
import edu.fiuba.algo3.modelo.Exceptions.VolcanOcupadoException;
import edu.fiuba.algo3.modelo.Individuos.Devorador;
import edu.fiuba.algo3.modelo.Individuos.Individuo;
import edu.fiuba.algo3.modelo.Individuos.Zangano;
import edu.fiuba.algo3.modelo.Recursos.RecursoInyectable;
import edu.fiuba.algo3.modelo.Recursos.Volcan;
import edu.fiuba.algo3.modelo.Zonas.Zona;
import edu.fiuba.algo3.modelo.Zonas.ZonaNeutral;

public class Mapa {

    private ArrayList<Construccion> construcciones;
    private ArrayList<Zona> zonas;
    private ArrayList<AreaEspacial> areasEspaciales;
    private ArrayList<Ocupable> ocupables;
    private ArrayList<RecursoInyectable> recursosInyectables;

    public Mapa()
    {
        this.construcciones = new ArrayList<>();
        this.zonas = new ArrayList<>();
        this.areasEspaciales = new ArrayList<>();
        this.zonas.add(new ZonaNeutral());
        this.ocupables = new ArrayList<>();
        this.recursosInyectables = new ArrayList<>();
    }

    public boolean agregarOcupable(Ocupable ocupable, Posicion posicion){
        if(ocupable == null || !verificarPosicion(posicion))
            return false;
        
        this.ocupables.add(ocupable);
        return true;
    }

    public boolean agregarRecursoInyectable(RecursoInyectable recursoInyectable, Posicion posicion){
        if(recursoInyectable == null)
            return false;
        this.recursosInyectables.add(recursoInyectable);
        //System.out.println("\nSE AGREGA");
        return true;
    }

    public boolean inyectarRecurso(Extractor extractor) throws VolcanOcupadoException{
        for (RecursoInyectable recurso:recursosInyectables){
            if(recurso.inyectarRecurso(extractor))
                return true;
        }
        return false;
    }

    public boolean inyectarRecurso(NexoMineral nexo) throws MenaOcupadaException{
        for (RecursoInyectable recurso:recursosInyectables){
            if(recurso.inyectarRecurso(nexo)){
                return true;
            }
        }
        return false;
    }

    public boolean inyectarRecurso(Asimilador asimilador) throws VolcanOcupadoException{
        for (RecursoInyectable recurso:recursosInyectables){
            if(recurso.inyectarRecurso(asimilador))
                return true;
        }
        return false;
    }
    public boolean inyectarRecurso(Zangano zangano) throws MenaOcupadaException{
        for (RecursoInyectable recurso:recursosInyectables){
            if(recurso.mostrarPosicion().posicionesIguales(zangano.posicion()) && recurso.inyectarRecurso(zangano)){
                return true;
            }
        }
        return false;
    }

    private boolean verificarPosicion(Posicion posicionDada){
        for (Ocupable ocupable:ocupables){
            if(ocupable.estaOcupada(posicionDada))
               return false;
            }
        return true;
    }

    public boolean verificarPosicionDisponible(Construccion construccion){
        for (Zona zona:zonas)
        {
            if(zona.puedeHabitar(construccion))
                return true;
        }
        return false;
    }

    public boolean enAreaEspacial(Posicion posicion)
    {
        for (AreaEspacial area : areasEspaciales)
        {
            if(area.abarca(posicion))
                return true;
        }
        return false;
    }

    public void agregarZona(Zona zona){
        zonas.add(zona);
    }

    public void agregarAreaEspacial(AreaEspacial area){
        areasEspaciales.add(area);
    }

    public Zona getZonaNeutral() {
        return this.zonas.get(0);
    }

    public void destruirZona(Zona zona)
    {
        zonas.remove(zona);
    }

    public void filtrarConstrucciones(ArrayList<Construccion> construcciones)
    {
        ArrayList<Construccion> construcciones_a_destruir = new ArrayList<>();
        for (Construccion construccion:construcciones)
        {
            if(!this.verificarPosicionDisponible(construccion))
                construcciones_a_destruir.add(construccion);
        }
        for (Construccion construccion:construcciones_a_destruir)
        {
            construccion.desactivar();
        }
    }

    public boolean hayMohoEnPosicion(Posicion posicion)  {
        try {
            boolean result = verificarPosicionDisponible(new Extractor(posicion, new Volcan(new Posicion(1,1)),this));
            return result;
        } catch (VolcanOcupadoException e) {
            return false;
        }
    }

    public boolean laZonaEstaVigilada(Posicion posicion) {
        for (Zona zona:zonas)
        {
            if(zona.puedeAtacar(posicion))
                return true;
        }
        return false;
    }
    public ArrayList<Construccion> mostrarConstrucciones() {
        return construcciones;
    }

    public ArrayList<RecursoInyectable> mostrarRecursos() {
        return this.recursosInyectables;
    }

    public void destruirConstruccion(Construccion construccion) {
        this.construcciones.remove(construccion);
    }
}
