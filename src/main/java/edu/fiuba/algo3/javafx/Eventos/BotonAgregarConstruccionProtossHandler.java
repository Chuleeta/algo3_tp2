package edu.fiuba.algo3.javafx.Eventos;

import edu.fiuba.algo3.javafx.JuegoVista;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Edificios.Acceso;
import edu.fiuba.algo3.modelo.Edificios.Asimilador;
import edu.fiuba.algo3.modelo.Edificios.NexoMineral;
import edu.fiuba.algo3.modelo.Edificios.Pilon;
import edu.fiuba.algo3.modelo.Edificios.PuertoEstelar;
import edu.fiuba.algo3.modelo.Exceptions.MenaOcupadaException;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.Exceptions.VolcanOcupadoException;
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

public class BotonAgregarConstruccionProtossHandler  implements EventHandler<ActionEvent> {

    JuegoVista juegoVista;
    private final Jugador jugador;
    private Image icono;
    String botonAntesDeSerPresionado = "-fx-border-width: 2px; -fx-border-color: #B4DBE2; -fx-background-color: rgba(243, 202, 76, 0.5); -fx-text-fill: #BDB69C; -fx-shape: \"M 100 350 A 50 50 0 1 1 100 250 L 300 250 A 50 50 0 1 1 300 350 Z\";";
    String botonNormal = "-fx-border-width: 2px; -fx-border-color: #B4DBE2; -fx-background-color: rgba(255, 255, 255, 0.2); -fx-text-fill: #42B0D3; -fx-shape: \"M 100 350 A 50 50 0 1 1 100 250 L 300 250 A 50 50 0 1 1 300 350 Z\";";
    String formatoTexto = "-fx-border-width: 0px; -fx-border-color: #80CEB9; -fx-background-color: transparent; -fx-text-fill: #F3CA4C";

