package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Edificios.*;
import edu.fiuba.algo3.modelo.Exceptions.AtributoInvalidoException;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Individuos.Individuo;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import edu.fiuba.algo3.javafx.Tablero;

import java.util.ArrayList;

public class Jugador {

    private int construccionesDestruidas;
    protected int capacidad;
    protected String nombre;
    protected String color;
    protected String raza;
    protected Posicion posicion;
    public Mineral mineral;
    private GasVespeno gas;
    protected int unidadesCreadas;
    public Mapa mapa;
    protected ArrayList<Construccion> construcciones;
    protected ArrayList<Individuo> individuos;

    public Jugador(String nombre, String color, String raza, Posicion posicionJugador, Mapa mapa, int capacidad){
        this.nombre = nombre;
        this.color = color;
        this.raza = raza;
        this.construccionesDestruidas = 0;
        this.posicion = posicionJugador;
        this.mapa = mapa;
        this.construcciones = new ArrayList<>();
        this.individuos = new ArrayList<>();
        this.mineral = new Mineral(2000);
        this.gas = new GasVespeno(2000);
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

    public boolean agregarConstruccion(Construccion construccion){
        if(!mapa.verificarPosicionDisponible(construccion)){
            return false;
        }
        
        return construccion.agregarAlMapa(this.mineral, this.gas);
    }

    public boolean agregarIndividuo(Individuo individuo){
        return individuos.add(individuo);
    }

    public boolean eliminarIndividuo(Individuo individuo){
        return individuos.remove(individuo);
    }

    public void agregarEnListaConstruccion(Construccion construccion) {
        construcciones.add(construccion);
    }

    public void destruirConstruccion(Construccion construccion) {
        construcciones.remove(construccion);
        construccionesDestruidas += 1;
    }
    public boolean verificarConstruccionesVacias() {
        return (!this.construcciones.isEmpty());
    }

    public ArrayList<Construccion> getConstrucciones() {
        return this.construcciones;
    }

    public void incrementarMineral(int cantidad){
        this.mineral.agregarMineral(cantidad);
    }

    public void incrementarGas(int cantidad){
        this.gas.agregarGas(cantidad);
    }

    public Mapa getMapa() {
        return mapa;
    }

    public ArrayList<Individuo> mostrarIndividuos() {
        return this.individuos;
    }

    public Mapa mostrarMapa() {
        return this.mapa;
    }

    public boolean validarCorrelativaPuertoEstelar(){
        for (Construccion construccion : this.construcciones) {
            if (construccion.getClass() == PuertoEstelar.class) {
                return true;
            }
        }
        return false;
    }

    public boolean validarCorrelativaAcceso(){
        for (Construccion construccion : this.construcciones) {
            if (construccion.getClass() == Acceso.class) {
                return true;
            }
        }
        return false;
    }

    public boolean validarCorrelativaCriadero() {
        for (Construccion construccion : this.construcciones) {
            if (construccion.getClass() == Criadero.class) {
                return true;
            }
        }
        return false;
    }

    public boolean validarCorrelativaReserva() {
        for (Construccion construccion : this.construcciones) {
            if (construccion.getClass() == ReservaDeReproduccion.class) {
                return true;
            }
        }
        return false;
    }

    public boolean validarCorrelativaEspiral() {
        for (Construccion construccion : this.construcciones) {
            if (construccion.getClass() == Espiral.class) {
                return true;
            }
        }
        return false;
    }

    public boolean validarCorrelativaGuarida() {
        for (Construccion construccion : this.construcciones) {
            if (construccion.getClass() == Guarida.class) {
                return true;
            }
        }
        return false;
    }
}
