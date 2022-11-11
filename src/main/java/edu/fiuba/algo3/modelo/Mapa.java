package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Mapa {
    
    private ArrayList<Construccion> construcciones;
    private ArrayList<Zona> zonas;

    public Mapa() 
    {
        this.construcciones = new ArrayList<>();
        this.zonas = new ArrayList<>();
        this.zonas.add(new ZonaNeutral());
    }

    public boolean verificarEdificacionCorrelativa(Edificio edificioCorrelativo) {
        return construcciones.stream().anyMatch(clase -> clase.getClass().isInstance(edificioCorrelativo));
    }

    public boolean agregarConstruccion(Construccion construccion, Mineral mineral, GasVespeno gas){
        if(!verificarPosicionDisponible(construccion)){
            return false;
        }

        construcciones.add(construccion);
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

    public void agregarZona(Zona zona){
        zonas.add(zona);
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
