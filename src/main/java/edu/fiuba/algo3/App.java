package edu.fiuba.algo3;

import java.io.InputStream;

import edu.fiuba.algo3.javafx.MenuPrincipal;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


/*
 * JavaFX App
 */
public class App extends Application {
    static String respuesta;
    static String a;
    Boolean confirmacionDatos;
    Scene pantallaDeInicio;
    Scene preguntaDatos;
    Scene mapaDelJuego;
    Scene tablaDePosicionesHistorial;
	
	//private Image logoGpsChallenge;
	String botonAntesDeSerPresionado = "-fx-border-width: 0px; -fx-border-color: #80CEB9; -fx-background-color: rgba(243, 202, 76, 0.5); -fx-text-fill: #BDB69C";
    String botonNormal = "-fx-border-width: 0px; -fx-border-color: #80CEB9; -fx-background-color: rgba(255, 255, 255, 0.2); -fx-text-fill: #42B0D3";
    String formatoTexto = "-fx-border-width: 0px; -fx-border-color: #80CEB9; -fx-background-color: transparent; -fx-text-fill: #42B0D3";

    @Override
    public void start(Stage stage) {
        stage.setTitle("AlgoStar");
        stage.setResizable(true);

		// String pathLogo = this.getClass().getResource("/imagenes/logo-gps-challenge.png").toString();
		// this.logoGpsChallenge = new Image(pathLogo);
        // stage.getIcons().add(this.logoGpsChallenge);

        MenuPrincipal menuPrincipal = new MenuPrincipal(stage);

        stage.setOnCloseRequest(e-> {
            e.consume();
            a = cerrarJuego(stage);
            if(a =="Salir"){
                stage.close();
            }
        });

        stage.setScene(menuPrincipal.getMenu());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private String cerrarJuego(Stage stage){

        InputStream is = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
        Font fuente = Font.loadFont(is, 20);

        Stage ventanaSalir = new Stage();
        ventanaSalir.setResizable(false);
        ventanaSalir.setTitle("Salir del Juego");
        ventanaSalir.getIcons().add(new Image(this.getClass().getResource("/imagenes/icono.png").toString()));
        
        Button botonSalir = new Button("SI");
        botonSalir.setStyle(botonNormal);
        botonSalir.setFont(fuente);
        botonSalir.setOnMouseEntered(e -> botonSalir.setStyle(botonAntesDeSerPresionado));
        botonSalir.setOnMouseExited(e -> botonSalir.setStyle(botonNormal));
        
        Button botonQuedarse = new Button("NO");
        botonQuedarse.setStyle(botonNormal);
        botonQuedarse.setFont(fuente);
        botonQuedarse.setOnMouseEntered(e -> botonQuedarse.setStyle(botonAntesDeSerPresionado));
        botonQuedarse.setOnMouseExited(e -> botonQuedarse.setStyle(botonNormal));
        
        VBox menuSalir = new VBox(20);
        
        Label preguntaSalir = new Label("Estas seguro que queres salir ?");
        preguntaSalir.setStyle(formatoTexto);
        preguntaSalir.setFont(fuente);
        
        menuSalir.getChildren().addAll(preguntaSalir , botonSalir ,botonQuedarse);
        menuSalir.setAlignment(Pos.CENTER);
        menuSalir.setStyle("-fx-border-color: #131E28; -fx-background-color: #131E28");
        
        botonSalir.setOnAction(e-> {
            respuesta = "Salir";
            ventanaSalir.close();
        });
        
        botonQuedarse.setOnAction(e->{
            respuesta  = "Quedarse";
            ventanaSalir.close();
        });

        Scene escenaSalir = new Scene(menuSalir , 530 , 200);
        
        //ventanaSalir.getIcons().add(this.logoGpsChallenge);
        ventanaSalir.setScene(escenaSalir);
        ventanaSalir.showAndWait();
        return(respuesta);
    }
}