package edu.fiuba.algo3.javafx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.InputStream;
import java.lang.Runnable;

public class MenuPreguntas extends BorderPane{

    static Scene preguntaDatos;
    static String respuesta;
    Boolean confirmacionDatos;
    //String botonAntesDeSerPresionado = "-fx-border-width: 0px; -fx-border-color: #80CEB9; -fx-background-color: rgba(243, 202, 76, 0.5); -fx-text-fill: #42B0D3";
    //String botonNormal = "-fx-border-width: 0px; -fx-border-color: #80CEB9; -fx-background-color: rgba(255, 255, 255, 0.2); -fx-text-fill: #42B0D3";
    String botonAntesDeSerPresionado = "-fx-border-width: 2px; -fx-border-color: #B4DBE2; -fx-background-color: rgba(243, 202, 76, 0.5); -fx-text-fill: #BDB69C; -fx-shape: \"M 100 350 A 50 50 0 1 1 100 250 L 300 250 A 50 50 0 1 1 300 350 Z\";";
    String botonNormal = "-fx-border-width: 2px; -fx-border-color: #B4DBE2; -fx-background-color: rgba(255, 255, 255, 0.2); -fx-text-fill: #42B0D3; -fx-shape: \"M 100 350 A 50 50 0 1 1 100 250 L 300 250 A 50 50 0 1 1 300 350 Z\";";
    String formatoTexto = "-fx-border-width: 0px; -fx-border-color: #80CEB9; -fx-background-color: transparent; -fx-text-fill: #42B0D3";

	private Image logoFondo;
	private Image icono;
	//private Image iconoVolver;


    public MenuPreguntas(Stage stage, Scene pantallaDeInicio) {
        this.cargarImagenes();
		this.setMenuPreguntas(stage, pantallaDeInicio);
        stage.setMaximized(true);
	}

    public Scene getMenuPreguntas(){
        return preguntaDatos;
    }

	private void cargarImagenes() {
		String pathLogoFondo = this.getClass().getResource("/imagenes/fondoDePantalla.jpg").toString();
		this.logoFondo = new Image(pathLogoFondo); 
		
		String pathicono = this.getClass().getResource("/imagenes/icono.png").toString();
		this.icono = new Image(pathicono); 
		
		// String pathIconoVolver = this.getClass().getResource("/imagenes/icono-volver.png").toString();
		// this.iconoVolver = new Image(pathIconoVolver); 
	}

    private void setMenuPreguntas(Stage stage, Scene pantallaDeInicio) {
        InputStream is = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
        Font fuente = Font.loadFont(is, 30);

        //ESCENA PREGUNTAR DATOS DEL JUGADOR Y LA PARTIDA
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        preguntaDatos = new Scene(this,screenSize.getWidth(), screenSize.getHeight(), Color.rgb(47, 52, 58));
        BackgroundImage imagenDeFondo = new BackgroundImage(this.logoFondo, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false));
        this.setBackground(new Background(imagenDeFondo));
    
        //BOTON DE VOLVER A LA PANTALLA DE INICIO
        Button volverPantallaInicial = new Button("Volver al Menu");
        //volverPantallaInicial.setGraphic(new ImageView(this.iconoVolver));
        volverPantallaInicial.setFont(fuente);
        volverPantallaInicial.setStyle(botonNormal);
        volverPantallaInicial.setOnMouseEntered(e -> volverPantallaInicial.setStyle(botonAntesDeSerPresionado));
        volverPantallaInicial.setOnMouseExited(e -> volverPantallaInicial.setStyle(botonNormal));

        volverPantallaInicial.setOnAction(e-> {
			stage.setScene(pantallaDeInicio);
            // String a = this.volverAlMenu(stage);
            // if(a =="Volver"){
            // }
        });
    
        //JUGADOR INSERTA NOMBRE
        VBox preguntasJugador1 = new VBox();
        preguntasJugador1.setAlignment(Pos.CENTER);
        preguntasJugador1.setSpacing(30);
        preguntasJugador1.setStyle("-fx-padding: 100;");

        VBox preguntasJugador2 = new VBox();
        preguntasJugador2.setAlignment(Pos.CENTER);
        preguntasJugador2.setSpacing(30);
        preguntasJugador2.setStyle("-fx-padding: 100;");
        
        //PREGUNTA DEL NOMBRE 1
        Label preguntaNombre1 = new Label("Nombre del Jugador 1");
        preguntaNombre1.setFont(fuente);
        preguntaNombre1.setStyle(formatoTexto);

        //PREGUNTA DEL NOMBRE 2
        Label preguntaNombre2 = new Label("Nombre del Jugador 2");
        preguntaNombre2.setFont(fuente);
        preguntaNombre2.setStyle(formatoTexto);
    
