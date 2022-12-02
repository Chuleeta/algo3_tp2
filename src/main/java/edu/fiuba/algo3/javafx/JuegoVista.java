package edu.fiuba.algo3.javafx;

import java.io.IOException;
import java.io.InputStream;

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

public class JuegoVista extends BorderPane {
    static String respuesta;
    static Scene juegoVista;
    static ToolBar barra;
    //static Juego juego;
    static Group contenedorCentral;

    String botonAntesDeSerPresionado = "-fx-border-width: 2px; -fx-border-color: #B4DBE2; -fx-background-color: rgba(243, 202, 76, 0.5); -fx-text-fill: #BDB69C; -fx-shape: \"M 100 350 A 50 50 0 1 1 100 250 L 300 250 A 50 50 0 1 1 300 350 Z\";";
    String botonNormal = "-fx-border-width: 2px; -fx-border-color: #B4DBE2; -fx-background-color: rgba(255, 255, 255, 0.2); -fx-text-fill: #42B0D3; -fx-shape: \"M 100 350 A 50 50 0 1 1 100 250 L 300 250 A 50 50 0 1 1 300 350 Z\";";
    String formatoTexto = "-fx-border-width: 0px; -fx-border-color: #80CEB9; -fx-background-color: transparent; -fx-text-fill: #42B0D3";

	private Image logo;
	private Image fondo;
	private Image iconoControlPolicial;
	private Image iconoPozo;
	private Image iconoPiquete;
	private Image iconoSorpresa;
	private Image iconoBotonArriba;
	private Image iconoBotonAbajo;
	private Image iconoBotonIzquierda;
	private Image iconoBotonDerecha;
	private Image iconoNotaMusical;
	private Image iconoNotaMusicalTachada;

