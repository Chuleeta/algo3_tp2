package edu.fiuba.algo3.javafx;

import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.ArrayList;

public class PasarTurno extends Button implements Notificador{

    private final Jugador jugadorUno;
    private final Jugador jugadorDos;
    private final Juego juego;
    private final Image icono;
    private ArrayList<Notificable> suscriptores;
    InputStream is = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
    Font fuente = Font.loadFont(getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf"), 20);
    static String botonNormal = "-fx-border-width: 2px; -fx-border-color: #B4DBE2; -fx-background-color: rgba(255, 255, 255, 0.2); -fx-text-fill: #42B0D3; -fx-shape: \"M 100 350 A 50 50 0 1 1 100 250 L 300 250 A 50 50 0 1 1 300 350 Z\";";
    static String botonHover = "-fx-border-width: 2px; -fx-border-color: #B4DBE2; -fx-background-color: rgba(243, 202, 76, 0.5); -fx-text-fill: #BDB69C; -fx-shape: \"M 100 350 A 50 50 0 1 1 100 250 L 300 250 A 50 50 0 1 1 300 350 Z\";";
    String formatoTexto = "-fx-border-width: 0px; -fx-border-color: #80CEB9; -fx-background-color: transparent; -fx-text-fill: #F3CA4C";
    private int turno;
    private String respuesta;
    private Image logo;

    public PasarTurno(String text, Juego juego) {
        super(text);
        this.turno = 1;
        this.jugadorUno = juego.getJugadorUno();
        this.jugadorDos = juego.getJugadorDos();
        this.juego = juego;
        this.suscriptores = new ArrayList<>();
        super.setOnAction(e -> {
            cambiarTurno();
        });
        super.setStyle(botonNormal);
        super.setOnMouseEntered(e -> this.setStyle(botonHover));
        super.setOnMouseExited(e -> this.setStyle(botonNormal));
        super.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf"), 20));
        String pathlogo = this.getClass().getResource("/imagenes/icono.png").toString();
        this.logo = new Image(pathlogo);
        String pathicono = this.getClass().getResource("/imagenes/icono.png").toString();
        this.icono = new Image(pathicono);
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

        try {
            if(!juego.pasarTiempo()){
                turnoActual = null;
                notificarFin();
            }
            for(Notificable suscriptor : this.suscriptores) {
                if (suscriptor != null) {
                    suscriptor.actualizar(turnoActual);
                }
            }
        } catch (NoExisteEdificioCorrelativoException e) {

        }
    }

    private void notificarFin() {
        InputStream is = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
        Font fuente = Font.loadFont(is, 20);

        Label ingresarPosicion = new Label("Fin de juego\n Vuelve al menu principal");
        ingresarPosicion.setFont(fuente);
        ingresarPosicion.setStyle(formatoTexto);
        ingresarPosicion.setAlignment(Pos.CENTER);

        Button botonConfirmar = new Button("Aceptar");
        botonConfirmar.setFont(fuente);
        botonConfirmar.setStyle(botonNormal);
        botonConfirmar.setOnMouseEntered(e -> botonConfirmar.setStyle(botonHover));
        botonConfirmar.setOnMouseExited(e -> botonConfirmar.setStyle(botonNormal));


        VBox inputPosicion = new VBox();
        inputPosicion.getChildren().addAll(ingresarPosicion, botonConfirmar);
        inputPosicion.setSpacing(30);
        inputPosicion.setAlignment(Pos.CENTER);
        inputPosicion.setBackground(new Background(new BackgroundFill(Color.rgb(47, 52, 58), new CornerRadii(0), Insets.EMPTY)));

        Scene sc = new Scene(inputPosicion, 500, 300);
        Stage s = new Stage();
        s.setResizable(false);
        s.setTitle("Fallo Al Construir Edificio");
        s.getIcons().add(this.icono);
        botonConfirmar.setOnAction(e-> {
            s.close();
        });
        s.setScene(sc);
        s.showAndWait();
    }

    public void cambiarTurno() {
        this.turno += 1;
        notificar();
    }
}
