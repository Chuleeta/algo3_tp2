package edu.fiuba.algo3.javafx;

import edu.fiuba.algo3.modelo.Construccion;
import edu.fiuba.algo3.modelo.Recursos.RecursoInyectable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UnidadRecurso extends Rectangle {
    private RecursoInyectable recurso;
    public UnidadRecurso(RecursoInyectable recurso, int coordenadaX, int coordenadaY) {
        super(40, 40, Color.GREY);
            this.setFill(Color.BROWN);
            String pathLogo = this.getClass().getResource(recurso.getSpray()).toString();
		    Image logo = new Image(pathLogo);
            this.setFill(new ImagePattern(logo));
        
        this.recurso = recurso;
        this.setTranslateX(coordenadaX);
        this.setTranslateY(coordenadaY);
    }
}
