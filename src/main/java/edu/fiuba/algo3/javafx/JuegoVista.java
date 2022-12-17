package edu.fiuba.algo3.javafx;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import edu.fiuba.algo3.javafx.Eventos.*;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class JuegoVista extends BorderPane {
    static String respuesta;
    static Scene juegoVista;
    static ToolBar barra;
    static Group contenedorCentral;

    String botonAntesDeSerPresionado = "-fx-border-width: 2px; -fx-border-color: #B4DBE2; -fx-background-color: rgba(243, 202, 76, 0.5); -fx-text-fill: #BDB69C; -fx-shape: \"M 100 350 A 50 50 0 1 1 100 250 L 300 250 A 50 50 0 1 1 300 350 Z\";";
    String botonNormal = "-fx-border-width: 2px; -fx-border-color: #B4DBE2; -fx-background-color: rgba(255, 255, 255, 0.2); -fx-text-fill: #42B0D3; -fx-shape: \"M 100 350 A 50 50 0 1 1 100 250 L 300 250 A 50 50 0 1 1 300 350 Z\";";
    String formatoTexto = "-fx-border-width: 0px; -fx-border-color: #80CEB9; -fx-background-color: transparent; -fx-text-fill: #F3CA4C";

	private Image logo;
	private Image fondo;
    private Image datosJugador;
    private String turnoJugadorActual;
    
    private Jugador jugadorUno;
    private Jugador jugadorDos;
    private Juego juego;
    private Tablero tablero;
    private int pisicionSeleccionadaX;
    private int pisicionSeleccionadaY;
    private Label posicionSeleccionada;
    //public Stage stage;
    
    public JuegoVista(Stage stage, Scene pantallaDeInicio, int ancho, int alto, Juego juego,String nombreJugador1, String eleccionRaza1, String nombreJugador2, String eleccionRaza2) throws RequerimientosInsuficientesException{
        this.pisicionSeleccionadaX = 1;
        this.pisicionSeleccionadaY = 1;
        
        this.juego = juego;
        this.cargarImagenes();
		this.setJuego(stage, pantallaDeInicio, ancho, alto, nombreJugador1, eleccionRaza1, nombreJugador2, eleccionRaza2);
        stage.setMaximized(true);
        BackgroundImage imagenDeFondo = new BackgroundImage(this.fondo, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        this.setBackground(new Background(imagenDeFondo));
        //this.stage = stage;
    }

    public Scene getJuegoVista(){
        return juegoVista;
    }

	private void cargarImagenes() {
		String pathlogo = this.getClass().getResource("/imagenes/icono.png").toString();
        this.logo = new Image(pathlogo);
        
		String pathFondo = this.getClass().getResource("/imagenes/fondoDePantalla.jpg").toString();
		this.fondo = new Image(pathFondo);

        String pathMenuJugador = this.getClass().getResource("/imagenes/fondoDatosJugadador.png").toString();
		this.datosJugador = new Image(pathMenuJugador);
		
	}

    public void  setPosicionSeleccionada(int X, int Y){
       this.pisicionSeleccionadaX = X;
       this.pisicionSeleccionadaY = Y;
       this.posicionSeleccionada.setText(String.format("Posicion Seleccionada - En X: " + this.pisicionSeleccionadaX + " | En Y: " + this.pisicionSeleccionadaY));
    }

    private void setJuego(Stage stage, Scene pantallaDeInicio, int ancho, int alto, String nombreJugador1, String eleccionRaza1, String nombreJugador2, String eleccionRaza2) throws RequerimientosInsuficientesException{
        InputStream is = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
        Font fuente = Font.loadFont(is, 25);

        //JUGADOR Jugadndo
        this.turnoJugadorActual = "1";
        Label nombreDelJugador = new Label("Turno Jugador : " + turnoJugadorActual);
        nombreDelJugador.setFont(fuente);
        nombreDelJugador.setStyle(formatoTexto);
        this.posicionSeleccionada = new Label("Posicion Seleccionada - En X: " + this.pisicionSeleccionadaX + " | En Y: " + this.pisicionSeleccionadaY);
        this.posicionSeleccionada.setFont(fuente);
        this.posicionSeleccionada.setStyle(formatoTexto);
        VBox turnoActual = new VBox();
        turnoActual.getChildren().addAll(nombreDelJugador, this.posicionSeleccionada);
        turnoActual.setAlignment(Pos.CENTER);
        turnoActual.setBackground(new Background(new BackgroundFill(Color.rgb(63, 84, 99, 0.7), new CornerRadii(20), Insets.EMPTY)));


        //NOMBRE DEL JUGADOR 1
        Label nombreDelJugador1 = new Label("Jugador 1: \n" + nombreJugador1);
        nombreDelJugador1.setFont(fuente);
        nombreDelJugador1.setStyle(formatoTexto);
        nombreDelJugador1.setPadding(new Insets(0, 0, 0, 25));

        //NOMBRE DEL JUGADOR 2
        Label nombreDelJugador2 = new Label("Jugador 2: \n" + nombreJugador2);
        nombreDelJugador2.setFont(fuente);
        nombreDelJugador2.setStyle(formatoTexto);
        nombreDelJugador2.setPadding(new Insets(0, 0, 0, 25));
        Mapa mapa = new Mapa();


        this.jugadorUno = new Jugador(nombreJugador1, "rojo", eleccionRaza1, new Posicion(0, 0), mapa, 0);
        this.jugadorDos = new Jugador(nombreJugador2, "azul", eleccionRaza2, new Posicion(ancho, alto), mapa, 0);
        /*if (eleccionRaza1 == "Zerg") {
            jugadorUno = jugadorUno.definirZerg();
            jugadorDos = jugadorDos.definirProtoss();
            jugadorUno = new JugadorZerg(nombreJugador1, "rojo", eleccionRaza1, new Posicion(0, 0), mapa, 0);
            jugadorDos = new JugadorProtoss(nombreJugador2, "azul", eleccionRaza2, new Posicion(ancho, alto), mapa, 0);
        } else {
            jugadorUno = new JugadorProtoss(nombreJugador1, "rojo", eleccionRaza1, new Posicion(0, 0), mapa, 0);
            jugadorDos = new JugadorZerg(nombreJugador2, "azul", eleccionRaza2, new Posicion(ancho, alto), mapa, 0);
        }*/

        this.juego = new Juego(mapa, jugadorUno, jugadorDos);

        //raza
        Label razaJugador1 = new Label("Raza: \n" + eleccionRaza1);
        razaJugador1.setFont(fuente);
        razaJugador1.setStyle(formatoTexto);
        razaJugador1.setPadding(new Insets(0, 0, 0, 25));

        //raza
        Label razaJugador2 = new Label("Raza: \n" + eleccionRaza2);
        razaJugador2.setFont(fuente);
        razaJugador2.setStyle(formatoTexto);
        razaJugador2.setPadding(new Insets(0, 0, 0, 25));

        BotonAgregarConstruccionZergHandler agregarConstruccionZergHandler;
        BotonAgregarIndividuoZergHandler agregarIndividuoZergHandler;
        BotonAgregarConstruccionProtossHandler agregarConstruccionProtossHandler;
        BotonAgregarIndividuoProtossHandler agregarIndividuoProtossHandler;

        if (eleccionRaza1 == "Zerg") {
            agregarConstruccionZergHandler = new BotonAgregarConstruccionZergHandler(this, jugadorUno);
            agregarIndividuoZergHandler = new BotonAgregarIndividuoZergHandler(this, jugadorUno);
            agregarConstruccionProtossHandler = new BotonAgregarConstruccionProtossHandler(this, jugadorDos);
            agregarIndividuoProtossHandler = new BotonAgregarIndividuoProtossHandler(this, jugadorDos);

        } else {
            agregarConstruccionZergHandler = new BotonAgregarConstruccionZergHandler(this, jugadorDos);
            agregarIndividuoZergHandler = new BotonAgregarIndividuoZergHandler(this, jugadorDos);
            agregarConstruccionProtossHandler = new BotonAgregarConstruccionProtossHandler(this, jugadorUno);
            agregarIndividuoProtossHandler = new BotonAgregarIndividuoProtossHandler(this, jugadorUno);
        }

        // creacion de botones
        Button botonAgregarConstruccionZerg = new Button("Agregar\nconstruccion");
        botonAgregarConstruccionZerg.setOnAction(agregarConstruccionZergHandler);
        botonAgregarConstruccionZerg.setTranslateX(20);
        botonAgregarConstruccionZerg.setTranslateY(-168);
        botonAgregarConstruccionZerg.setFont(fuente);
        botonAgregarConstruccionZerg.setStyle(botonNormal);
        botonAgregarConstruccionZerg.setOnMouseEntered(e -> botonAgregarConstruccionZerg.setStyle(botonAntesDeSerPresionado));
        botonAgregarConstruccionZerg.setOnMouseExited(e -> botonAgregarConstruccionZerg.setStyle(botonNormal));

        Button botonAgregarConstruccionProtoss = new Button("Agregar\nconstruccion");
        botonAgregarConstruccionProtoss.setOnAction(agregarConstruccionProtossHandler);
        botonAgregarConstruccionProtoss.setTranslateX(20);
        botonAgregarConstruccionProtoss.setTranslateY(-168);
        botonAgregarConstruccionProtoss.setFont(fuente);
        botonAgregarConstruccionProtoss.setStyle(botonNormal);
        botonAgregarConstruccionProtoss.setOnMouseEntered(e -> botonAgregarConstruccionProtoss.setStyle(botonAntesDeSerPresionado));
        botonAgregarConstruccionProtoss.setOnMouseExited(e -> botonAgregarConstruccionProtoss.setStyle(botonNormal));

        Button botonAgregarIndividuoZerg = new Button("Agregar\nindividuo");
        botonAgregarIndividuoZerg.setOnAction(agregarIndividuoZergHandler);
        botonAgregarIndividuoZerg.setTranslateX(20);
        botonAgregarIndividuoZerg.setTranslateY(-168);
        botonAgregarIndividuoZerg.setFont(fuente);
        botonAgregarIndividuoZerg.setStyle(botonNormal);
        botonAgregarIndividuoZerg.setOnMouseEntered(e -> botonAgregarIndividuoZerg.setStyle(botonAntesDeSerPresionado));
        botonAgregarIndividuoZerg.setOnMouseExited(e -> botonAgregarIndividuoZerg.setStyle(botonNormal));

        Button botonAgregarIndividuoProtoss = new Button("Agregar\nindividuo");
        botonAgregarIndividuoProtoss.setOnAction(agregarIndividuoProtossHandler);
        botonAgregarIndividuoProtoss.setTranslateX(20);
        botonAgregarIndividuoProtoss.setTranslateY(-168);
        botonAgregarIndividuoProtoss.setFont(fuente);
        botonAgregarIndividuoProtoss.setStyle(botonNormal);
        botonAgregarIndividuoProtoss.setOnMouseEntered(e -> botonAgregarIndividuoProtoss.setStyle(botonAntesDeSerPresionado));
        botonAgregarIndividuoProtoss.setOnMouseExited(e -> botonAgregarIndividuoProtoss.setStyle(botonNormal));


        // label de minerales

        Label mineralesJugadorUno = new Label("Minerales: \n" + jugadorUno.mineral.cantidad);
        Label mineralesJugadorDos = new Label("Minerales: \n" + jugadorDos.mineral.cantidad);
        mineralesJugadorUno.setFont(fuente);
        mineralesJugadorUno.setStyle(formatoTexto);
        mineralesJugadorUno.setPadding(new Insets(0, 0, 0, 25));
        mineralesJugadorDos.setFont(fuente);
        mineralesJugadorDos.setStyle(formatoTexto);
        mineralesJugadorDos.setPadding(new Insets(0, 0, 0, 20));
        
        //GET MAPA DEBE SER UN GROUP
        this.tablero = new Tablero(alto,ancho, juego, this);
        VBox cosasDelCentro = new VBox();
        cosasDelCentro.getChildren().addAll(turnoActual, tablero.getContenedor());
        cosasDelCentro.setAlignment(Pos.CENTER);
        this.setCenter(cosasDelCentro);

		// Menu bar
        MenuBar barraSuperior = new MenuBar();
        barraSuperior.setStyle("-fx-border-width: 0px; -fx-border-color: #2F343A; -fx-background-color: #717D8C; -fx-text-fill: #BDB69C; -fx-font-family: Helvetica; -fx-font-size: 15");
        
        Menu archivo = new Menu("Archivo");
        barraSuperior.getMenus().add(archivo);
        this.setAlignment(barraSuperior, Pos.CENTER);

        Menu ver = new Menu("Ver");
        barraSuperior.getMenus().add(ver);
        
        Menu ayuda = new Menu("Ayuda");
        barraSuperior.getMenus().add(ayuda);
        
        MenuItem archivoSalir = new MenuItem("Salir");
		archivoSalir.setOnAction(e-> {
            String a = volverAlMenu(stage);
            if(a =="Volver"){
                stage.setScene(pantallaDeInicio);
            }
        });
        archivo.getItems().addAll(archivoSalir);
        
        MenuItem ayudaAcercaDe = new MenuItem("Acerca De");
        MenuItem ayudaComoJugar = new MenuItem("Como Jugar");
        ayudaComoJugar.setOnAction(e -> comoJugar());
        ayudaAcercaDe.setOnAction(e -> acercaDe());
        
        ayuda.getItems().addAll(ayudaComoJugar, new SeparatorMenuItem(), ayudaAcercaDe);
        
        MenuItem pantallaCompleta = new MenuItem("Pantalla Completa");
        pantallaCompleta.setOnAction(e -> stage.setFullScreen(!stage.isFullScreen()));
        ver.getItems().addAll(pantallaCompleta);
        
        this.setTop(barraSuperior);
        

        //BORDE IZQUIERDO
        VBox bordeIzquierdo = new VBox();
        
        
        // VBox bordeIzquierdo = new VBox().prefWidthProperty().bind(stage.widthProperty().multiply(0.80));
        if (eleccionRaza1 == "Zerg") {
            VBox botonesBordeIzquierdoZerg = new VBox();
            botonesBordeIzquierdoZerg.getChildren().addAll(botonAgregarConstruccionZerg, botonAgregarIndividuoZerg);
            botonesBordeIzquierdoZerg.setSpacing(50);
            VBox datosJugador1 = new VBox();
            datosJugador1.getChildren().addAll(nombreDelJugador1, razaJugador1, mineralesJugadorUno);
            datosJugador1.setSpacing(30);
            datosJugador1.setBackground(new Background(new BackgroundFill(Color.rgb(63, 84, 99, 0.6), new CornerRadii(20), Insets.EMPTY)));
            bordeIzquierdo.getChildren().addAll(botonesBordeIzquierdoZerg, datosJugador1);
            //bordeIzquierdo.getChildren().addAll(botonesBordeIzquierdoZerg,  nombreDelJugador1, razaJugador1, mineralesJugadorUno);
            //bordeIzquierdo.getChildren().addAll(nombreDelJugador1, razaJugador1,  botonAgregarIndividuoZerg, botonAgregarConstruccionZerg, mineralesJugadorUno);
        } else {
            VBox botonesBordeIzquierdoProtoss = new VBox();
            botonesBordeIzquierdoProtoss.getChildren().addAll(botonAgregarConstruccionProtoss, botonAgregarIndividuoProtoss);
            botonesBordeIzquierdoProtoss.setSpacing(50);
            VBox datosJugador1 = new VBox();
            datosJugador1.getChildren().addAll(nombreDelJugador1, razaJugador1, mineralesJugadorUno);
            datosJugador1.setSpacing(30);
            datosJugador1.setBackground(new Background(new BackgroundFill(Color.rgb(63, 84, 99, 0.6), new CornerRadii(20), Insets.EMPTY)));
            bordeIzquierdo.getChildren().addAll(botonesBordeIzquierdoProtoss, datosJugador1);
            //bordeIzquierdo.getChildren().addAll(botonesBordeIzquierdoProtoss, nombreDelJugador1, razaJugador1, mineralesJugadorUno);
            //bordeIzquierdo.getChildren().addAll(nombreDelJugador1, razaJugador1, botonAgregarIndividuoProtoss, botonAgregarConstruccionProtoss, mineralesJugadorUno);
        }
        bordeIzquierdo.setSpacing(40);
        //bordeIzquierdo.setPadding(new Insets(50, 0, 0, 5));
        bordeIzquierdo.setPadding(new Insets(190, 5, 0, 15));
        this.setAlignment(bordeIzquierdo, Pos.CENTER_RIGHT);
        this.setLeft(bordeIzquierdo);

        //BORDE Derecho
        VBox bordeDerecho = new VBox();

        
        if (eleccionRaza2 == "Zerg") {
            //bordeDerecho.getChildren().addAll(nombreDelJugador2, razaJugador2,  botonAgregarIndividuoZerg, botonAgregarConstruccionZerg, mineralesJugadorDos);
            VBox botonesBordeDerechoZerg = new VBox();
            botonesBordeDerechoZerg.getChildren().addAll(botonAgregarConstruccionZerg, botonAgregarIndividuoZerg);
            botonesBordeDerechoZerg.setSpacing(50);
            VBox datosJugador2 = new VBox();
            datosJugador2.getChildren().addAll(nombreDelJugador2, razaJugador2, mineralesJugadorDos);
            datosJugador2.setSpacing(30);
            datosJugador2.setBackground(new Background(new BackgroundFill(Color.rgb(63, 84, 99, 0.6), new CornerRadii(20), Insets.EMPTY)));
            bordeDerecho.getChildren().addAll(botonesBordeDerechoZerg, datosJugador2);
            //bordeDerecho.getChildren().addAll(botonesBordeDerechoZerg, nombreDelJugador2, razaJugador2, mineralesJugadorDos);
        } else {
            //bordeDerecho.getChildren().addAll(nombreDelJugador2, razaJugador2, botonAgregarIndividuoProtoss, botonAgregarConstruccionProtoss, mineralesJugadorDos);
            VBox botonesBordeDerechoProtoss = new VBox();
            botonesBordeDerechoProtoss.getChildren().addAll(botonAgregarConstruccionProtoss, botonAgregarIndividuoProtoss);
            botonesBordeDerechoProtoss.setSpacing(50);
            VBox datosJugador2 = new VBox();
            datosJugador2.getChildren().addAll(nombreDelJugador2, razaJugador2, mineralesJugadorDos);
            datosJugador2.setSpacing(30);
            datosJugador2.setBackground(new Background(new BackgroundFill(Color.rgb(63, 84, 99,0.6), new CornerRadii(20), Insets.EMPTY)));
            bordeDerecho.getChildren().addAll(botonesBordeDerechoProtoss, datosJugador2);
            //bordeDerecho.getChildren().addAll(botonesBordeDerechoProtoss, nombreDelJugador2, razaJugador2, mineralesJugadorDos);
        }
        bordeDerecho.setSpacing(40);
        bordeDerecho.setPadding(new Insets(190, 25, 0, 0));
        this.setAlignment(bordeDerecho, Pos.CENTER_LEFT);
        this.setRight(bordeDerecho);


        //CONSOLA INFERIOR
        // VBox contenedorConsola = new VBox();
        // contenedorConsola.setSpacing(10);
        // contenedorConsola.setPadding(new Insets(15));

        // this.setAlignment(contenedorConsola, Pos.TOP_LEFT);
        // this.setBottom(contenedorConsola);

        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        juegoVista = new Scene(this,screenSize.getWidth(), screenSize.getHeight(), Color.rgb(47, 52, 58));

        BotonPasarTurnoHandler pasarTurnoHandler = new BotonPasarTurnoHandler(this, calcularTurnoDelJugador());
        Button pasarTurno = new Button("Pasar\nturno");
        pasarTurno.setOnAction(pasarTurnoHandler);
        botonAgregarIndividuoZerg.setOnAction(agregarIndividuoZergHandler);
        botonAgregarIndividuoZerg.setTranslateX(20);
        botonAgregarIndividuoZerg.setTranslateY(-168);
        botonAgregarIndividuoZerg.setFont(fuente);
        botonAgregarIndividuoZerg.setStyle(botonNormal);
        botonAgregarIndividuoZerg.setOnMouseEntered(e -> botonAgregarIndividuoZerg.setStyle(botonAntesDeSerPresionado));
        botonAgregarIndividuoZerg.setOnMouseExited(e -> botonAgregarIndividuoZerg.setStyle(botonNormal));
    }

    private Jugador calcularTurnoDelJugador() {
        if(Integer.parseInt(turnoJugadorActual) % 2 == 0){
            return jugadorUno;
        }
        return jugadorDos;
    }

    private void acercaDe(){
        Stage ventanaAcercaDe = new Stage();
        ventanaAcercaDe.setResizable(false);
        ventanaAcercaDe.setTitle("Acerca De");
        ventanaAcercaDe.getIcons().add(this.logo);

        InputStream is = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
        Font fuente = Font.loadFont(is, 20);
        
        Button botonOK = new Button("OK");
        botonOK.setStyle(botonNormal);
        botonOK.setFont(fuente);
        botonOK.setOnMouseEntered(e -> botonOK.setStyle(botonAntesDeSerPresionado));
        botonOK.setOnMouseExited(e -> botonOK.setStyle(botonNormal));
        
        VBox menuSalir = new VBox(20);
        
        Text parrafoAcercaDe = new Text("Somos el grupo 4 de la materia\n\nALGORITMOS Y PROGRAMACION III FIUBA.\n"
        + "\nEste es nuestro proyecto para el Trabajo Practico N2\n"+ "\n"
        + "\nSi queres ver el codigo de este proyecto podes ir a:\n"+ "\n"
        + "\nhttps://github.com/Chuleeta/algo3_tp2\n");

        Button link = new Button("IR A REPOSITORIO");
        link.setStyle(botonNormal);
        link.setFont(fuente);
        link.setOnMouseEntered(e -> link.setStyle(botonAntesDeSerPresionado));
        link.setOnMouseExited(e -> link.setStyle(botonNormal));
        link.setOnAction(e -> {
            Runtime rt = Runtime.getRuntime();
            String url = "https://github.com/Chuleeta/algo3_tp2";
            try{
                rt.exec("rundll32 url.dll, FileProtocolHandler " + url);
            } catch(IOException r){
                ((Throwable) r).printStackTrace();
            }
        });
        
        parrafoAcercaDe.setFont(fuente);
        parrafoAcercaDe.setFill(Color.rgb(66,176,211));
        parrafoAcercaDe.setTextAlignment(TextAlignment.CENTER);
        
        menuSalir.getChildren().addAll(parrafoAcercaDe, link, botonOK);
        menuSalir.setAlignment(Pos.CENTER);
        menuSalir.setStyle("-fx-border-color: #131E28; -fx-background-color: #131E28");
        
        botonOK.setOnAction(e-> {
            ventanaAcercaDe.close();
        });

        Scene  escenaSalir = new Scene(menuSalir , 800 , 400);
		
        ventanaAcercaDe.setScene(escenaSalir);
        ventanaAcercaDe.showAndWait();
    }

    private void comoJugar(){
        InputStream is = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
        Font fuente = Font.loadFont(is, 20);
        
        Stage ventanaComoSalir = new Stage();
        ventanaComoSalir.getIcons().add(this.logo);
        ventanaComoSalir.setResizable(false);
        ventanaComoSalir.setTitle("Como se Juega");
        
        VBox menuSalir = new VBox(20);
        
        Text parrafoComoJugar = new Text("ACA VA COMO JUGAR");

        parrafoComoJugar.setFont(fuente);
        parrafoComoJugar.setFill(Color.rgb(66,176,211));
        parrafoComoJugar.setTextAlignment(TextAlignment.CENTER);
        
        Button botonOK = new Button("OK");
        botonOK.setStyle(botonNormal);
        botonOK.setFont(fuente);
        botonOK.setOnMouseEntered(e -> botonOK.setStyle(botonAntesDeSerPresionado));
        botonOK.setOnMouseExited(e -> botonOK.setStyle(botonNormal));
        
        menuSalir.getChildren().addAll(parrafoComoJugar , botonOK);
        menuSalir.setAlignment(Pos.CENTER);
        menuSalir.setStyle("-fx-border-color: #131E28; -fx-background-color: #131E28");
        
        botonOK.setOnAction(e-> {
            ventanaComoSalir.close();
        });
        
        Scene  escenaSalir = new Scene(menuSalir , 800 , 700);

        ventanaComoSalir.setScene(escenaSalir);
        ventanaComoSalir.showAndWait();
    }

    private String volverAlMenu(Stage stage){
        InputStream is = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
        Font fuente = Font.loadFont(is, 20);

        Stage ventanaVolver = new Stage();
        ventanaVolver.setResizable(false);
        ventanaVolver.setTitle("Volver al Menu");
        
        Button botonVolver = new Button("Volver al Menu Principal");
        botonVolver.setFont(fuente);
        botonVolver.setStyle(botonNormal);
        botonVolver.setOnMouseEntered(e -> botonVolver.setStyle(botonAntesDeSerPresionado));
        botonVolver.setOnMouseExited(e -> botonVolver.setStyle(botonNormal));
        
        Button botonQuedarse = new Button("Quedarse");
        botonQuedarse.setFont(fuente);
        botonQuedarse.setStyle(botonNormal);
        botonQuedarse.setOnMouseEntered(e -> botonQuedarse.setStyle(botonAntesDeSerPresionado));
        botonQuedarse.setOnMouseExited(e -> botonQuedarse.setStyle(botonNormal));

        VBox menuVolver = new VBox(20);
        
        Label preguntaVolver = new Label("Estas seguro que queres ir al Menu Principal?");
        preguntaVolver.setFont(fuente);
        preguntaVolver.setStyle(formatoTexto);
        
        Label advertenciaDatos = new Label("Todo tu progreso se perdera");
        advertenciaDatos.setFont(fuente);
        advertenciaDatos.setStyle(formatoTexto);
        
        ventanaVolver.setMinWidth(280);
        ventanaVolver.initModality(Modality.APPLICATION_MODAL);
        
        menuVolver.getChildren().addAll(preguntaVolver, advertenciaDatos, botonVolver, botonQuedarse);
        menuVolver.setAlignment(Pos.CENTER);
        menuVolver.setStyle("-fx-border-color: #131E28; -fx-background-color: #131E28");
        
        botonVolver.setOnAction(e-> {
            respuesta = "Volver";
            ventanaVolver.close();
        });
        
        botonQuedarse.setOnAction(e->{
            respuesta  = "Quedarse";
            ventanaVolver.close();
        });

        Scene  escenaVolver = new Scene(menuVolver , 680 , 300);
        ventanaVolver.getIcons().add(this.logo);
        ventanaVolver.setScene(escenaVolver);
        ventanaVolver.showAndWait();
        return(respuesta);
    }

    public void actualizarTablero() {
        //this.tablero.actualizarConstrucciones();
        ArrayList<Construccion> construccionesJ1 = this.jugadorUno.getConstrucciones();
        if(construccionesJ1.size() > 0){
            for (Construccion construccion : construccionesJ1) {
                tablero.insertarConstruccion(construccion);
            }
        }
        ArrayList<Construccion> construccionesJ2 = this.jugadorDos.getConstrucciones();
        if(construccionesJ2.size() != 0){
            for (Construccion construccion : construccionesJ2) {
                tablero.insertarConstruccion(construccion);
            }
        }
    }
}
