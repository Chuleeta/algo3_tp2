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

    public boolean agregarConstruccion(Construccion construccion, Mineral mineral, GasVespeno gas){
        if(!verificarPosicionDisponible(construccion)){
            return false;
        }
        //construcciones.add(construccion);
        return construccion.agregarAlMapa(mineral, gas);
    }

    public void pasarTiempo()
    {
        for (Construccion construccion:construcciones)
        {
            construccion.pasarTiempo();
        }
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
        for (Construccion construccion:construcciones)
        {
            construccion.desactivar();
        }
    }
}
