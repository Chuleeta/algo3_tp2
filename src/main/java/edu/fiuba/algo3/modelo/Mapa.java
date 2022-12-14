package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.Extractor;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.VolcanOcupadoException;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mena;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import edu.fiuba.algo3.modelo.Recursos.Volcan;
import edu.fiuba.algo3.modelo.Zonas.Zona;
import edu.fiuba.algo3.modelo.Zonas.ZonaNeutral;

public class Mapa {

    private ArrayList<Construccion> construcciones;
    private ArrayList<Zona> zonas;
    private ArrayList<AreaEspacial> areasEspaciales;
    private ArrayList<Volcan> volcanes;
    private ArrayList<Mena> menas;

    public Mapa()
    {
        this.construcciones = new ArrayList<>();
        this.zonas = new ArrayList<>();
        this.areasEspaciales = new ArrayList<>();
        this.zonas.add(new ZonaNeutral());
        this.volcanes = new ArrayList<>();
        this.menas = new ArrayList<>();

    }

    public boolean agregarVolcan(Volcan volcanDado){
        if(volcanDado == null || !verificarPosicion(volcanDado.getPosicion()))
            return false;
        
        this.volcanes.add(volcanDado);
        return true;
    }

    public boolean agregarMena(Mena menaDada){
        if(menaDada == null || !verificarPosicion(menaDada.getPosicion()))
            return false;
        
        this.menas.add(menaDada);
        return true;
    }

    private boolean verificarPosicion(Posicion posicionDada){
        if(verificarPosicionVolcanes(posicionDada) && verificarPosicionMenas(posicionDada) && verificarPosicionConstrucciones(posicionDada))
            return true;
        return false;
    }

    private boolean verificarPosicionVolcanes(Posicion posicionDada){
        for (Volcan volcan:volcanes){
            if(volcan.estaOcupado(posicionDada))
                return false;
        }
        return true;
    }

    private boolean verificarPosicionMenas(Posicion posicionDada){
        for (Mena mena:menas){
            if(mena.estaOcupada(posicionDada))
                return false;
        }
        return true;
    }

    private boolean verificarPosicionConstrucciones(Posicion posicionDada){
        for (Construccion construccion:construcciones){
            if(construccion.estaOcupada(posicionDada))
                return false;
        }
        return true;
    }

    public boolean verificarEdificacionCorrelativa(Edificio edificioCorrelativo) {
        return construcciones.stream().anyMatch(clase -> clase.getClass().isInstance(edificioCorrelativo));
    }

    public boolean agregarConstruccion(Construccion construccion, Mineral mineral, GasVespeno gas){
        if(!verificarPosicionDisponible(construccion)){
            return false;
        }
        return construccion.agregarAlMapa(mineral, gas);
    }

    public void pasarTiempo() throws NoExisteEdificioCorrelativoException {
        for (Construccion construccion:construcciones)
        {
            construccion.pasarTiempo();
        }
        this.filtrarConstrucciones();
    }

    private boolean verificarPosicionDisponible(Construccion construccion){
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

    public void agregarEnListaConstruccion(Construccion construccion) {
        construcciones.add(construccion);
    }

    public void destruirConstruccion(Construccion construccion)
    {
        construcciones.remove(construccion);
    }

    public void destruirZona(Zona zona)
    {
        zonas.remove(zona);
    }

    public void filtrarConstrucciones()
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
}