        //INPUT NOMBRE DEL JUGADOR
        TextField nombreDelJugador1 = new TextField();
        nombreDelJugador1.setAlignment(Pos.CENTER);
        nombreDelJugador1.setPromptText("Inserte un nombre");
        nombreDelJugador1.setFont(fuente);
        nombreDelJugador1.setStyle(botonNormal);
        nombreDelJugador1.setOnMouseEntered(e -> nombreDelJugador1.setStyle(botonAntesDeSerPresionado));
        nombreDelJugador1.setOnMouseExited(e -> nombreDelJugador1.setStyle(botonNormal));

        TextField nombreDelJugador2 = new TextField();
        nombreDelJugador2.setAlignment(Pos.CENTER);
        nombreDelJugador2.setPromptText("Inserte un nombre");
        nombreDelJugador2.setFont(fuente);
        nombreDelJugador2.setStyle(botonNormal);
        nombreDelJugador2.setOnMouseEntered(e -> nombreDelJugador2.setStyle(botonAntesDeSerPresionado));
        nombreDelJugador2.setOnMouseExited(e -> nombreDelJugador2.setStyle(botonNormal));

        //PREGUNTA DEL NOMBRE
        Label seleccioneUnaRaza1 = new Label("Seleccione Una Raza");
        seleccioneUnaRaza1.setFont(fuente);
        seleccioneUnaRaza1.setStyle(formatoTexto);

        Label seleccioneUnaRaza2 = new Label("Seleccione Una Raza");
        seleccioneUnaRaza2.setFont(fuente);
        seleccioneUnaRaza2.setStyle(formatoTexto);
    
        //INPUT NOMBRE DEL JUGADOR
        ChoiceBox<String> seleccionRaza1 = new ChoiceBox();
        seleccionRaza1.getItems().addAll("Zerg", "Protoss");
        seleccionRaza1.setMinWidth(200);
        seleccionRaza1.setMinHeight(100);
        seleccionRaza1.setStyle("-fx-border-width: 0px; -fx-border-color: #2F343A; -fx-background-color: #717D8C; -fx-text-fill: #BDB69C; -fx-font-family: Impact; -fx-font-size: 40; -fx-color: #BDB69C");
        seleccionRaza1.setOnMouseEntered(e -> seleccionRaza1.setStyle("-fx-border-width: 0px; -fx-border-color: #2F343A; -fx-background-color: #717D8C; -fx-text-fill: #BDB69C; -fx-font-family: Impact; -fx-font-size: 40; -fx-color: #BDB69C"));
        seleccionRaza1.setOnMouseExited(e -> seleccionRaza1.setStyle("-fx-border-width: 0px; -fx-border-color: #2F343A; -fx-background-color: #717D8C; -fx-text-fill: #BDB69C; -fx-font-family: Impact; -fx-font-size: 40; -fx-color: #BDB69C"));

        ChoiceBox<String> seleccionRaza2 = new ChoiceBox();
        seleccionRaza2.getItems().addAll("Zerg", "Protoss");
        seleccionRaza2.setMinWidth(200);
        seleccionRaza2.setMinHeight(100);
        seleccionRaza2.setStyle("-fx-border-width: 0px; -fx-border-color: #2F343A; -fx-background-color: #717D8C; -fx-text-fill: #BDB69C; -fx-font-family: Impact; -fx-font-size: 40; -fx-color: #BDB69C");
        seleccionRaza2.setOnMouseEntered(e -> seleccionRaza2.setStyle("-fx-border-width: 0px; -fx-border-color: #2F343A; -fx-background-color: #717D8C; -fx-text-fill: #BDB69C; -fx-font-family: Impact; -fx-font-size: 40; -fx-color: #BDB69C"));
        seleccionRaza2.setOnMouseExited(e -> seleccionRaza2.setStyle("-fx-border-width: 0px; -fx-border-color: #2F343A; -fx-background-color: #717D8C; -fx-text-fill: #BDB69C; -fx-font-family: Impact; -fx-font-size: 40; -fx-color: #BDB69C"));


        //BOTON SIGUIENTE
        Button siguiente = new Button("Aceptar");
        siguiente.setFont(fuente);
        siguiente.setStyle(botonNormal);
        siguiente.setOnMouseEntered(e -> siguiente.setStyle(botonAntesDeSerPresionado));
        siguiente.setOnMouseExited(e -> siguiente.setStyle(botonNormal));
        
