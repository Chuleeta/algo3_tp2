package edu.fiuba.algo3.javafx.Eventos;

import edu.fiuba.algo3.javafx.JuegoVista;
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
    private final JugadorZerg jugador;

    public BotonAgregarIndividuoProtossHandler(JuegoVista juegoVista, JugadorZerg jugador) {
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
        alert.setTitle("Construcciones protos");
        alert.setHeaderText("Seleccione la construccion que desea construir");

        ButtonType botonCriadero = new ButtonType("Criadero");


        alert.getButtonTypes().setAll(botonCriadero);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == botonCriadero){
            //
        } /*else if (result.get() == botonMoverIzquierdaDoble) {
            BloqueIzquierda bloque = new BloqueIzquierda();
            tablero.agregarBloqueParaDobleRepeticion(bloque);
        } else if (result.get() == botonMoverDerechaDoble) {
            BloqueDerecha bloque = new BloqueDerecha();
            tablero.agregarBloqueParaDobleRepeticion(bloque);
        } else if (result.get() == botonMoverAbajoDoble) {
            BloqueAbajo bloque = new BloqueAbajo();
            tablero.agregarBloqueParaDobleRepeticion(bloque);
        } else {
            // ... user chose CANCEL or closed the dialog
        }
        */
    }
}
