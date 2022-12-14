package edu.fiuba.algo3.modelo;

import java.util.Random;

import edu.fiuba.algo3.modelo.Recursos.Mena;
import edu.fiuba.algo3.modelo.Recursos.Volcan;

public class Base {
    private Jugador jugadorDeReferencia;

    public Base(Jugador jugador) {
        this.jugadorDeReferencia = jugador;
    }

    public boolean agregarAlMapaElementos(Mapa mapa) {
        if(mapa == null) return false;

        agregarVolcanEnMapa(mapa);
        agregarMenaEnMapa(mapa);
        agregarMenaEnMapa(mapa);
        agregarMenaEnMapa(mapa);

        return true;
    }

    private void agregarMenaEnMapa(Mapa mapa) {
        Random rand = new Random();
        Posicion posiblePosicion;
        Mena posibleMena;
        boolean seAgregoAlMapa = false;

        while(!seAgregoAlMapa){
            // Se obtiene un numero entre [0 - 20].
            int x = rand.nextInt(100);
            int y = rand.nextInt(100);
            posiblePosicion = new Posicion(x, y);
            posibleMena = new Mena(posiblePosicion);
    
            if(this.jugadorDeReferencia.estaCercaDelJugador(posiblePosicion) && mapa.agregarMena(posibleMena))
                seAgregoAlMapa = true;
        }
    }

    private void agregarVolcanEnMapa(Mapa mapa) {
        Random rand = new Random();
        Posicion posiblePosicion;
        Volcan posibleVolcan;
        boolean seAgregoAlMapa = false;

        while(!seAgregoAlMapa){
            // Se obtiene un numero entre [0 - 20].
            int x = rand.nextInt(100);
            int y = rand.nextInt(100);
            posiblePosicion = new Posicion(x, y);
            posibleVolcan = new Volcan(posiblePosicion);
    
            if(this.jugadorDeReferencia.estaCercaDelJugador(posiblePosicion) && mapa.agregarVolcan(posibleVolcan))
                seAgregoAlMapa = true;
        }
    }



}
