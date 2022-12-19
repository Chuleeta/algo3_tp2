package edu.fiuba.algo3.javafx.Eventos;

import edu.fiuba.algo3.javafx.JuegoVista;
import edu.fiuba.algo3.modelo.Construccion;
import edu.fiuba.algo3.modelo.Edificios.Criadero;
import edu.fiuba.algo3.modelo.Edificios.Espiral;
import edu.fiuba.algo3.modelo.Edificios.Guarida;
import edu.fiuba.algo3.modelo.Edificios.ReservaDeReproduccion;
import edu.fiuba.algo3.modelo.Exceptions.*;
import edu.fiuba.algo3.modelo.Individuos.*;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Larva;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
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
    String formatoTexto = "-fx-border-width: 0px; -fx-border-color: #80CEB9; -fx-background-color: transparent; -fx-text-fill: #F3CA4C";

    public BotonAgregarIndividuoZergHandler(JuegoVista juegoVista, Jugador jugador) {
        //this.stage = stage;
        //this.escenaParaDobleRepeticion = escenaParaDobleRepeticion;
        cargarImagenes();
        this.juegoVista = juegoVista;
        this.jugador = jugador;
    }

    private void cargarImagenes() {
        // String pathLogoFondo = this.getClass().getResource("/imagenes/fondoDePantalla.jpg").toString();
        // this.logoFondo = new Image(pathLogoFondo);

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

        return (new Posicion(valorX, valorY));
    }

    private void noSePuedeConstruir(String text) {
        InputStream is = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
        Font fuente = Font.loadFont(is, 20);

        Label ingresarPosicion = new Label(text);
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
        s.setTitle("Fallo Al Construir la unidad");
        s.getIcons().add(this.icono);
        botonConfirmar.setOnAction(e-> {
            s.close();
        });
        s.setScene(sc);
        s.showAndWait();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Stage s = new Stage();

        InputStream is = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
        Font fuente = Font.loadFont(is, 25);

        InputStream is2 = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
        Font fuente2 = Font.loadFont(is2, 25);

        Label seleccionarIndividuo = new Label("Seleccionar Individuo\n");
        seleccionarIndividuo.setFont(fuente);
        seleccionarIndividuo.setStyle(formatoTexto);
        
        HBox opciones = new HBox();
        
        Button botonAmo = new Button("Amo Supremo");
        botonAmo.setFont(fuente2);
        botonAmo.setStyle(botonNormal);
        botonAmo.setOnMouseEntered(e -> botonAmo.setStyle(botonAntesDeSerPresionado));
        botonAmo.setOnMouseExited(e -> botonAmo.setStyle(botonNormal));
        botonAmo.setOnAction(e-> {
            Posicion inputUsuario = this.cargarPosicion();
            System.out.println("\n input usuario: "+ inputUsuario);
        });
        
        Button botonZangano = new Button("Zangano");
        botonZangano.setFont(fuente2);
        botonZangano.setStyle(botonNormal);
        botonZangano.setOnMouseEntered(e -> botonZangano.setStyle(botonAntesDeSerPresionado));
        botonZangano.setOnMouseExited(e -> botonZangano.setStyle(botonNormal));
        botonZangano.setOnAction(e-> {
            Posicion inputUsuario = this.cargarPosicion();
            try {
                new Zangano(new Mineral(1000), inputUsuario, jugador);
                juegoVista.actualizarTablero();
            } catch (RequerimientosInsuficientesException ex) {
                noSePuedeConstruir("\nRequerimientosInsuficientesException");
            } catch (CriaderoNoDisponibleException ex) {
                noSePuedeConstruir("\nCriaderoNoDisponibleException");
            }
            s.close();
        });
        
        Button botonZerling = new Button("Zerling");
        botonZerling.setFont(fuente2);
        botonZerling.setStyle(botonNormal);
        botonZerling.setOnMouseEntered(e -> botonZerling.setStyle(botonAntesDeSerPresionado));
        botonZerling.setOnMouseExited(e -> botonZerling.setStyle(botonNormal));
        botonZerling.setOnAction(e-> {
            Posicion inputUsuario = this.cargarPosicion();
            try {
                new Zerling(new Mineral(1000), inputUsuario, jugador);
                juegoVista.actualizarTablero();
            } catch (RequerimientosInsuficientesException ex) {
                noSePuedeConstruir("\nRequerimientosInsuficientesException");
            } catch (ReservaDeReproduccionNoDisponibleException ex) {
                throw new RuntimeException(ex);
            }
            s.close();
        });
        
        Button botonHidralisco = new Button("Hidralisco");
        botonHidralisco.setFont(fuente2);
        botonHidralisco.setStyle(botonNormal);
        botonHidralisco.setOnMouseEntered(e -> botonHidralisco.setStyle(botonAntesDeSerPresionado));
        botonHidralisco.setOnMouseExited(e -> botonHidralisco.setStyle(botonNormal));
        botonHidralisco.setOnAction(e-> {
            Posicion inputUsuario = this.cargarPosicion();
            try {
                new Hidralisco(new Mineral(1000), new GasVespeno(1000), inputUsuario, jugador);
                juegoVista.actualizarTablero();
            } catch (RequerimientosInsuficientesException ex) {
                noSePuedeConstruir("\nRequerimientosInsuficientesException");
            } catch (GuaridaNoDisponibleException ex) {
                throw new RuntimeException(ex);
            }
            s.close();
        });
        
        Button botonMutalisco = new Button("Mutalisco");
        botonMutalisco.setFont(fuente2);
        botonMutalisco.setStyle(botonNormal);
        botonMutalisco.setOnMouseEntered(e -> botonMutalisco.setStyle(botonAntesDeSerPresionado));
        botonMutalisco.setOnMouseExited(e -> botonMutalisco.setStyle(botonNormal));
        botonMutalisco.setOnAction(e-> {
            Posicion inputUsuario = this.cargarPosicion();
            try {
                new Mutalisco(new Mineral(1000), new GasVespeno(1000), inputUsuario, jugador);
                juegoVista.actualizarTablero();
            } catch (RequerimientosInsuficientesException ex) {
                noSePuedeConstruir("\nRequerimientosInsuficientesException");
            } catch (EspiralNoDisponibleException ex) {
                throw new RuntimeException(ex);
            }
            s.close();
        });
        
        Button botonGuardian = new Button("Guardian");
        botonGuardian.setFont(fuente2);
        botonGuardian.setStyle(botonNormal);
        botonGuardian.setOnMouseEntered(e -> botonGuardian.setStyle(botonAntesDeSerPresionado));
        botonGuardian.setOnMouseExited(e -> botonGuardian.setStyle(botonNormal));
        botonGuardian.setOnAction(e-> {
            Posicion inputUsuario = this.cargarPosicion();
            try {
                new Guardian(new Mineral(1000), new GasVespeno(1000), inputUsuario, jugador);
                juegoVista.actualizarTablero();
            } catch (RequerimientosInsuficientesException ex) {
                noSePuedeConstruir("\nRequerimientosInsuficientesException");
            } catch (EspiralNoDisponibleException ex) {
                noSePuedeConstruir("\nNo existe mutalisco en esa posicion");
            }
            s.close();
        });
        
        Button botonDevorador = new Button("Devorador");
        botonDevorador.setFont(fuente2);
        botonDevorador.setStyle(botonNormal);
        botonDevorador.setOnMouseEntered(e -> botonDevorador.setStyle(botonAntesDeSerPresionado));
        botonDevorador.setOnMouseExited(e -> botonDevorador.setStyle(botonNormal));
        botonDevorador.setOnAction(e-> {
            Posicion inputUsuario = this.cargarPosicion();
            try {
                new Devorador(new Mineral(1000), new GasVespeno(1000), inputUsuario, jugador);
                juegoVista.actualizarTablero();
            } catch (RequerimientosInsuficientesException ex) {
                noSePuedeConstruir("\nRequerimientosInsuficientesException");
            } catch (EspiralNoDisponibleException ex) {
                noSePuedeConstruir("\nNo existe mutalisco en esa posicion");
            }
            s.close();
        });
        
        
        VBox inputPosicion = new VBox();
        VBox opciones1 = new VBox();
        VBox opciones2 = new VBox();
        VBox opciones3 = new VBox();
        opciones1.getChildren().addAll(botonAmo, botonZangano);
        opciones1.setSpacing(10);
        opciones1.setAlignment(Pos.CENTER);
        opciones2.getChildren().addAll(botonZerling ,botonHidralisco);
        opciones2.setSpacing(10);
        opciones2.setAlignment(Pos.CENTER);
        opciones3.getChildren().addAll(botonMutalisco, botonGuardian);
        opciones3.setSpacing(10);
        opciones3.setAlignment(Pos.CENTER);
        opciones.getChildren().addAll(opciones1, opciones2, opciones3);
        opciones.setSpacing(20);
        opciones.setAlignment(Pos.CENTER);
        inputPosicion.getChildren().addAll(seleccionarIndividuo, opciones, botonDevorador);
        inputPosicion.setSpacing(30);
        inputPosicion.setAlignment(Pos.CENTER);
        inputPosicion.setBackground(new Background(new BackgroundFill(Color.rgb(47, 52, 58), new CornerRadii(0), Insets.EMPTY)));
        Scene sc = new Scene(inputPosicion, 800, 300, Color.rgb(47, 52, 58));
        sc.setFill(Color.RED);
        s.initModality(Modality.APPLICATION_MODAL);
        s.setTitle("Tienda Individuos Zerg");
        s.getIcons().add(this.icono);
        s.setResizable(false);
        s.setScene(sc);
        //s.show();
        s.showAndWait();

        // ButtonType botonAmo = new ButtonType("Amo Supremo");
        // ButtonType botonZangano = new ButtonType("Zangano");
        // ButtonType botonZerling = new ButtonType("Zerling");
        // ButtonType botonHidralisco = new ButtonType("Hidralisco");
        // ButtonType botonMutalisco = new ButtonType("Mutalisco");
        //ButtonType botonGuardian = new ButtonType("Guardian");
        //ButtonType botonDevorador = new ButtonType("Devorador");


        //alert.getButtonTypes().setAll(botonAmo, botonZangano, botonZerling, botonHidralisco/*, botonMutaliscobotonGuardian, botonDevorador*/);

        //Optional<ButtonType> result = alert.showAndWait();
        // if (result.get() == botonAmo){
        //     AmoSupremo amo = null;
        //     try {
        //         amo = new AmoSupremo(jugador.mineral, new GasVespeno(0), new Posicion(2, 2), juegoVista.juego.mapa);
        //     } catch (RequerimientosInsuficientesException e) {
        //         e.printStackTrace();
        //     }
        //     jugador.agregarIndividuo(amo);
        // }else if (result.get() == botonZangano) {
        //     String pos = cargarPosicion();
        //     System.out.println("\nPOSICION: " + pos);
        //     //Zangano zangano = new Zangano();
        // } else if (result.get() == botonZerling) {
        //     // Zerling zerling = new Zerling();
        // } else if (result.get() == botonHidralisco) {
        //     // Hidralisco hidralisco = new Hidralisco();
        // }  else if (result.get() == botonMutalisco) {
        //     // Mutalisco mutalisco = new Mutalisco();
        // } else {
        //     // ... user chose CANCEL or closed the dialog
        // }
    }

}