        siguiente.setOnAction(e-> {
            if(preguntarDatosCorrectos(nombreDelJugador1.getText(), seleccionRaza1.getValue(), nombreDelJugador2.getText(), seleccionRaza2.getValue())){
                if(seleccionRaza1.getValue() != null && !(nombreDelJugador1.getText().isBlank())){
                    //JuegoVista juegoVista = new JuegoVista(stage, pantallaDeInicio,20, 14, nombreDelJugador.getText(), seleccionVehiculo.getValue());
                    //stage.setScene(juegoVista.getJuegoVista());
				} else{
                    datosIncorrectos();
                }
            }
        });
		
        //MAPA DEL JUEGO
        preguntasJugador1.getChildren().add(preguntaNombre1);
        preguntasJugador1.getChildren().add(nombreDelJugador1);
        preguntasJugador1.getChildren().add(seleccioneUnaRaza1);
        preguntasJugador1.getChildren().add(seleccionRaza1);

        preguntasJugador2.getChildren().add(preguntaNombre2);
        preguntasJugador2.getChildren().add(nombreDelJugador2);
        preguntasJugador2.getChildren().add(seleccioneUnaRaza2);
        preguntasJugador2.getChildren().add(seleccionRaza2);

        HBox preguntas = new HBox();
        preguntas.setAlignment(Pos.CENTER);
        preguntas.setSpacing(0);
        preguntas.getChildren().add(preguntasJugador1);
        preguntas.getChildren().add(preguntasJugador2);

        VBox overlayPreguntas = new VBox();
        overlayPreguntas.setAlignment(Pos.CENTER);
        overlayPreguntas.getChildren().add(preguntas);
        overlayPreguntas.getChildren().add(siguiente);

