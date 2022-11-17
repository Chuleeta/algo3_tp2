package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Exceptions.AtributoInvalidoException;

public class Jugador {

    private String nombre;
    private String color;
    private String raza;
    private Posicion posicion;


    public Jugador(String name, String color, String race, Jugador jugadorContrario){
        nombre = name;
        this.color = color;
        raza = race;
        crearPosicion(jugadorContrario);
    }

    private void crearPosicion(Jugador jugadorContrario){
        if(jugadorContrario == null)
            this.posicion = new Posicion(1, 1);
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


}
