package edu.fiuba.algo3.javafx.Eventos;

import edu.fiuba.algo3.javafx.JuegoVista;
import edu.fiuba.algo3.modelo.Jugador;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

public class BotonIngresarPosicionHandler implements EventHandler<ActionEvent> {

    TextInputDialog td;
    Label l;

    public BotonIngresarPosicionHandler(Label label) {
        //this.stage = stage;
        //this.escenaParaDobleRepeticion = escenaParaDobleRepeticion;

        this.td = new TextInputDialog();
        this.l = label;
    }

    @Override
    public void handle(ActionEvent e){
        td.showAndWait();
        l.setText(td.getEditor().getText());
    }

    public String devolverValor(){
        return (td.getEditor().getText());
    }
}
