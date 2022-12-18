package edu.fiuba.algo3.javafx;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

import java.io.InputStream;

public class BotonModal extends Button {

    String botonAntesDeSerPresionado = "-fx-border-width: 2px; -fx-border-color: #B4DBE2; -fx-background-color: rgba(243, 202, 76, 0.5); -fx-text-fill: #BDB69C; -fx-shape: \"M 100 350 A 50 50 0 1 1 100 250 L 300 250 A 50 50 0 1 1 300 350 Z\";";
    String botonNormal = "-fx-border-width: 2px; -fx-border-color: #B4DBE2; -fx-background-color: rgba(255, 255, 255, 0.2); -fx-text-fill: #42B0D3; -fx-shape: \"M 100 350 A 50 50 0 1 1 100 250 L 300 250 A 50 50 0 1 1 300 350 Z\";";
    String formatoTexto = "-fx-border-width: 0px; -fx-border-color: #80CEB9; -fx-background-color: transparent; -fx-text-fill: #F3CA4C";
    InputStream is = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
    Font fuente2 = Font.loadFont(is, 25);
    public BotonModal (String text) {
        super(text);
        super.setFont(fuente2);
        super.setStyle(botonNormal);
        super.setOnMouseEntered(event -> super.setStyle(botonAntesDeSerPresionado));
        super.setOnMouseExited(event -> super.setStyle(botonNormal));
    }

}
