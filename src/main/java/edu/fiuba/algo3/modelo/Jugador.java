package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Exceptions.AtributoInvalidoException;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

public class Jugador {

    private String nombre;
    private String color;
    private String raza;
    private Posicion posicion;
    public Mineral mineral;

    public Jugador(String name, String color, String race, Jugador jugadorContrario){
        this.nombre = name;
        this.color = color;
        this.raza = race;
        crearPosicion(jugadorContrario);
        this.mineral = new Mineral(200);
    }

    private void crearPosicion(Jugador jugadorContrario){
        if(jugadorContrario == null){
            this.posicion = new Posicion(1, 1);
            return;
        }
        this.posicion = new Posicion(100, 100);
    }

    public void validarAtributos(Jugador jugadorDado) throws AtributoInvalidoException {
        if(jugadorDado == null) return;
        validarNombre(this.nombre, jugadorDado.nombre);
        validarColor(this.color, jugadorDado.color);
        validarRaza(this.raza, jugadorDado.raza);
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


}
