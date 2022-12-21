package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Edificios.*;
import edu.fiuba.algo3.modelo.Exceptions.AtributoInvalidoException;
import edu.fiuba.algo3.modelo.Exceptions.CriaderoNoDisponibleException;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Individuos.Devorador;
import edu.fiuba.algo3.modelo.Exceptions.NoSeEncuentraAlIndividuoException;
import edu.fiuba.algo3.modelo.Individuos.Dragon;
import edu.fiuba.algo3.modelo.Individuos.Guardian;
import edu.fiuba.algo3.modelo.Individuos.Hidralisco;
import edu.fiuba.algo3.modelo.Individuos.Individuo;
import edu.fiuba.algo3.modelo.Individuos.Mutalisco;
import edu.fiuba.algo3.modelo.Individuos.Scout;
import edu.fiuba.algo3.modelo.Individuos.Zangano;
import edu.fiuba.algo3.modelo.Individuos.Zealot;
import edu.fiuba.algo3.modelo.Individuos.Zerling;
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
        return individuo.agregarAlMapa(this.mineral, this.gas);
    }

    public boolean eliminarIndividuo(Individuo individuo){
        return individuos.remove(individuo);
    }


    public void agregarEnListaConstruccion(Construccion construccion) {
        construcciones.add(construccion);
    }

    public boolean agregarEnListaIndividuo(Individuo individuo){
        return individuos.add(individuo);
    }

    public void destruirConstruccion(Construccion construccion) {
        construcciones.remove(construccion);
        construccionesDestruidas += 1;
    }
    public boolean verificarConstruccionesVacias() {

        return ((this.construcciones.size() == 0) && (construccionesDestruidas > 0)) ;

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


    public Mineral invertirMineral() {
        return this.mineral;
    }
    public GasVespeno invertirGas() {
        return this.gas;
    }

    public void añadirMineral(int cantidad) {
        this.mineral.agregarMineral(cantidad);
    }

    public void atacarUnidad(Posicion posicion, Individuo individuoAtacante) throws NoSeEncuentraAlIndividuoException {
        Individuo individuoAtacado = encontrarIndividuo(posicion);
        if(individuoAtacado != null) {
            individuoAtacante.atacar(individuoAtacado);
            return;
        }

        Construccion construccion = encontrarConstruccion(posicion);
        System.out.println(construccion);
        if(construccion != null) {
            individuoAtacante.atacar(construccion);
            return;
        }
        throw new NoSeEncuentraAlIndividuoException();
    }

    private Construccion encontrarConstruccion(Posicion posicion) {
        for (Construccion construccion : this.construcciones) {
            System.out.println("x: " + construccion.posicion().coordenadaX() + "y: " + construccion.posicion().coordenadaY());
            if (construccion.posicion().posicionesIguales(posicion)) {
                return construccion;
            }
        }
        return null;
    }

    private Individuo encontrarIndividuo(Posicion posicion) {
        return individuos.stream().filter(individuo -> individuo.posicion().posicionesIguales(posicion)).findFirst().orElse(null);
    }

    public void verificarEdificacionCorrelativa(Guarida guarida) throws NoExisteEdificioCorrelativoException {

        for (Construccion construccion : this.construcciones) {
            if (construccion.verificarCorrelativa(guarida)) {
                return;
            }
        }
        throw new NoExisteEdificioCorrelativoException();
    }

    public void verificarEdificacionCorrelativa(Espiral espiral) throws NoExisteEdificioCorrelativoException {
        for (Construccion construccion : this.construcciones) {
            if (construccion.verificarCorrelativa(espiral)) {
                return;
            }
        }
        throw new NoExisteEdificioCorrelativoException();
    }

    public void verificarEdificacionCorrelativa(PuertoEstelar puertoEstelar) throws NoExisteEdificioCorrelativoException {
        for (Construccion construccion : this.construcciones) {
            if (construccion.verificarCorrelativa(puertoEstelar)) {
                return;
            }
        }
        throw new NoExisteEdificioCorrelativoException();
    }

    public void verificarEdificacionCorrelativa(Zangano zangano) throws NoExisteEdificioCorrelativoException {
        for (Construccion construccion : this.construcciones) {
            if (construccion.verificarCorrelativa(zangano)) {
                return;
            }
        }
        throw new NoExisteEdificioCorrelativoException();
    }

    public void verificarEdificacionCorrelativa(Zerling zerling) throws NoExisteEdificioCorrelativoException {
        for (Construccion construccion : this.construcciones) {
            if (construccion.verificarCorrelativa(zerling)) {
                return;
            }
        }
        throw new NoExisteEdificioCorrelativoException();
    }

    public void verificarEdificacionCorrelativa(Hidralisco hidralisco) throws NoExisteEdificioCorrelativoException {
        for (Construccion construccion : this.construcciones) {
            if (construccion.verificarCorrelativa(hidralisco)) {
                return;
            }
        }
        throw new NoExisteEdificioCorrelativoException();
    }

    public void verificarEdificacionCorrelativa(Mutalisco mutalisco) throws NoExisteEdificioCorrelativoException {
        for (Construccion construccion : this.construcciones) {
            if (construccion.verificarCorrelativa(mutalisco)) {
                return;
            }
        }
        throw new NoExisteEdificioCorrelativoException();
    }

    public void verificarEdificacionCorrelativa(Zealot zealot) throws NoExisteEdificioCorrelativoException {
        for (Construccion construccion : this.construcciones) {
            if (construccion.verificarCorrelativa(zealot)) {
                return;
            }
        }
        throw new NoExisteEdificioCorrelativoException();
    }

    public void verificarEdificacionCorrelativa(Dragon dragon) throws NoExisteEdificioCorrelativoException {
        for (Construccion construccion : this.construcciones) {
            if (construccion.verificarCorrelativa(dragon)) {
                return;
            }
        }
        throw new NoExisteEdificioCorrelativoException();
    }

    public void verificarEdificacionCorrelativa(Scout scout) throws NoExisteEdificioCorrelativoException {
        for (Construccion construccion : this.construcciones) {
            if (construccion.verificarCorrelativa(scout)) {
                return;
            }
        }
        throw new NoExisteEdificioCorrelativoException();
    }

    public boolean validarCorrelativaEvolucion(Devorador devorador) {
        for (Individuo individuo : this.individuos) {
            if (individuo.validarCorrelativaEvolucion(devorador)) {
                eliminarIndividuo(individuo);
                return true;
            }
        }
        return false;
    }

    public boolean validarCorrelativaEvolucion(Guardian guardian) {
        for (Individuo individuo : this.individuos) {
            if (individuo.validarCorrelativaEvolucion(guardian)) {
                eliminarIndividuo(individuo);
                return true;
            }
        }
        return false;
    }
}
