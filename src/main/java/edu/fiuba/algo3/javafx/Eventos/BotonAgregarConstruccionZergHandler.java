package edu.fiuba.algo3.javafx.Eventos;

import edu.fiuba.algo3.javafx.JuegoVista;
import edu.fiuba.algo3.modelo.Edificios.Criadero;
import edu.fiuba.algo3.modelo.Edificios.Espiral;
import edu.fiuba.algo3.modelo.Edificios.Extractor;
import edu.fiuba.algo3.modelo.Edificios.Guarida;
import edu.fiuba.algo3.modelo.Edificios.ReservaDeReproduccion;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.Exceptions.VolcanOcupadoException;
import edu.fiuba.algo3.modelo.Recursos.Volcan;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Posicion;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.Optional;
import java.util.function.UnaryOperator;

public class BotonAgregarConstruccionZergHandler  implements EventHandler<ActionEvent> {

    JuegoVista juegoVista;
    private final Jugador jugador;
    private Image icono;
    String botonAntesDeSerPresionado = "-fx-border-width: 2px; -fx-border-color: #B4DBE2; -fx-background-color: rgba(243, 202, 76, 0.5); -fx-text-fill: #BDB69C; -fx-shape: \"M 100 350 A 50 50 0 1 1 100 250 L 300 250 A 50 50 0 1 1 300 350 Z\";";
    String botonNormal = "-fx-border-width: 2px; -fx-border-color: #B4DBE2; -fx-background-color: rgba(255, 255, 255, 0.2); -fx-text-fill: #42B0D3; -fx-shape: \"M 100 350 A 50 50 0 1 1 100 250 L 300 250 A 50 50 0 1 1 300 350 Z\";";
    String formatoTexto = "-fx-border-width: 0px; -fx-border-color: #80CEB9; -fx-background-color: transparent; -fx-text-fill: #F3CA4C";


    public BotonAgregarConstruccionZergHandler(JuegoVista juegoVista, Jugador jugador) {
        //this.stage = stage;
        //this.escenaParaDobleRepeticion = escenaParaDobleRepeticion;
        cargarImagenes();
        this.juegoVista = juegoVista;
        this.jugador = jugador;
    }

    private void cargarImagenes() {

        String pathicono = this.getClass().getResource("/imagenes/icono.png").toString();
        this.icono = new Image(pathicono);
    }

