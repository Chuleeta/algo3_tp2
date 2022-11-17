package edu.fiuba.algo3.modelo.Exceptions;

public class RecursosInsuficientesException extends Exception{
    public RecursosInsuficientesException(){
        System.out.println("No hay suficientes recursos para construir este edificio");
    }
}