    public BotonAgregarConstruccionProtossHandler(JuegoVista juegoVista, Jugador jugador) {
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
        
        return (new Posicion(valorX-1, valorY-1));
    }
    
    
    @Override
    public void handle(ActionEvent actionEvent) {
        InputStream is = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
        Font fuente = Font.loadFont(is, 25);
        Stage s = new Stage();
        
        InputStream is2 = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
        Font fuente2 = Font.loadFont(is2, 25);

        Label seleccionarIndividuo = new Label("Seleccionar Construccion\n");
        seleccionarIndividuo.setFont(fuente);
        seleccionarIndividuo.setStyle(formatoTexto);
        
        HBox opciones = new HBox();
        
        Button botonNexo = new Button("Nexo\nMineral");
        botonNexo.setFont(fuente2);
        botonNexo.setStyle(botonNormal);
        botonNexo.setOnMouseEntered(e -> botonNexo.setStyle(botonAntesDeSerPresionado));
        botonNexo.setOnMouseExited(e -> botonNexo.setStyle(botonNormal));
        botonNexo.setOnAction(e-> {
            Posicion inputUsuario = this.cargarPosicion();
            try {
                System.out.println(this.cargarPosicion().coordenadaX() + "" + this.cargarPosicion().coordenadaY());
                NexoMineral nexo = new NexoMineral(inputUsuario, this.jugador);
                juegoVista.actualizarTablero();
                
            } catch (MenaOcupadaException e1) {
                noSePuedeConstruir();
            } catch (RecursosInsuficientesException e1) {
                noSePuedeConstruir();
            }
            // if(this.jugador.agregarConstruccion(nexo)){
            // }else{
            //     noSePuedeConstruir();
            // }
            s.close();
        });
        
        Button botonPilon = new Button("Pilon");
        botonPilon.setFont(fuente2);
        botonPilon.setStyle(botonNormal);
        botonPilon.setOnMouseEntered(e -> botonPilon.setStyle(botonAntesDeSerPresionado));
        botonPilon.setOnMouseExited(e -> botonPilon.setStyle(botonNormal));
        botonPilon.setOnAction(e-> {
            Posicion inputUsuario = this.cargarPosicion();
            try {
                Pilon pilon = new Pilon(inputUsuario, this.jugador);
                juegoVista.actualizarTablero();
            } catch (RecursosInsuficientesException e1) {
                noSePuedeConstruir();
            }
            s.close();
        });
        
        Button botonAsimilador = new Button("Asimilador");
        botonAsimilador.setFont(fuente2);
        botonAsimilador.setStyle(botonNormal);
        botonAsimilador.setOnMouseEntered(e -> botonAsimilador.setStyle(botonAntesDeSerPresionado));
        botonAsimilador.setOnMouseExited(e -> botonAsimilador.setStyle(botonNormal));
        botonAsimilador.setOnAction(e-> {
            Posicion inputUsuario = this.cargarPosicion();
            try {
                Asimilador asimilador = new Asimilador(inputUsuario, this.jugador);
                juegoVista.actualizarTablero();
            } catch (RecursosInsuficientesException e1) {
                noSePuedeConstruir();
            } catch (VolcanOcupadoException e1) {
                noSePuedeConstruir();
            }
            s.close();
        });
        
        Button botonAcceso = new Button("Acceso");
        botonAcceso.setFont(fuente2);
        botonAcceso.setStyle(botonNormal);
        botonAcceso.setOnMouseEntered(e -> botonAcceso.setStyle(botonAntesDeSerPresionado));
        botonAcceso.setOnMouseExited(e -> botonAcceso.setStyle(botonNormal));
        botonAcceso.setOnAction(e-> {
            Posicion inputUsuario = this.cargarPosicion();
            System.out.println(this.cargarPosicion().coordenadaX() + "" + this.cargarPosicion().coordenadaY());
            try {
                Acceso acceso = new Acceso(inputUsuario, this.jugador);
                juegoVista.actualizarTablero();
            } catch (RecursosInsuficientesException e1) {
                noSePuedeConstruir();
            }
            s.close();
        });
        
        Button botonPuerto = new Button("Puerto\nEstelar");
        botonPuerto.setFont(fuente2);
        botonPuerto.setStyle(botonNormal);
        botonPuerto.setOnMouseEntered(e -> botonPuerto.setStyle(botonAntesDeSerPresionado));
        botonPuerto.setOnMouseExited(e -> botonPuerto.setStyle(botonNormal));
        botonPuerto.setOnAction(e-> {
            Posicion inputUsuario = this.cargarPosicion();
            try {
                PuertoEstelar puertoEstelar = new PuertoEstelar(inputUsuario, this.jugador);
                juegoVista.actualizarTablero();
            } catch (RecursosInsuficientesException e1) {
                noSePuedeConstruir();
            } catch (NoExisteEdificioCorrelativoException e1) {
                noSePuedeConstruir();
            }
            s.close();
        });
        
        VBox eleccionUsuario = new VBox();
        VBox opciones1 = new VBox();
        VBox opciones2 = new VBox();
        VBox opciones3 = new VBox();
        opciones1.getChildren().addAll(botonNexo, botonPilon);
        opciones1.setSpacing(10);
        opciones1.setAlignment(Pos.CENTER);
        opciones2.getChildren().addAll(botonAsimilador ,botonAcceso);
        opciones2.setSpacing(10);
        opciones2.setAlignment(Pos.CENTER);
        opciones3.getChildren().addAll(botonPuerto);
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
        s.setTitle("Tienda Construcciones Protoss");
        s.getIcons().add(this.icono);
        s.setResizable(false);
        s.setScene(sc);
        //s.show();
        s.showAndWait();
        
        
        
        // Label etiqueta = new Label();
        // etiqueta.setText("Agregar Construccion Protoss");
        // // vista.agregarElementosAEjecutar(etiqueta);
        // // tablero.agregarBloqueDobleRepeticion();

        // Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        // alert.setTitle("Construcciones protoss");
        // alert.setHeaderText("Seleccione la construccion que desea construir");

        // ButtonType botonNexo = new ButtonType("Nexo Mineral");
        // ButtonType botonPilon = new ButtonType("Pilon");
        // ButtonType botonAsimilador = new ButtonType("Asimilador");
        // ButtonType botonAcceso = new ButtonType("Acceso");
        // ButtonType botonPuerto = new ButtonType("Puerto Estelar");


        // alert.getButtonTypes().setAll(botonNexo, botonPilon, botonAsimilador, botonAcceso, botonPuerto);

        // Optional<ButtonType> result = alert.showAndWait();
        // if (result.get() == botonNexo){
        //     // NexoMineral nexo = new NexoMineral();
        // }else if (result.get() == botonPilon) {
        //     //Pilon pilon = new Pilon();
        // } else if (result.get() == botonAsimilador) {
        //     // Asimilador asimilador = new Asimilador();
        // } else if (result.get() == botonAcceso) {
        //     // Acceso acceso = new Acceso();
        // }  else if (result.get() == botonPuerto) {
        //     // PuertoEstelar puerto = new PuertoEstelar();
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