    private Posicion cargarPosicion(){
        InputStream is = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
        Font fuente = Font.loadFont(is, 25);

        Label ingresarPosicion = new Label("Ingresar Posicion Deseada \n");
        ingresarPosicion.setFont(fuente);
        ingresarPosicion.setStyle(formatoTexto);

        ChoiceBox<String> posicionDeseadaX = new ChoiceBox();
        posicionDeseadaX.getItems().addAll("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22");
        posicionDeseadaX.setMinWidth(200);
        posicionDeseadaX.setMinHeight(100);
        posicionDeseadaX.setStyle("-fx-border-width: 0px; -fx-border-color: #2F343A; -fx-background-color: #717D8C; -fx-text-fill: #BDB69C; -fx-font-family: Impact; -fx-font-size: 40; -fx-color: #BDB69C");
        posicionDeseadaX.setOnMouseEntered(e -> posicionDeseadaX.setStyle("-fx-border-width: 0px; -fx-border-color: #2F343A; -fx-background-color: #717D8C; -fx-text-fill: #BDB69C; -fx-font-family: Impact; -fx-font-size: 40; -fx-color: #BDB69C"));
        posicionDeseadaX.setOnMouseExited(e -> posicionDeseadaX.setStyle("-fx-border-width: 0px; -fx-border-color: #2F343A; -fx-background-color: #717D8C; -fx-text-fill: #BDB69C; -fx-font-family: Impact; -fx-font-size: 40; -fx-color: #BDB69C"));

        ChoiceBox<String> posicionDeseadaY = new ChoiceBox();
        posicionDeseadaY.getItems().addAll("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18");
        posicionDeseadaY.setMinWidth(200);
        posicionDeseadaY.setMinHeight(100);
        posicionDeseadaY.setStyle("-fx-border-width: 0px; -fx-border-color: #2F343A; -fx-background-color: #717D8C; -fx-text-fill: #BDB69C; -fx-font-family: Impact; -fx-font-size: 40; -fx-color: #BDB69C");
        posicionDeseadaY.setOnMouseEntered(e -> posicionDeseadaY.setStyle("-fx-border-width: 0px; -fx-border-color: #2F343A; -fx-background-color: #717D8C; -fx-text-fill: #BDB69C; -fx-font-family: Impact; -fx-font-size: 40; -fx-color: #BDB69C"));
        posicionDeseadaY.setOnMouseExited(e -> posicionDeseadaY.setStyle("-fx-border-width: 0px; -fx-border-color: #2F343A; -fx-background-color: #717D8C; -fx-text-fill: #BDB69C; -fx-font-family: Impact; -fx-font-size: 40; -fx-color: #BDB69C"));

        Label X = new Label("EN X: \n");
        X.setFont(fuente);
        X.setStyle(formatoTexto);

        Label Y = new Label("EN Y: \n");
        Y.setFont(fuente);
        Y.setStyle(formatoTexto);

        Button botonConfirmar = new Button("Confirmar");
        botonConfirmar.setFont(fuente);
        botonConfirmar.setStyle(botonNormal);
        botonConfirmar.setOnMouseEntered(e -> botonConfirmar.setStyle(botonAntesDeSerPresionado));
        botonConfirmar.setOnMouseExited(e -> botonConfirmar.setStyle(botonNormal));
        
        VBox enX = new VBox();
        VBox enY = new VBox();
        enX.getChildren().addAll(X, posicionDeseadaX);
        enX.setSpacing(20);
        enX.setAlignment(Pos.CENTER);

        enY.getChildren().addAll(Y, posicionDeseadaY);
        enY.setSpacing(20);
        enY.setAlignment(Pos.CENTER);
        
        VBox inputPosicion = new VBox();
        HBox posicionDeseada = new HBox();
        posicionDeseada.getChildren().addAll(enX, enY);
        posicionDeseada.setSpacing(20);
        posicionDeseada.setAlignment(Pos.CENTER);
        inputPosicion.getChildren().addAll(ingresarPosicion, posicionDeseada, botonConfirmar);
        inputPosicion.setSpacing(30);
        inputPosicion.setAlignment(Pos.CENTER);
        inputPosicion.setBackground(new Background(new BackgroundFill(Color.rgb(47, 52, 58), new CornerRadii(0), Insets.EMPTY)));
        
        Scene sc = new Scene(inputPosicion, 500, 300);
        Stage s = new Stage();
        s.setResizable(false);
        s.setTitle("Insertar Una Posicion");
        s.getIcons().add(this.icono);
        botonConfirmar.setOnAction(e-> {
            s.close();
        });
        s.setScene(sc);
        s.showAndWait();
        int valorX = 0;
        int valorY = 0;
        if(posicionDeseadaX.getValue() != null){
            valorX = Integer.valueOf(posicionDeseadaX.getValue());
        }else{
            return null;
        }

        if(posicionDeseadaY.getValue() != null){
            valorY = Integer.valueOf(posicionDeseadaY.getValue());
        }else{
            return null;
        }
        
        return (new Posicion(valorX - 1, valorY - 1));
    }
    
    
    @Override
    public void handle(ActionEvent actionEvent) {
        Stage s = new Stage();
        
        InputStream is = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
        Font fuente = Font.loadFont(is, 25);

        InputStream is2 = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
        Font fuente2 = Font.loadFont(is2, 25);

        Label seleccionarIndividuo = new Label("Seleccionar Construccion\n");
        seleccionarIndividuo.setFont(fuente);
        seleccionarIndividuo.setStyle(formatoTexto);
        
        HBox opciones = new HBox();
        
        Button botonCriadero = new Button("Criadero");
        botonCriadero.setFont(fuente2);
        botonCriadero.setStyle(botonNormal);
        botonCriadero.setOnMouseEntered(e -> botonCriadero.setStyle(botonAntesDeSerPresionado));
        botonCriadero.setOnMouseExited(e -> botonCriadero.setStyle(botonNormal));
        botonCriadero.setOnAction(e-> {
            Posicion inputUsuario = this.cargarPosicion();
            try {
                Criadero criadero = new Criadero(inputUsuario, this.jugador);
                juegoVista.actualizarTablero();
            } catch (RecursosInsuficientesException e1) {
                noSePuedeConstruir();
            }
            // if(this.jugador.agregarConstruccion(criadero)){
            //     juegoVista.actualizarTablero();
            // }else{
            // }
            s.close();
        });
        
        Button botonReserva = new Button("Reserva de\nReproduccion");
        botonReserva.setFont(fuente2);
        botonReserva.setStyle(botonNormal);
        botonReserva.setOnMouseEntered(e -> botonReserva.setStyle(botonAntesDeSerPresionado));
        botonReserva.setOnMouseExited(e -> botonReserva.setStyle(botonNormal));
        botonReserva.setOnAction(e-> {
            Posicion inputUsuario = this.cargarPosicion();
            try {
                ReservaDeReproduccion reserva = new ReservaDeReproduccion(inputUsuario, this.jugador);
                juegoVista.actualizarTablero();
            } catch (RecursosInsuficientesException e1) {
                noSePuedeConstruir();
            }
            // if(this.jugador.agregarConstruccion(reserva)){
            // }else{
            // }
            s.close();
        });
        
        Button botonExtractor = new Button("Extractor");
        botonExtractor.setFont(fuente2);
        botonExtractor.setStyle(botonNormal);
        botonExtractor.setOnMouseEntered(e -> botonExtractor.setStyle(botonAntesDeSerPresionado));
        botonExtractor.setOnMouseExited(e -> botonExtractor.setStyle(botonNormal));
        botonExtractor.setOnAction(e-> {
            Posicion inputUsuario = this.cargarPosicion();
            //ACA TIENE Q SELECCIONAR UN VOLCAN
            //Volcan volcan = new Volcan(inputUsuario);
            //this.jugador.mapa.agregarRecursoInyectable(volcan, inputUsuario);
            try {
                Extractor extractor = new Extractor(inputUsuario, jugador);
                juegoVista.actualizarTablero();
                // if(this.jugador.agregarConstruccion(extractor)){
                //     juegoVista.actualizarTablero();
                // }else{
                //     noSePuedeConstruir();
                // }
            } catch (VolcanOcupadoException e1) {
                noSePuedeConstruir();
            } catch (RecursosInsuficientesException e1) {
                noSePuedeConstruir();
            }
            //Extractor extractor = new Extractor(inputUsuario, this.jugador.mapa, this.jugador);
            // if(this.jugador.agregarConstruccion(extractor)){
            //     juegoVista.actualizarTablero();
            // }else{
            //     noSePuedeConstruir();
            // }
            s.close();
        });
        
        Button botonGuarida = new Button("Guarida");
        botonGuarida.setFont(fuente2);
        botonGuarida.setStyle(botonNormal);
        botonGuarida.setOnMouseEntered(e -> botonGuarida.setStyle(botonAntesDeSerPresionado));
        botonGuarida.setOnMouseExited(e -> botonGuarida.setStyle(botonNormal));
        botonGuarida.setOnAction(e-> {
            Posicion inputUsuario = this.cargarPosicion();
            Guarida guarida;
            try {
                guarida = new Guarida(inputUsuario, this.jugador);
                juegoVista.actualizarTablero();
            } catch (NoExisteEdificioCorrelativoException e1) {
                noSePuedeConstruir();
            } catch (RecursosInsuficientesException e1) {
                noSePuedeConstruir();
            }
            s.close();
        });
        
        Button botonEspiral = new Button("Espiral");
        botonEspiral.setFont(fuente2);
        botonEspiral.setStyle(botonNormal);
        botonEspiral.setOnMouseEntered(e -> botonEspiral.setStyle(botonAntesDeSerPresionado));
        botonEspiral.setOnMouseExited(e -> botonEspiral.setStyle(botonNormal));
        botonEspiral.setOnAction(e-> {
            Posicion inputUsuario = this.cargarPosicion();
            try {
                Espiral espiral = new Espiral(inputUsuario, this.jugador);
                juegoVista.actualizarTablero();
            } catch (RecursosInsuficientesException e1) {
                noSePuedeConstruir();
            } catch (NoExisteEdificioCorrelativoException e1) {
                noSePuedeConstruir();
            }
            // if(this.jugador.agregarConstruccion(espiral)){
            // }else{
            //     noSePuedeConstruir();
            // }
            s.close();
        });
        
        VBox eleccionUsuario = new VBox();
        VBox opciones1 = new VBox();
        VBox opciones2 = new VBox();
        VBox opciones3 = new VBox();
        opciones1.getChildren().addAll(botonCriadero, botonReserva);
        opciones1.setSpacing(10);
        opciones1.setAlignment(Pos.CENTER);
        opciones2.getChildren().addAll(botonExtractor ,botonGuarida);
        opciones2.setSpacing(10);
        opciones2.setAlignment(Pos.CENTER);
        opciones3.getChildren().addAll(botonEspiral);
        opciones3.setSpacing(10);
        opciones3.setAlignment(Pos.CENTER);
        opciones.getChildren().addAll(opciones1, opciones2, opciones3);
        opciones.setSpacing(20);
        opciones.setAlignment(Pos.CENTER);
        eleccionUsuario.getChildren().addAll(seleccionarIndividuo, opciones);
        eleccionUsuario.setSpacing(30);
        eleccionUsuario.setAlignment(Pos.CENTER);
        eleccionUsuario.setBackground(new Background(new BackgroundFill(Color.rgb(47, 52, 58), new CornerRadii(0), Insets.EMPTY)));
        Scene sc = new Scene(eleccionUsuario, 800, 300, Color.rgb(47, 52, 58));
        sc.setFill(Color.RED);
        s.initModality(Modality.APPLICATION_MODAL);
        s.setTitle("Tienda Construcciones Zerg");
        s.getIcons().add(this.icono);
        s.setResizable(false);
        s.setScene(sc);
        //s.show();
        s.showAndWait();


        // Label etiqueta = new Label();
        // etiqueta.setText("Agregar Construccion Zerg");
        // // vista.agregarElementosAEjecutar(etiqueta);
        // // tablero.agregarBloqueDobleRepeticion();

        // Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        // alert.setTitle("Construcciones zerg");
        // alert.setHeaderText("Seleccione la construccion que desea construir");

        // ButtonType botonCriadero = new ButtonType("Criadero");
        // ButtonType botonReserva = new ButtonType("Reserva de\nReproduccion");
        // ButtonType botonExtractor = new ButtonType("Extractor");
        // ButtonType botonGuarida = new ButtonType("Guarida");
        // ButtonType botonEspiral = new ButtonType("Espiral");


        // alert.getButtonTypes().setAll(botonCriadero, botonReserva, botonExtractor, botonGuarida, botonEspiral);

        // Optional<ButtonType> result = alert.showAndWait();
        // if (result.get() == botonCriadero){
        //     // Criadero criadero = new Criadero();
        // }else if (result.get() == botonReserva) {
        //     //ReservaDeReproduccion reserva = new ReservaDeReproduccion();
        // } else if (result.get() == botonExtractor) {
        //     // Extractor extractor = new Extractor();
        // } else if (result.get() == botonGuarida) {
        //     // Guarida guarida = new Guarida();
        // }  else if (result.get() == botonEspiral) {
        //     // Espiral espiral = new Espiral();
        // } else {
        //     // ... user chose CANCEL or closed the dialog
        // }
    }

    private void noSePuedeConstruir() {
        InputStream is = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
        Font fuente = Font.loadFont(is, 20);

        Label ingresarPosicion = new Label("Posicion Ingresada Invalida o\n\nRequerimientos Insuficientes!");
        ingresarPosicion.setFont(fuente);
        ingresarPosicion.setStyle(formatoTexto);
        ingresarPosicion.setAlignment(Pos.CENTER);

        Button botonConfirmar = new Button("Confirmar");
        botonConfirmar.setFont(fuente);
        botonConfirmar.setStyle(botonNormal);
        botonConfirmar.setOnMouseEntered(e -> botonConfirmar.setStyle(botonAntesDeSerPresionado));
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
}