    public JuegoVista(Stage stage, Scene pantallaDeInicio, int ancho, int alto, String nombreJugador1, String eleccionRaza1, String nombreJugador2, String eleccionRaza2){
        this.cargarImagenes();
		this.setJuego(stage, pantallaDeInicio, ancho, alto, nombreJugador1, eleccionRaza1, nombreJugador2, eleccionRaza2);
        stage.setMaximized(true);
        BackgroundImage imagenDeFondo = new BackgroundImage(this.fondo, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        this.setBackground(new Background(imagenDeFondo));
    }

    public Scene getJuegoVista(){
        return juegoVista;
    }

	private void cargarImagenes() {
		String pathlogo = this.getClass().getResource("/imagenes/icono.png").toString();
        this.logo = new Image(pathlogo);
        
		String pathFondo = this.getClass().getResource("/imagenes/fondoDePantalla.jpg").toString();
		this.fondo = new Image(pathFondo);
		
		// String pathIconoBotonArriba = this.getClass().getResource("/imagenes/icono-boton-arriba.png").toString();
		// this.iconoBotonArriba = new Image(pathIconoBotonArriba);
		
		// String pathIconoBotonAbajo = this.getClass().getResource("/imagenes/icono-boton-abajo.png").toString();
		// this.iconoBotonAbajo = new Image(pathIconoBotonAbajo);
		
		// String pathIconoBotonIzquierda = this.getClass().getResource("/imagenes/icono-boton-izquierda.png").toString();
		// this.iconoBotonIzquierda = new Image(pathIconoBotonIzquierda);
		
		// String pathIconoBotonDerecha = this.getClass().getResource("/imagenes/icono-boton-derecha.png").toString();
		// this.iconoBotonDerecha = new Image(pathIconoBotonDerecha);
		
		// String pathNotaMusical = this.getClass().getResource("/imagenes/nota-musical.png").toString();
		// this.iconoNotaMusical = new Image(pathNotaMusical);
		
		// String pathNotaMusicalTachada = this.getClass().getResource("/imagenes/nota-musical-tachada.png").toString();
		// this.iconoNotaMusicalTachada = new Image(pathNotaMusicalTachada);

		// String pathIconoPiquete = this.getClass().getResource("/imagenes/icono-piquete.png").toString();
		// this.iconoPiquete = new Image(pathIconoPiquete);

		// String pathIconoPozo = this.getClass().getResource("/imagenes/icono-pozo.png").toString();
		// this.iconoPozo = new Image(pathIconoPozo);

		// String pathIconoControl = this.getClass().getResource("/imagenes/icono-control-policial.png").toString();
		// this.iconoControlPolicial = new Image(pathIconoControl);
		
		// String pathIconoSorpresa = this.getClass().getResource("/imagenes/icono-sorpresa.png").toString();
		// this.iconoSorpresa = new Image(pathIconoSorpresa);
	}

    private void setJuego(Stage stage, Scene pantallaDeInicio, int ancho, int alto, String nombreJugador1, String eleccionRaza1, String nombreJugador2, String eleccionRaza2){
        InputStream is = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
        Font fuente = Font.loadFont(is, 25);

        //NOMBRE DEL JUGADOR 1
        Label nombreDelJugador1 = new Label("Jugador 1: \n" + nombreJugador1);
        nombreDelJugador1.setFont(fuente);
        nombreDelJugador1.setStyle(formatoTexto);

        //NOMBRE DEL JUGADOR 2
        Label nombreDelJugador2 = new Label("Jugador 2: \n" + nombreJugador2);
        nombreDelJugador2.setFont(fuente);
        nombreDelJugador2.setStyle(formatoTexto);


		switch (eleccionRaza1) {
			case "Zerg":
				//juego = new Juego(nombreJugador, new Moto());
                break;
			case "Protoss":
				//juego = new Juego(nombreJugador, new Auto());
                break;
		}
        //juego.setDimensionesMapa(ancho, alto);

        //raza
        Label razaJugador1 = new Label("Raza: " + eleccionRaza1);
        razaJugador1.setFont(fuente);
        razaJugador1.setStyle(formatoTexto);

        //raza
        Label razaJugador2 = new Label("Raza: " + eleccionRaza2);
        razaJugador2.setFont(fuente);
        razaJugador2.setStyle(formatoTexto);


        //Tablero grilla = new Tablero(alto,ancho, juego);
        
        //this.setCenter(grilla.getContenedor());

        Canvas canvasCentral = new Canvas(900, 650);
        //JugadorVista jugadorVista = new JugadorVista(juego, canvasCentral, grilla, stage, pantallaDeInicio, razaJugador1);


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
				//jugadorVista.salirDelJuego();
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
        
        //Boton DERECHA
        // Button moverseDerecha = new Button("");
        // moverseDerecha.setFont(fuente);
        // moverseDerecha.setGraphic(new ImageView(this.iconoBotonDerecha));
        // moverseDerecha.setStyle(formatoTexto);
        // moverseDerecha.setOnMouseEntered(e -> moverseDerecha.setStyle("-fx-background-color: #717D8C; -fx-text-fill: #BDB69C"));
        // moverseDerecha.setOnMouseExited(e -> moverseDerecha.setStyle(formatoTexto));
        
        // Button moverseAbajo = new Button("");
        // moverseAbajo.setFont(fuente);
        // moverseAbajo.setGraphic(new ImageView(this.iconoBotonAbajo));
        // moverseAbajo.setStyle(formatoTexto);
        // moverseAbajo.setOnMouseEntered(e -> moverseAbajo.setStyle("-fx-background-color: #717D8C; -fx-text-fill: #BDB69C"));
        // moverseAbajo.setOnMouseExited(e -> moverseAbajo.setStyle(formatoTexto));
        
        // Button moverseIzquierda = new Button("");
        // moverseIzquierda.setFont(fuente);
        // moverseIzquierda.setGraphic(new ImageView(this.iconoBotonIzquierda));
        // moverseIzquierda.setStyle(formatoTexto);
        // moverseIzquierda.setOnMouseEntered(e -> moverseIzquierda.setStyle("-fx-background-color: #717D8C; -fx-text-fill: #BDB69C"));
        // moverseIzquierda.setOnMouseExited(e -> moverseIzquierda.setStyle(formatoTexto));
        
        // Button moverseArriba = new Button("");
        // moverseArriba.setFont(fuente);
        // moverseArriba.setGraphic(new ImageView(this.iconoBotonArriba));
        // moverseArriba.setStyle(formatoTexto);
        // moverseArriba.setOnMouseEntered(e -> moverseArriba.setStyle("-fx-background-color: #717D8C; -fx-text-fill: #BDB69C"));
        // moverseArriba.setOnMouseExited(e -> moverseArriba.setStyle(formatoTexto));
        
        // VBox botonesMovimiento = new VBox();
        HBox ContenedorBotonesSonidos = new HBox();

        //StackPane stackSonidos = new StackPane();
        // BotonMusica apagarMusica = new BotonMusica("",jugadorVista);
        // apagarMusica.setOnAction(((BotonMusica) apagarMusica).silenciar());
        // apagarMusica.setStyle(botonNormal);
        // apagarMusica.setOnMouseEntered(e -> apagarMusica.setStyle(botonAntesDeSerPresionado));
        // apagarMusica.setOnMouseExited(e -> apagarMusica.setStyle(botonNormal));
        // apagarMusica.setGraphic(new ImageView(this.iconoNotaMusical));
        // apagarMusica.setPrefSize(60, 60);
        //stackSonidos.setAlignment(Pos.TOP_CENTER);

        // BotonMusica encenderMusica = new BotonMusica("",jugadorVista);
        // encenderMusica.setStyle(botonNormal);
        // encenderMusica.setOnMouseEntered(e -> encenderMusica.setStyle(botonAntesDeSerPresionado));
        // encenderMusica.setOnMouseExited(e -> encenderMusica.setStyle(botonNormal));
        // encenderMusica.setGraphic(new ImageView(this.iconoNotaMusicalTachada));
        // stackSonidos.getChildren().add(encenderMusica);
        // stackSonidos.getChildren().add(apagarMusica);

        // apagarMusica.setOnAction(actionEvent -> {
        //     EventHandler<ActionEvent> evento = apagarMusica.silenciar();
        //     evento.handle(actionEvent);
        //     stackSonidos.getChildren().remove(apagarMusica);
        // });

        // encenderMusica.setOnAction(actionEvent -> {
        //     EventHandler<ActionEvent> evento = encenderMusica.reproducir();
        //     evento.handle(actionEvent);
        //     stackSonidos.getChildren().add(apagarMusica);
        // });

        // stackSonidos.setAlignment(Pos.TOP_RIGHT);
        // stackSonidos.setAlignment(Pos.TOP_CENTER);
        // this.setTop(stackSonidos);

        // MoverseALaDerechaEventHandler moverseDerechaHandler = new MoverseALaDerechaEventHandler(jugadorVista);
        // moverseDerecha.setOnAction(moverseDerechaHandler);
        
        // MoverseALaIzquierdaEventHandler moverseIzquierdaHandler = new MoverseALaIzquierdaEventHandler(jugadorVista);
        // moverseIzquierda.setOnAction(moverseIzquierdaHandler);
        
        // MoverseAbajoEventHandler moverseAbajoHandler = new MoverseAbajoEventHandler(jugadorVista);
        // moverseAbajo.setOnAction(moverseAbajoHandler);
        
        // MoverseArribaEventHandler moverseArribaHandler = new MoverseArribaEventHandler(jugadorVista);
        // moverseArriba.setOnAction(moverseArribaHandler);
        
        
        // HBox botonesID = new HBox();
        // botonesID.getChildren().add(moverseIzquierda);
        // botonesID.getChildren().add(moverseDerecha);
        // botonesID.setAlignment(Pos.CENTER);
        // botonesID.setSpacing(5);
        
        // moverseArriba.setAlignment(Pos.CENTER);
        // botonesMovimiento.getChildren().add(moverseArriba);
        // botonesMovimiento.getChildren().add(botonesID);
        // moverseAbajo.setAlignment(Pos.CENTER);
        // botonesMovimiento.getChildren().add(moverseAbajo);
        // botonesMovimiento.setAlignment(Pos.CENTER);

        //BORDE IZQUIERDO
        VBox bordeIzquierdo = new VBox();
        bordeIzquierdo.getChildren().addAll(nombreDelJugador1, razaJugador1);
        bordeIzquierdo.setSpacing(70);
        bordeIzquierdo.setPadding(new Insets(50, 0, 50, 80));
        this.setAlignment(bordeIzquierdo, Pos.CENTER_LEFT);
        this.setLeft(bordeIzquierdo);

        //BORDE Derecho
        VBox bordeDerecho = new VBox();
        bordeDerecho.getChildren().addAll(nombreDelJugador2, razaJugador2);
        bordeDerecho.setSpacing(70);
        bordeDerecho.setPadding(new Insets(50, 80, 50, 0));
        this.setAlignment(bordeDerecho, Pos.CENTER_RIGHT);
        this.setRight(bordeDerecho);


        // Label controlPolical = new Label(":  Policial");
        // controlPolical.setFont(fuente);
        // controlPolical.setStyle("-fx-border-width: 0px; -fx-border-color: transparent; -fx-background-color: transparent; -fx-text-fill: #BDB69C");
        // // controlPolical.setGraphic(new ImageView(this.iconoControlPolicial));

        // Label pozo = new Label(": Pozo");
        // pozo.setFont(fuente);
        // pozo.setStyle("-fx-border-width: 0px; -fx-border-color: transparent; -fx-background-color: transparent; -fx-text-fill: #BDB69C");
        // // pozo.setGraphic(new ImageView(this.iconoPozo));

        // Label piquete = new Label(": Piquete");
        // piquete.setFont(fuente);
        // piquete.setStyle("-fx-border-width: 0px; -fx-border-color: transparent; -fx-background-color: transparent; -fx-text-fill: #BDB69C");
        // // piquete.setGraphic(new ImageView(this.iconoPiquete));

        // Label sorpresaFavorable = new Label(": Sorpresa");
        // sorpresaFavorable.setFont(fuente);
        // sorpresaFavorable.setStyle("-fx-border-width: 0px; -fx-border-color: transparent; -fx-background-color: transparent; -fx-text-fill: #BDB69C");
        // // sorpresaFavorable.setGraphic(new ImageView(this.iconoSorpresa));

        // VBox bordeDerecho = new VBox();
        // bordeDerecho.getChildren().addAll(controlPolical, pozo, piquete, sorpresaFavorable);
        // bordeDerecho.setSpacing(100);
        // bordeDerecho.setPadding(new Insets(10, 0, 0, 30));
        // this.setAlignment(bordeDerecho, Pos.CENTER_RIGHT);
        // this.setRight(bordeDerecho);


        //CONSOLA INFERIOR
        VBox contenedorConsola = new VBox();
        contenedorConsola.setSpacing(10);
        contenedorConsola.setPadding(new Insets(15));
        //contenedorConsola.setStyle("-fx-background-color: black;");
        //contenedorConsola.getChildren().add(stackSonidos);

        this.setAlignment(contenedorConsola, Pos.TOP_LEFT);
        this.setBottom(contenedorConsola);

        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        juegoVista = new Scene(this,screenSize.getWidth(), screenSize.getHeight(), Color.rgb(47, 52, 58));
        //juegoVista.setOnKeyPressed(new Controles(stage,jugadorVista));
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
}
