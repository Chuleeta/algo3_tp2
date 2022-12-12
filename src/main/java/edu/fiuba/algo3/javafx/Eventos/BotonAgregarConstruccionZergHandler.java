package edu.fiuba.algo3.javafx.Eventos;

import edu.fiuba.algo3.javafx.JuegoVista;
import edu.fiuba.algo3.modelo.Edificios.ReservaDeReproduccion;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.JugadorProtoss;
import edu.fiuba.algo3.modelo.JugadorZerg;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.util.Optional;

public class BotonAgregarConstruccionZergHandler  implements EventHandler<ActionEvent> {

    JuegoVista juegoVista;
    private final Jugador jugador;

    public BotonAgregarConstruccionZergHandler(JuegoVista juegoVista, Jugador jugador) {
        //this.stage = stage;
        //this.escenaParaDobleRepeticion = escenaParaDobleRepeticion;

        this.juegoVista = juegoVista;
        this.jugador = jugador;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        Label etiqueta = new Label();
        etiqueta.setText("Agregar Construccion Zerg");
        // vista.agregarElementosAEjecutar(etiqueta);
        // tablero.agregarBloqueDobleRepeticion();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Construcciones zerg");
        alert.setHeaderText("Seleccione la construccion que desea construir");

        ButtonType botonCriadero = new ButtonType("Criadero");
        ButtonType botonReserva = new ButtonType("Reserva de\nReproduccion");
        ButtonType botonExtractor = new ButtonType("Extractor");
        ButtonType botonGuarida = new ButtonType("Guarida");
        ButtonType botonEspiral = new ButtonType("Espiral");


        alert.getButtonTypes().setAll(botonCriadero, botonReserva, botonExtractor, botonGuarida, botonEspiral);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == botonCriadero){
            // Criadero criadero = new Criadero();
        }else if (result.get() == botonReserva) {
            //ReservaDeReproduccion reserva = new ReservaDeReproduccion();
        } else if (result.get() == botonExtractor) {
            // Extractor extractor = new Extractor();
        } else if (result.get() == botonGuarida) {
            // Guarida guarida = new Guarida();
        }  else if (result.get() == botonEspiral) {
            // Espiral espiral = new Espiral();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }
}