package edu.fiuba.algo3.javafx;
import edu.fiuba.algo3.modelo.Construccion;
import edu.fiuba.algo3.modelo.Jugador;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UnidadEdificio extends Rectangle implements Notificable {
    private final BotonModal botonVida;
    private Construccion construccion;
    public UnidadEdificio(Construccion construccion, int coordenadaX, int coordenadaY) {
        super(20, 20, Color.GREEN);
        this.construccion = construccion;
        this.setTranslateX(coordenadaX);
        this.setTranslateY(coordenadaY);
        this.botonVida = new BotonModal(this.construccion.obtenerVida().vidaRestante());
        this.setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.PRIMARY)){
                if(e.getClickCount() == 2){
                    VBox eleccionUsuario = new VBox();
                    HBox opciones = new HBox();
                    VBox opciones1 = new VBox();
                    Label seleccionarAccion = new Label("Propiedades\n");
                    String pathicono = this.getClass().getResource("/imagenes/icono.png").toString();
                    Image icono = new Image(pathicono);
                    botonVida.setText(construccion.obtenerVida().vidaRestante());
                    opciones1.getChildren().addAll(this.botonVida);
                    opciones1.setSpacing(10);
                    opciones1.setAlignment(Pos.CENTER);
                    opciones.getChildren().addAll(opciones1);
                    opciones.setSpacing(20);
                    opciones.setAlignment(Pos.CENTER);
                    eleccionUsuario.getChildren().addAll(seleccionarAccion, opciones);
                    eleccionUsuario.setSpacing(30);
                    eleccionUsuario.setAlignment(Pos.CENTER);
                    eleccionUsuario.setBackground(new Background(new BackgroundFill(Color.rgb(47, 52, 58), new CornerRadii(0), Insets.EMPTY)));
                    Scene sc = new Scene(eleccionUsuario, 800, 300, Color.rgb(47, 52, 58));
                    sc.setFill(Color.RED);
                    Stage s = new Stage();
                    s.initModality(Modality.APPLICATION_MODAL);
                    s.setTitle("Propuedades de la unidad");
                    s.getIcons().add(icono);
                    s.setResizable(false);
                    s.setScene(sc);
                    //s.show();
                    s.showAndWait();
                }
            }
        });
    }
    @Override
    public void actualizar(Jugador jugadorDeTurno) {
        this.setDisable(jugadorDeTurno != this.construccion.mostrarJugador());
    }
}
