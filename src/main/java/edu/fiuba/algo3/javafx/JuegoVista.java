package edu.fiuba.algo3.javafx;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import edu.fiuba.algo3.javafx.Eventos.*;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Individuos.Individuo;
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

    private Label nombreDelJugador;
    private Image datosJugador;
    private String turnoJugadorActual;
    
    private Jugador jugadorUno;
    private Jugador jugadorDos;
    private Juego juego;
    private Tablero tablero;
    private int pisicionSeleccionadaX;
    private int pisicionSeleccionadaY;
    private Label posicionSeleccionada;
    private PasarTurno pasarTurno;
    //public Stage stage;
    
    public JuegoVista(Stage stage, Scene pantallaDeInicio, int ancho, int alto, Juego juego,String nombreJugador1, String eleccionRaza1, String nombreJugador2, String eleccionRaza2) throws RequerimientosInsuficientesException{
        this.pisicionSeleccionadaX = 1;
        this.pisicionSeleccionadaY = 1;
        this.jugadorUno = juego.getJugadorUno();
        this.jugadorDos = juego.getJugadorDos();
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

    public void  setPosicionSeleccionada(Posicion posicion){
       this.pisicionSeleccionadaX = posicion.coordenadaX();
       this.pisicionSeleccionadaY = posicion.coordenadaY();
       this.posicionSeleccionada.setText(String.format("Posicion Seleccionada - En X: " + this.pisicionSeleccionadaX + " | En Y: " + this.pisicionSeleccionadaY));
    }

    private void setJuego(Stage stage, Scene pantallaDeInicio, int ancho, int alto, String nombreJugador1, String eleccionRaza1, String nombreJugador2, String eleccionRaza2) throws RequerimientosInsuficientesException{
        InputStream is = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
        Font fuente = Font.loadFont(is, 25);

        //JUGADOR Jugadndo
        this.turnoJugadorActual = "1";
        this.nombreDelJugador = new Label("Turno Jugador : " + turnoJugadorActual);
        nombreDelJugador.setFont(fuente);
        nombreDelJugador.setStyle(formatoTexto);
        this.posicionSeleccionada = new Label("Posicion Seleccionada - En X: " + this.pisicionSeleccionadaX + " | En Y: " + this.pisicionSeleccionadaY);
        this.posicionSeleccionada.setFont(fuente);
        this.posicionSeleccionada.setStyle(formatoTexto);

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
        /*if (eleccionRaza1 == "Zerg") {
            jugadorUno = jugadorUno.definirZerg();
            jugadorDos = jugadorDos.definirProtoss();
            jugadorUno = new JugadorZerg(nombreJugador1, "rojo", eleccionRaza1, new Posicion(0, 0), mapa, 0);
            jugadorDos = new JugadorProtoss(nombreJugador2, "azul", eleccionRaza2, new Posicion(ancho, alto), mapa, 0);
        } else {
            jugadorUno = new JugadorProtoss(nombreJugador1, "rojo", eleccionRaza1, new Posicion(0, 0), mapa, 0);
            jugadorDos = new JugadorZerg(nombreJugador2, "azul", eleccionRaza2, new Posicion(ancho, alto), mapa, 0);
        }*/

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
        PasarTurno pasarturno = new PasarTurno("Pasar turno", jugadorUno, jugadorDos);
        this.pasarTurno = pasarturno;
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

        // creacion de botones y Labels

        BotonGenerico botonAgregarIndividuoZerg = new BotonGenerico("Agregar\nindividuo", agregarIndividuoZergHandler, jugadorUno);
        BotonGenerico botonAgregarIndividuoProtoss = new BotonGenerico("Agregar\nindividuo", agregarIndividuoProtossHandler, jugadorUno);
        BotonGenerico botonAgregarConstruccionZerg = new BotonGenerico("Agregar\nconstruccion", agregarConstruccionZergHandler, jugadorUno);
        BotonGenerico botonAgregarConstruccionProtoss = new BotonGenerico("Agregar\nconstruccion", agregarConstruccionProtossHandler, jugadorUno);
        LabelMinerales mineralesJugadorUno = new LabelMinerales(jugadorUno);
        LabelMinerales mineralesJugadorDos = new LabelMinerales(jugadorDos);

        pasarturno.añadirSuscriptor(botonAgregarConstruccionZerg);
        pasarturno.añadirSuscriptor(botonAgregarConstruccionProtoss);
        pasarturno.añadirSuscriptor(botonAgregarIndividuoProtoss);
        pasarturno.añadirSuscriptor(botonAgregarIndividuoZerg);
        pasarturno.añadirSuscriptor(mineralesJugadorDos);
        pasarTurno.añadirSuscriptor(mineralesJugadorUno);

        VBox turnoActual = new VBox();

        turnoActual.getChildren().addAll(nombreDelJugador, pasarturno, this.posicionSeleccionada);
        // turnoActual.getChildren().addAll(pasarTurno);
        turnoActual.setAlignment(Pos.CENTER);
        turnoActual.setBackground(new Background(new BackgroundFill(Color.rgb(63, 84, 99, 0.7), new CornerRadii(20), Insets.EMPTY)));


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
        VBox bordeDerecho = new VBox();
        VBox botonesBordeIzquierdo = new VBox();
        VBox botonesBordeDerecho = new VBox();
        VBox datosJugador2 = new VBox();
        VBox datosJugador1 = new VBox();
        datosJugador1.getChildren().addAll(nombreDelJugador1, razaJugador1, mineralesJugadorUno);
        datosJugador1.setSpacing(30);
        datosJugador1.setBackground(new Background(new BackgroundFill(Color.rgb(63, 84, 99, 0.6), new CornerRadii(20), Insets.EMPTY)));
        datosJugador2.getChildren().addAll(nombreDelJugador2, razaJugador2, mineralesJugadorDos);
        datosJugador2.setSpacing(30);
        datosJugador2.setBackground(new Background(new BackgroundFill(Color.rgb(63, 84, 99,0.6), new CornerRadii(20), Insets.EMPTY)));

        // VBox bordeIzquierdo = new VBox().prefWidthProperty().bind(stage.widthProperty().multiply(0.80));
        if (eleccionRaza1 == "Zerg") {
            botonAgregarConstruccionZerg.setearJugador(jugadorUno);
            botonAgregarIndividuoZerg.setearJugador(jugadorUno);
            botonAgregarIndividuoProtoss.setearJugador(jugadorDos);
            botonAgregarConstruccionProtoss.setearJugador(jugadorDos);
            botonesBordeIzquierdo.getChildren().addAll(botonAgregarConstruccionZerg, botonAgregarIndividuoZerg);
            botonesBordeIzquierdo.setSpacing(50);
            bordeIzquierdo.getChildren().addAll(botonesBordeIzquierdo, datosJugador1);
            botonesBordeDerecho.getChildren().addAll(botonAgregarConstruccionProtoss, botonAgregarIndividuoProtoss);
            botonesBordeDerecho.setSpacing(50);
            bordeDerecho.getChildren().addAll(botonesBordeDerecho, datosJugador2);
        } else {
            botonAgregarIndividuoProtoss.setearJugador(jugadorUno);
            botonAgregarConstruccionProtoss.setearJugador(jugadorUno);
            botonAgregarConstruccionZerg.setearJugador(jugadorDos);
            botonAgregarIndividuoZerg.setearJugador(jugadorDos);
            botonesBordeIzquierdo.getChildren().addAll(botonAgregarConstruccionProtoss, botonAgregarIndividuoProtoss);
            botonesBordeIzquierdo.setSpacing(50);
            bordeIzquierdo.getChildren().addAll(botonesBordeIzquierdo, datosJugador1);
            botonesBordeDerecho.getChildren().addAll(botonAgregarConstruccionZerg, botonAgregarIndividuoZerg);
            botonesBordeDerecho.setSpacing(50);
            bordeDerecho.getChildren().addAll(botonesBordeDerecho, datosJugador2);
        }
        bordeIzquierdo.setSpacing(40);
        //bordeIzquierdo.setPadding(new Insets(50, 0, 0, 5));
        bordeIzquierdo.setPadding(new Insets(190, 5, 0, 15));
        this.setAlignment(bordeIzquierdo, Pos.CENTER_RIGHT);
        this.setLeft(bordeIzquierdo);
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

    }

    private Jugador calcularTurnoDelJugador() {
        if(Integer.parseInt(turnoJugadorActual) % 2 == 0){
            return jugadorDos;
        }
        return jugadorUno;
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
      /*
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
        ArrayList<Individuo> individuos = this.juego.mostrarUnidades();
        if(individuos.size() != 0){
            for (Individuo individuo : individuos) {
                tablero.insertarUnidad(individuo);
            }
        }
        */

        tablero.actualizar();
    }

    public void agregarSuscriptorPasarTurno(Notificable notificable) {
        this.pasarTurno.añadirSuscriptor(notificable);
    }
}
