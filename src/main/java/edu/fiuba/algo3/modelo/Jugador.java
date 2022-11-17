package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Exceptions.AtributoInvalidoException;

public class Jugador {

    private String nombre;
    private String color;
    private String raza; //TODO: cambiarlo a Raza?


    public Jugador(String name, String color, String race){
        nombre = name;
        this.color = color;
        raza = race;
    }

    /*public Jugador(String name, String color, String race, Jugador jugadorUno){
        nombre = name;
        this.color = color;
        raza = race;
    }*/

    public void validarAtributos(String name) throws AtributoInvalidoException {
        validarNombre(name, null);
    }

    public void validarAtributos(String name, String color, String race, Jugador jugadorUno) throws AtributoInvalidoException {
        validarNombre(name, jugadorUno.nombre);
        validarColor(color, jugadorUno.color);
        validarRaza(race, jugadorUno.raza);
    }

    private void validarRaza(String race, String razaUno) throws AtributoInvalidoException {
        if(razaUno.equals(race)) throw new AtributoInvalidoException(race);
    }

    private void validarColor(String color, String colorUno) throws AtributoInvalidoException {
        if(colorUno.equals(color)) throw new AtributoInvalidoException(color);
    }

    private void validarNombre(String name, String nombreUno) throws AtributoInvalidoException {
        if(name.length() < 6 || (nombreUno != null && nombreUno.equals(name))) throw new AtributoInvalidoException(name);
    }


}
