package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Individuos.Individuo;
import edu.fiuba.algo3.modelo.Recursos.RecursoInyectable;

import java.util.ArrayList;

public class Juego {
    public Mapa mapa;
    private Jugador jugadorUno;
    private Jugador jugadorDos;

    public Juego(Mapa mapa, Jugador jugadorUno, Jugador jugadorDos) throws RequerimientosInsuficientesException{
        if (mapa == null || jugadorUno == null || jugadorDos == null) throw new RequerimientosInsuficientesException();
        this.mapa = mapa;
        this.jugadorUno = jugadorUno;
        this.jugadorDos = jugadorDos;
    }

    public boolean pasarTiempo() throws NoExisteEdificioCorrelativoException {
        this.jugadorUno.pasarTiempo();
        this.jugadorDos.pasarTiempo();
        
        return (!this.jugadorDos.verificarConstruccionesVacias() && !this.jugadorUno.verificarConstruccionesVacias());
    }

    public ArrayList<Construccion> mostrarConstrucciones() {
        ArrayList<Construccion> construcciones = new ArrayList<Construccion>();
        for (Construccion construccion : jugadorUno.getConstrucciones())
            construcciones.add(construccion);
        for (Construccion construccion : jugadorDos.getConstrucciones())
            construcciones.add(construccion);
        return construcciones;
    }

    
    public ArrayList<AreaEspacial> mostrarAreaEspacial() {
        ArrayList<AreaEspacial> areasEspaciales = new ArrayList<AreaEspacial>();
        for (AreaEspacial areasEspacial : this.mapa.getAreaEspacial())
            areasEspaciales.add(areasEspacial);
        return areasEspaciales;
    }

    public ArrayList<RecursoInyectable> mostrarRecursos() {
        return mapa.mostrarRecursos();
    }

    public Jugador getJugadorUno(){
        return jugadorUno;
    }

    public Jugador getJugadorDos(){
        return jugadorDos;
    }

    public ArrayList<Individuo> mostrarUnidades() {
        ArrayList<Individuo> unidades = new ArrayList<>();
        for (Individuo individuo : jugadorUno.mostrarIndividuos())
            unidades.add(individuo);
        for (Individuo individuo : jugadorDos.mostrarIndividuos())
            unidades.add(individuo);
        return unidades;
    }
}