        this.setTop(volverPantallaInicial);
        this.setCenter(overlayPreguntas);
    }

    private void datosIncorrectos(){
        InputStream is = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
        Font fuente = Font.loadFont(is, 50);

        Stage ventanaDatosIncorrectos = new Stage();
        ventanaDatosIncorrectos.setResizable(false);
        ventanaDatosIncorrectos.setTitle("Datos Incorrectos");
        
        Button botonVolverAIngresarDatos = new Button("Volver a Ingresar Datos");
        botonVolverAIngresarDatos.setFont(fuente);
        botonVolverAIngresarDatos.setStyle(botonNormal);
        botonVolverAIngresarDatos.setOnMouseEntered(e -> botonVolverAIngresarDatos.setStyle(botonAntesDeSerPresionado));
        botonVolverAIngresarDatos.setOnMouseExited(e -> botonVolverAIngresarDatos.setStyle(botonNormal));
        
        VBox menuDatosIngresados = new VBox(20);
        
        Label avisoDatos = new Label("Los datos ingresados no son validos, asegurate de:\n");
        avisoDatos.setFont(fuente);
        avisoDatos.setStyle(formatoTexto);
        
        Label avisoVehiculo = new Label("      -Si o si tenés que seleccionar un Vehículo\n");
        avisoVehiculo.setFont(fuente);
        avisoVehiculo.setStyle(formatoTexto);
        
        Label avisoNombre = new Label("      -Tu nombre tiene que tener por lo menos un caracter");
        avisoNombre.setFont(fuente);
        avisoNombre.setStyle(formatoTexto);
        
        ventanaDatosIncorrectos.setMinWidth(280);
        ventanaDatosIncorrectos.initModality(Modality.APPLICATION_MODAL);
        
        menuDatosIngresados.getChildren().addAll(avisoDatos, avisoVehiculo, avisoNombre, botonVolverAIngresarDatos);
        menuDatosIngresados.setAlignment(Pos.CENTER);
        menuDatosIngresados.setStyle("-fx-border-color: #2F343A; -fx-background-color: #2F343A");
        
        
        botonVolverAIngresarDatos.setOnAction(e->{
            confirmacionDatos = false;
            ventanaDatosIncorrectos.close();
        });

        Scene  escenaDatosIngresados = new Scene(menuDatosIngresados , 650 , 400, Color.rgb(47, 52, 58));
        ventanaDatosIncorrectos.getIcons().add(this.icono);
        ventanaDatosIncorrectos.setScene(escenaDatosIngresados);
        ventanaDatosIncorrectos.showAndWait();
    }


    private boolean preguntarDatosCorrectos(String nombre1, String razaElejida1, String nombre2, String razaElejida2){
        InputStream is = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
        InputStream is2 = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
        Font fuente = Font.loadFont(is, 30);
        Font fuente2 = Font.loadFont(is2, 50);

        Stage ventanaPreguntaDatos = new Stage();
        ventanaPreguntaDatos.setResizable(false);
        ventanaPreguntaDatos.setTitle("Datos Ingresados");
        
        Button botonConfirmar = new Button("Confirmar");
        botonConfirmar.setFont(fuente);
        botonConfirmar.setStyle(botonNormal);
        botonConfirmar.setOnMouseEntered(e -> botonConfirmar.setStyle(botonAntesDeSerPresionado));
        botonConfirmar.setOnMouseExited(e -> botonConfirmar.setStyle(botonNormal));
        
        Button botonVolverAIngresarDatos = new Button("Volver a Ingresar Datos");
        botonVolverAIngresarDatos.setFont(fuente);
        botonVolverAIngresarDatos.setStyle(botonNormal);
        botonVolverAIngresarDatos.setOnMouseEntered(e -> botonVolverAIngresarDatos.setStyle(botonAntesDeSerPresionado));
        botonVolverAIngresarDatos.setOnMouseExited(e -> botonVolverAIngresarDatos.setStyle(botonNormal));
        
        VBox menuDatosIngresados = new VBox(20);
        
        Label preguntaDatosCorrectos = new Label("Mantener Estos Datos?");
        preguntaDatosCorrectos.setFont(fuente2);
        preguntaDatosCorrectos.setStyle(formatoTexto);

        Label jugadorUno = new Label("Jugador 1");
        jugadorUno.setFont(fuente);
        jugadorUno.setStyle("-fx-border-width: 0px; -fx-border-color: transparent; -fx-background-color: transparent; -fx-text-fill: #BDB69C");
        
        Label nombreDado1 = new Label("Nombre: " + nombre1);
        nombreDado1.setFont(fuente);
        nombreDado1.setStyle("-fx-border-width: 0px; -fx-border-color: transparent; -fx-background-color: transparent; -fx-text-fill: #BDB69C");

        Label razaSeleccionada1 = new Label("Raza: " + razaElejida1);
        razaSeleccionada1.setFont(fuente);
        razaSeleccionada1.setStyle("-fx-border-width: 0px; -fx-border-color: transparent; -fx-background-color: transparent; -fx-text-fill: #BDB69C");

        Label jugadorDos = new Label("Jugador 2");
        jugadorDos.setFont(fuente);
        jugadorDos.setStyle("-fx-border-width: 0px; -fx-border-color: transparent; -fx-background-color: transparent; -fx-text-fill: #BDB69C");

        Label nombreDado2 = new Label("Nombre: " + nombre2);
        nombreDado2.setFont(fuente);
        nombreDado2.setStyle("-fx-border-width: 0px; -fx-border-color: transparent; -fx-background-color: transparent; -fx-text-fill: #BDB69C");

        Label razaSeleccionada2 = new Label("Raza: " + razaElejida2);
        razaSeleccionada2.setFont(fuente);
        razaSeleccionada2.setStyle("-fx-border-width: 0px; -fx-border-color: transparent; -fx-background-color: transparent; -fx-text-fill: #BDB69C");

        ventanaPreguntaDatos.setMinWidth(580);
        ventanaPreguntaDatos.initModality(Modality.APPLICATION_MODAL);
        
        VBox datosJ1 = new VBox();
        datosJ1.getChildren().addAll(jugadorUno, nombreDado1, razaSeleccionada1);
        datosJ1.setSpacing(20);
        VBox datosJ2 = new VBox();
        datosJ2.getChildren().addAll(jugadorDos, nombreDado2, razaSeleccionada2);
        datosJ2.setSpacing(20);

        HBox ambosDatos = new HBox();
        ambosDatos.getChildren().addAll(datosJ1, datosJ2);
        ambosDatos.setAlignment(Pos.CENTER);
        ambosDatos.setSpacing(100);

        menuDatosIngresados.getChildren().addAll(preguntaDatosCorrectos, ambosDatos, botonConfirmar, botonVolverAIngresarDatos);
        menuDatosIngresados.setAlignment(Pos.CENTER);
        menuDatosIngresados.setStyle("-fx-border-color: #131E28; -fx-background-color: #131E28");
        
        botonConfirmar.setOnAction(e-> {
            confirmacionDatos = true;
            ventanaPreguntaDatos.close();
        });
        
        botonVolverAIngresarDatos.setOnAction(e->{
            confirmacionDatos = false;
            ventanaPreguntaDatos.close();
        });

        Scene  escenaDatosIngresados = new Scene(menuDatosIngresados , 900 , 500, Color.rgb(47, 52, 58));
        ventanaPreguntaDatos.getIcons().add(this.icono);
        ventanaPreguntaDatos.setScene(escenaDatosIngresados);
        ventanaPreguntaDatos.showAndWait();
        return(confirmacionDatos);
    }
}
