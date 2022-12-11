package edu.fiuba.algo3.javafx.Eventos;

import edu.fiuba.algo3.javafx.JuegoVista;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.JugadorProtoss;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.util.Optional;

public class BotonAgregarConstruccionProtossHandler  implements EventHandler<ActionEvent> {

    JuegoVista juegoVista;
        private final Jugador jugador;

        public BotonAgregarConstruccionProtossHandler(JuegoVista juegoVista, Jugador jugador) {
            //this.stage = stage;
            //this.escenaParaDobleRepeticion = escenaParaDobleRepeticion;

            this.juegoVista = juegoVista;
            this.jugador = jugador;
        }
    @Override
    public void handle(ActionEvent actionEvent) {
        Label etiqueta = new Label();
        etiqueta.setText("Agregar Construccion Protoss");
        // vista.agregarElementosAEjecutar(etiqueta);
        // tablero.agregarBloqueDobleRepeticion();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Construcciones protoss");
        alert.setHeaderText("Seleccione la construccion que desea construir");

        ButtonType botonNexo = new ButtonType("Nexo Mineral");
        ButtonType botonPilon = new ButtonType("Pilon");
        ButtonType botonAsimilador = new ButtonType("Asimilador");
        ButtonType botonAcceso = new ButtonType("Acceso");
        ButtonType botonPuerto = new ButtonType("Puerto Estelar");


        alert.getButtonTypes().setAll(botonNexo, botonPilon, botonAsimilador, botonAcceso, botonPuerto);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == botonNexo){
            // NexoMineral nexo = new NexoMineral();
        }else if (result.get() == botonPilon) {
            //Pilon pilon = new Pilon();
        } else if (result.get() == botonAsimilador) {
            // Asimilador asimilador = new Asimilador();
        } else if (result.get() == botonAcceso) {
            // Acceso acceso = new Acceso();
        }  else if (result.get() == botonPuerto) {
            // PuertoEstelar puerto = new PuertoEstelar();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }
}
