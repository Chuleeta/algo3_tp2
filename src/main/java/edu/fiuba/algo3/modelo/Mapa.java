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

    public boolean agregarConstruccion(Construccion construccion){
        if(!this.verificarPosicionDisponible(construccion)){
            return false;
        }
        construcciones.add(construccion);
        return true;
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
}
