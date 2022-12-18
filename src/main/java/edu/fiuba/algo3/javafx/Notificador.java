package edu.fiuba.algo3.javafx;

public interface Notificador {
    public void notificar();

    public void añadirSuscriptor(Notificable nuevoSuscriptor);

}
