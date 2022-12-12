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

public class BotonAgregarIndividuoProtossHandler  implements EventHandler<ActionEvent> {

    JuegoVista juegoVista;
    private final Jugador jugador;

    public BotonAgregarIndividuoProtossHandler(JuegoVista juegoVista, Jugador jugador) {
        //this.stage = stage;
        //this.escenaParaDobleRepeticion = escenaParaDobleRepeticion;

        this.juegoVista = juegoVista;
        this.jugador = jugador;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        Label etiqueta = new Label();
        etiqueta.setText("Agregar Individuo Protoss");
        // vista.agregarElementosAEjecutar(etiqueta);
        // tablero.agregarBloqueDobleRepeticion();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Unidades protoss");
        alert.setHeaderText("Seleccione la unidad que desea enjendrar");

        ButtonType botonZealot = new ButtonType("Zealot");
        ButtonType botonScout = new ButtonType("Scout");
        ButtonType botonDragon = new ButtonType("Dragon");


        alert.getButtonTypes().setAll(botonZealot, botonDragon, botonScout);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == botonZealot){
            // Zealot zealot = new Zealot();
        }else if (result.get() == botonDragon) {
            //Dragon dragon = new Dragon();
        } else if (result.get() == botonScout) {
            // Scout scout = new Scout();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }
}
