package edu.fiuba.algo3.modelo;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Mapa {
    
    private ArrayList<Construccion> construcciones;
    private ArrayList<Zona> zonas;

    public boolean agregarConstruccion(Construccion construccion){
        if(!this.verificarPosicionDisponible(construccion)){
            return false;
        }
        construcciones.add(construccion);
        return true;
    }

    public void pasarTiempo(){

    }

    private boolean verificarPosicionDisponible(Construccion construccion){
        construcciones.forEach();
        return true;
    }
}
