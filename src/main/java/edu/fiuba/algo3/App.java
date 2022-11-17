package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Exceptions.AtributoInvalidoException;
import edu.fiuba.algo3.modelo.Jugador;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Scanner;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws AtributoInvalidoException {
        // var javaVersion = SystemInfo.javaVersion();
        // var javafxVersion = SystemInfo.javafxVersion();

        // var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        // var scene = new Scene(new StackPane(label), 640, 480);
        // stage.setScene(scene);
        // stage.show();

        // System.out.println("Jugador uno: Ingrese su nombre");
        // Scanner entrada = new Scanner(System.in);
        // String nombreUno = entrada.next();
        // System.out.println("Jugador uno: Ingrese su color");
        // String colorUno = entrada.next();
        // System.out.println("Jugador uno: Ingrese su raza");
        // String razaUno = entrada.next();
        // Jugador jugadorUno = new Jugador(nombreUno, colorUno, razaUno);
        // jugadorUno.validarAtributos(nombreUno);
        // System.out.println("Jugador dos: Ingrese su nombre");
        // String nombreDos = entrada.next();
        // System.out.println("Jugador dos: Ingrese su color");
        // String colorDos = entrada.next();
        // System.out.println("Jugador dos: Ingrese su raza");
        // String razaDos = entrada.next();
        // Jugador jugadorDos = new Jugador(nombreDos, colorDos, razaDos);
        // jugadorDos.validarAtributos(nombreDos, colorDos, razaDos, jugadorUno);

    }

    public static void main(String[] args) {
        launch();
    }

}