package edu.fiuba.algo3.javafx;

import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Jugador;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

import java.io.InputStream;
import java.util.ArrayList;

public class PasarTurno extends Button implements Notificador{

    private final Jugador jugadorUno;
    private final Jugador jugadorDos;
    private ArrayList<Notificable> suscriptores;
    InputStream is = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
    Font fuente = Font.loadFont(getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf"), 20);
    static String botonNormal = "-fx-border-width: 2px; -fx-border-color: #B4DBE2; -fx-background-color: rgba(255, 255, 255, 0.2); -fx-text-fill: #42B0D3; -fx-shape: \"M 100 350 A 50 50 0 1 1 100 250 L 300 250 A 50 50 0 1 1 300 350 Z\";";
    static String botonHover = "-fx-border-width: 2px; -fx-border-color: #B4DBE2; -fx-background-color: rgba(243, 202, 76, 0.5); -fx-text-fill: #BDB69C; -fx-shape: \"M 100 350 A 50 50 0 1 1 100 250 L 300 250 A 50 50 0 1 1 300 350 Z\";";
    private int turno;

    public PasarTurno(String text, Jugador jugadorUno, Jugador jugadorDos) {
        super(text);
        this.turno = 1;
        this.jugadorUno = jugadorUno;
        this.jugadorDos = jugadorDos;
        this.suscriptores = new ArrayList<>();
        super.setOnAction(e -> {
            cambiarTurno();
        });
        super.setStyle(botonNormal);
        super.setOnMouseEntered(e -> this.setStyle(botonHover));
        super.setOnMouseExited(e -> this.setStyle(botonNormal));
        super.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf"), 20));
    }

    public void añadirSuscriptor(Notificable nuevoSuscriptor) {
        if (nuevoSuscriptor == null) {
            return;
        }
        suscriptores.add(nuevoSuscriptor);
    }
    @Override
    public void notificar() {
        Jugador turnoActual = jugadorUno;
        if ((this.turno % 2) == 0){
            turnoActual = jugadorDos;
        }
        for(Notificable suscriptor : this.suscriptores) {
            if (suscriptor != null) {
                suscriptor.actualizar(turnoActual);
            }
        }
        try {
            jugadorUno.pasarTiempo();
            jugadorDos.pasarTiempo();
        } catch (NoExisteEdificioCorrelativoException e) {

        }
    }
    public void cambiarTurno() {
        this.turno += 1;
        notificar();
    }
}
