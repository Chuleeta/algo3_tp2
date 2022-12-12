package edu.fiuba.algo3.javafx.Eventos;

import edu.fiuba.algo3.javafx.JuegoVista;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Individuos.AmoSupremo;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.JugadorProtoss;
import edu.fiuba.algo3.modelo.JugadorZerg;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.Optional;

public class BotonAgregarIndividuoZergHandler extends BorderPane implements EventHandler<ActionEvent>{

    JuegoVista juegoVista;
    static Scene preguntaDatos;
    private Image logoFondo;
    private Image icono;
    private final Jugador jugador;
    String botonAntesDeSerPresionado = "-fx-border-width: 2px; -fx-border-color: #B4DBE2; -fx-background-color: rgba(243, 202, 76, 0.5); -fx-text-fill: #BDB69C; -fx-shape: \"M 100 350 A 50 50 0 1 1 100 250 L 300 250 A 50 50 0 1 1 300 350 Z\";";
    String botonNormal = "-fx-border-width: 2px; -fx-border-color: #B4DBE2; -fx-background-color: rgba(255, 255, 255, 0.2); -fx-text-fill: #42B0D3; -fx-shape: \"M 100 350 A 50 50 0 1 1 100 250 L 300 250 A 50 50 0 1 1 300 350 Z\";";

    public BotonAgregarIndividuoZergHandler(JuegoVista juegoVista, Jugador jugador) {
        //this.stage = stage;
        //this.escenaParaDobleRepeticion = escenaParaDobleRepeticion;

        this.juegoVista = juegoVista;
        this.jugador = jugador;
    }

    /*private void cargarImagenes() {
        String pathLogoFondo = this.getClass().getResource("/imagenes/fondoDePantalla.jpg").toString();
        this.logoFondo = new Image(pathLogoFondo);

        String pathicono = this.getClass().getResource("/imagenes/icono.png").toString();
        this.icono = new Image(pathicono);
    }*/

    private void cargarPosicion(){
        TilePane posicion = new TilePane();
        Button b = new Button("Insertar posicion");
        Label l = new Label("Ingrese la posicion:");
        BotonIngresarPosicionHandler ingresarPosicion = new BotonIngresarPosicionHandler(l);
        b.setOnAction(ingresarPosicion);
        posicion.getChildren().add(b);
        posicion.getChildren().add(l);

        Scene sc = new Scene(posicion, 500, 300);

        Stage s = new Stage();
        s.setScene(sc);
        s.show();
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        /*this.cargarImagenes();

        InputStream is = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
        Font fuente = Font.loadFont(is, 30);
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        preguntaDatos = new Scene(this,screenSize.getWidth(), screenSize.getHeight(), Color.rgb(47, 52, 58));
        BackgroundImage imagenDeFondo = new BackgroundImage(this.logoFondo, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false));
        this.setBackground(new Background(imagenDeFondo));
        // vista.agregarElementosAEjecutar(etiqueta);
        // tablero.agregarBloqueDobleRepeticion();

        Label etiqueta = new Label();
        etiqueta.setText("Agregar Unidad Zerg");

        VBox preguntasJugador1 = new VBox();
        preguntasJugador1.setAlignment(Pos.CENTER);
        preguntasJugador1.setSpacing(30);
        preguntasJugador1.setStyle("-fx-padding: 100;");

        TextField posicionX = new TextField();
        posicionX.setAlignment(Pos.CENTER);
        posicionX.setPromptText("Inserte un casillero horizontal");
        posicionX.setFont(fuente);
        posicionX.setStyle(botonNormal);
        posicionX.setOnMouseEntered(e -> posicionX.setStyle(botonAntesDeSerPresionado));
        posicionX.setOnMouseExited(e -> posicionX.setStyle(botonNormal));

        TextField posicionY = new TextField();
        posicionY.setAlignment(Pos.CENTER);
        posicionY.setPromptText("Inserte un casillero horizontal");
        posicionY.setFont(fuente);
        posicionY.setStyle(botonNormal);
        posicionY.setOnMouseEntered(e -> posicionY.setStyle(botonAntesDeSerPresionado));
        posicionY.setOnMouseExited(e -> posicionY.setStyle(botonNormal));

        ChoiceBox<String> seleccionRaza1 = new ChoiceBox();
        seleccionRaza1.getItems().addAll("Zerg", "Protoss");
        seleccionRaza1.setMinWidth(200);
        seleccionRaza1.setMinHeight(100);
        seleccionRaza1.setStyle("-fx-border-width: 0px; -fx-border-color: #2F343A; -fx-background-color: #717D8C; -fx-text-fill: #BDB69C; -fx-font-family: Impact; -fx-font-size: 40; -fx-color: #BDB69C");
        seleccionRaza1.setOnMouseEntered(e -> seleccionRaza1.setStyle("-fx-border-width: 0px; -fx-border-color: #2F343A; -fx-background-color: #717D8C; -fx-text-fill: #BDB69C; -fx-font-family: Impact; -fx-font-size: 40; -fx-color: #BDB69C"));
        seleccionRaza1.setOnMouseExited(e -> seleccionRaza1.setStyle("-fx-border-width: 0px; -fx-border-color: #2F343A; -fx-background-color: #717D8C; -fx-text-fill: #BDB69C; -fx-font-family: Impact; -fx-font-size: 40; -fx-color: #BDB69C"));

        preguntasJugador1.getChildren().add(etiqueta);
        preguntasJugador1.getChildren().add(posicionX);
        preguntasJugador1.getChildren().add(posicionY);
        preguntasJugador1.getChildren().add(seleccionRaza1);

        HBox preguntas = new HBox();
        preguntas.setAlignment(Pos.CENTER);
        preguntas.setSpacing(0);
        preguntas.getChildren().add(preguntasJugador1);

        VBox overlayPreguntas = new VBox();
        overlayPreguntas.setAlignment(Pos.CENTER);
        overlayPreguntas.getChildren().add(preguntas);

        this.setCenter(overlayPreguntas);*/

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Unidades zerg");
        alert.setHeaderText("Seleccione la unidad que desea enjendrar");

        ButtonType botonAmo = new ButtonType("Amo Supremo");
        ButtonType botonZangano = new ButtonType("Zangano");
        ButtonType botonZerling = new ButtonType("Zerling");
        ButtonType botonHidralisco = new ButtonType("Hidralisco");
        ButtonType botonMutalisco = new ButtonType("Mutalisco");
        //ButtonType botonGuardian = new ButtonType("Guardian");
        //ButtonType botonDevorador = new ButtonType("Devorador");


        alert.getButtonTypes().setAll(botonAmo, botonZangano, botonZerling, botonHidralisco/*, botonMutaliscobotonGuardian, botonDevorador*/);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == botonAmo){
            AmoSupremo amo = null;
            try {
                amo = new AmoSupremo(jugador.mineral, new GasVespeno(0), new Posicion(2, 2), juegoVista.juego.mapa);
            } catch (RequerimientosInsuficientesException e) {
                e.printStackTrace();
            }
            jugador.agregarIndividuo(amo);
        }else if (result.get() == botonZangano) {
            this.cargarPosicion();
            //Zangano zangano = new Zangano();
        } else if (result.get() == botonZerling) {
            // Zerling zerling = new Zerling();
        } else if (result.get() == botonHidralisco) {
            // Hidralisco hidralisco = new Hidralisco();
        }  else if (result.get() == botonMutalisco) {
            // Mutalisco mutalisco = new Mutalisco();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

}