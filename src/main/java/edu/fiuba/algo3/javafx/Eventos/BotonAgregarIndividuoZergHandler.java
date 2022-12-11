package edu.fiuba.algo3.javafx.Eventos;

import edu.fiuba.algo3.javafx.JuegoVista;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.JugadorProtoss;
import edu.fiuba.algo3.modelo.JugadorZerg;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.util.Optional;

public class BotonAgregarIndividuoZergHandler  implements EventHandler<ActionEvent> {

    JuegoVista juegoVista;
    private final Jugador jugador;

    public BotonAgregarIndividuoZergHandler(JuegoVista juegoVista, Jugador jugador) {
        //this.stage = stage;
        //this.escenaParaDobleRepeticion = escenaParaDobleRepeticion;

        this.juegoVista = juegoVista;
        this.jugador = jugador;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        Label etiqueta = new Label();
        etiqueta.setText("Agregar Unidad Zerg");
        // vista.agregarElementosAEjecutar(etiqueta);
        // tablero.agregarBloqueDobleRepeticion();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Unidades zerg");
        alert.setHeaderText("Seleccione la unidad que desea enjendrar");

        ButtonType botonAmo = new ButtonType("Amo Supremo");
        ButtonType botonZangano = new ButtonType("Zangano");
        ButtonType botonZerling = new ButtonType("Zerling");
        ButtonType botonHidralisco = new ButtonType("Hidralisco");
        ButtonType botonMutalisco = new ButtonType("Mutalisco");
        //ButtonType botonGuardian = new ButtonType("Guardian");
        //ButtonType botonDevorador = new ButtonType("Devorador");


        alert.getButtonTypes().setAll(botonAmo, botonZangano, botonZerling, botonHidralisco, botonMutalisco/*botonGuardian, botonDevorador*/);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == botonAmo){
            // AmoSupremo amo = new AmoSupremo;
        }else if (result.get() == botonZangano) {
            //Zangano zangano = new Zangano();
        } else if (result.get() == botonZerling) {
            // Zerling zerling = new Zerling();
        } else if (result.get() == botonHidralisco) {
            // Hidralisco hidralisco = new Hidralisco();
        }  else if (result.get() == botonMutalisco) {
            // Mutalisco mutalisco = new Mutalisco();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }
}