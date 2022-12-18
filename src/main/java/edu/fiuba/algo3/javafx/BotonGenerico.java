package edu.fiuba.algo3.javafx;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

import java.io.InputStream;

public class BotonGenerico extends Button implements Notificable {

    private int jugador;

    InputStream is = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
    Font fuente = Font.loadFont(getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf"), 20);
    static String botonNormal = "-fx-border-width: 2px; -fx-border-color: #B4DBE2; -fx-background-color: rgba(255, 255, 255, 0.2); -fx-text-fill: #42B0D3; -fx-shape: \"M 100 350 A 50 50 0 1 1 100 250 L 300 250 A 50 50 0 1 1 300 350 Z\";";
    static String botonHover = "-fx-border-width: 2px; -fx-border-color: #B4DBE2; -fx-background-color: rgba(243, 202, 76, 0.5); -fx-text-fill: #BDB69C; -fx-shape: \"M 100 350 A 50 50 0 1 1 100 250 L 300 250 A 50 50 0 1 1 300 350 Z\";";
    public BotonGenerico(String text, EventHandler action) {
        super(text);
        super.setOnAction(action);
        super.setStyle(botonNormal);
        super.setOnMouseEntered(e -> this.setStyle(botonHover));
        super.setOnMouseExited(e -> this.setStyle(botonNormal));
        super.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf"), 20));
    }
    public BotonGenerico(String text, int turnoInicial, EventHandler action, int x, int y) {
        super(text);
        super.setDisable(turnoInicial != this.jugador);
        super.setOnAction(action);
        super.setStyle(botonNormal);
        super.setOnMouseEntered(e -> this.setStyle(botonHover));
        super.setOnMouseExited(e -> this.setStyle(botonNormal));
        super.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf"), 20));
        super.setTranslateX(x);
        super.setTranslateY(y);
    }
    public void setearJugador(int jugador){
        this.jugador = jugador;
        this.setDisable(this.jugador != 1);
    }
    @Override
    public void actualizar(int turno) {
            this.setDisable(turno != this.jugador);
    }
}
