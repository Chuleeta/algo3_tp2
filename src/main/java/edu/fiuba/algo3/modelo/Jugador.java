package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Exceptions.AtributoInvalidoException;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Individuos.Individuo;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

import java.util.ArrayList;

public class Jugador {

    protected int capacidad;
    protected String nombre;
    protected String color;
    protected String raza;
    protected Posicion posicion;
    public Mineral mineral;
    protected int unidadesCreadas;
    public Mapa mapa;
    protected ArrayList<Construccion> construcciones;
    protected ArrayList<Individuo> individuos;

    public Jugador(String name, String color, String race, Posicion posicionJugador, Mapa mapa, int capacidad){
        this.nombre = name;
        this.color = color;
        this.raza = race;
        this.posicion = posicionJugador;
        this.mapa = mapa;
        this.construcciones = new ArrayList<>();
        this.individuos = new ArrayList<>();
        this.mineral = new Mineral(200);
        this.capacidad = capacidad;
        unidadesCreadas = 0;
    }

    public void validarAtributos(Jugador jugadorDado) throws AtributoInvalidoException {
        if(jugadorDado == null) return;
        validarNombre(this.nombre, jugadorDado.nombre);
        validarColor(this.color, jugadorDado.color);
        validarRaza(this.raza, jugadorDado.raza);
        jugadorDado.compararPosicionConOtroJugador(this.posicion);
    }

    public void compararPosicionConOtroJugador(Posicion posicionDada) throws AtributoInvalidoException{
        //CAMBIE ACA EL 10
        if(this.posicion.adentro(10, posicionDada)) throw new AtributoInvalidoException();
    }

    private void validarRaza(String race, String razaUno) throws AtributoInvalidoException {
        if(razaUno.equals(race)) throw new AtributoInvalidoException();
    }

    private void validarColor(String color, String colorUno) throws AtributoInvalidoException {
        if(colorUno.equals(color)) throw new AtributoInvalidoException();
    }

    private void validarNombre(String name, String nombreUno) throws AtributoInvalidoException {
        if(name.length() < 6 || (nombreUno != null && nombreUno.equals(name))) throw new AtributoInvalidoException();
    }

    public boolean estaCercaDelJugador(Posicion posicionDada){
        return (posicionDada.adentro( 10, this.posicion));
    }

    public void incrementarCapacidadDePoblacion(int nuevaCapacidad) {
        capacidad += nuevaCapacidad;
        if (capacidad > 200)
            capacidad = 200;
    }

    public void añadirUnidad() {
        unidadesCreadas += 1;
    }

    public boolean unidadesDisponibles() {
        return unidadesCreadas < capacidad;
    }

    public void decrementarCapacidadDePoblacion(int nuevaCapacidad) {
        capacidad -= nuevaCapacidad;
    }

    public void eliminarUnidad() {
        unidadesCreadas -= 1;
    }

    public void pasarTiempo() throws NoExisteEdificioCorrelativoException {
        for (Construccion construccion : construcciones) {
            construccion.pasarTiempo();
        }
        for (Individuo individuo:individuos)
        {
            individuo.pasarTiempo();
        }
        mapa.filtrarConstrucciones(construcciones);
    }

    public boolean agregarConstruccion(Construccion construccion, Mineral mineral, GasVespeno gas){
        if(!mapa.verificarPosicionDisponible(construccion)){
            return false;
        }
        return construccion.agregarAlMapa(mineral, gas);
    }

    public boolean agregarIndividuo(Individuo individuo){
        return individuos.add(individuo);
    }

    public void agregarEnListaConstruccion(Construccion construccion) {
        construcciones.add(construccion);
    }

    public void destruirConstruccion(Construccion construccion)
    {
        construcciones.remove(construccion);
    }
    public boolean verificarConstruccionesVacias() {
        return (!this.construcciones.isEmpty());
    }
}
