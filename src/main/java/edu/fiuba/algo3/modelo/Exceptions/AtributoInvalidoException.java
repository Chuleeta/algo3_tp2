package edu.fiuba.algo3.modelo.Exceptions;

public class AtributoInvalidoException extends Exception{
    public AtributoInvalidoException(String atributo){
        System.out.printf("El atributo %s es invalido%n", atributo);
    }
}
