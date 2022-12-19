package edu.fiuba.algo3.javafx;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.stream.IntStream;

import edu.fiuba.algo3.modelo.Construccion;
import edu.fiuba.algo3.modelo.Individuos.Individuo;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Recursos.RecursoInyectable;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Tablero {
    private Juego juego;
    private Group contenedor;
    private Rectangle[][] grilla;
    private int startX;
    private int startY;
    private Pane mapaVista;
    private int alto;
    private int ancho;
    private JuegoVista juegoVista;
    
    public Tablero(int alto, int ancho, Juego juego, JuegoVista juegoVista){
        this.juegoVista = juegoVista;
        this.alto = alto;
        this.ancho = ancho;
        this.cargarImagenes();
        this.mapaVista = new Pane();
        this.juego = juego;
        crearGrilla();
        Rectangle J1 = new Rectangle(20, 20, Color.GREEN);
        J1.setTranslateX(10);
        J1.setTranslateY(10);
        hacerMovible(J1);

        actualizarConstrucciones();
        // ArrayList<Construccion> construcciones = juego.mostrarConstrucciones();
        // for (Construccion construccion : construcciones) {
        //     Rectangle J2 = new Rectangle(20, 20, Color.GREEN);
        //     J2.setTranslateX((construccion.mostrarPosicion().coordenadaX() * 40) + 10);
        //     J2.setTranslateY((construccion.mostrarPosicion().coordenadaY() * 40) + 10);
        //     hacerMovible(J2);
        //     mapaVista.getChildren().add(J2);
        // }

        // Rectangle J2 = new Rectangle(20, 20, Color.GREEN);
        // J2.setTranslateX(850);
        // J2.setTranslateY(691);
        // hacerMovible(J2);

        mapaVista.getChildren().add(J1);
        //mapaVista.getChildren().add(J2);

        mapaVista.setPrefSize(880, 721);
        mapaVista.setStyle("-fx-background-color: #717D8C");

        this.contenedor = new Group(mapaVista);
    }

    private void crearGrilla() {
        grilla = new Rectangle[this.alto][this.ancho];
        int coordY = 0;
        int coordX = 0;
        for (int i = 0; i < this.alto; i += 1) {
            for (int j = 0; j < this.ancho; j += 1) {
                Rectangle r = new Rectangle(i, j, 40, 40);
                grilla[i][j] = r;
                r.setFill(Color.WHITE);
                r.setStroke(Color.BLACK);
                r.setX(coordX);
                r.setY(coordY);
                mapaVista.getChildren().add(r);
                coordX += 40;
            }
            coordY += 40;
            coordX = 0;
        }
    }

    public UnidadMovible crearUnidadMovible(Individuo individuo) {
        int coordenadaX = (individuo.posicion().coordenadaX() * 40) + 10;
        int coordenadaY = (individuo.posicion().coordenadaY() * 40) + 10;
        UnidadMovible unidad = new UnidadMovible(individuo, coordenadaX, coordenadaY);
        hacerMovible(unidad);
        this.juegoVista.agregarSuscriptorPasarTurno(unidad);
        return unidad;
    }
    public UnidadEstatica crearUnidadEstatica(Construccion construccion) {
        int coordenadaX = (construccion.mostrarPosicion().coordenadaX() * 40) + 10;
        int coordenadaY = (construccion.mostrarPosicion().coordenadaY() * 40) + 10;
        UnidadEstatica unidad = new UnidadEstatica(construccion, coordenadaX, coordenadaY);
        this.juegoVista.agregarSuscriptorPasarTurno(unidad);
        return unidad;
    }

    public void actualizarConstrucciones() {
        ArrayList<Construccion> construcciones = juego.mostrarConstrucciones();
        ArrayList<Individuo> unidades = juego.mostrarUnidades();
        for (Construccion construccion : construcciones) {
            UnidadEstatica unidad = crearUnidadEstatica(construccion);
            mapaVista.getChildren().add(unidad);
        }
        for (Individuo individuo : unidades) {
            UnidadMovible unidad = crearUnidadMovible(individuo);
            mapaVista.getChildren().add(unidad);
        }

        ArrayList<RecursoInyectable> recursos = juego.mostrarRecursos();
        for (RecursoInyectable recurso : recursos) {
            Rectangle J2 = new Rectangle(20, 20, Color.GREEN);
            if(recurso.getSpray() == "rojo"){
                J2 = new Rectangle(20, 20, Color.RED);
            }else{
                J2 = new Rectangle(20, 20, Color.BLUE);
            }
            J2.setTranslateX((recurso.mostrarPosicion().coordenadaX() * 40) + 10);
            J2.setTranslateY((recurso.mostrarPosicion().coordenadaY() * 40) + 10);
            hacerMovible(J2);
            mapaVista.getChildren().add(J2);
        }
    }

    private void seleccionarVerPosicion(Node n){
        
    }

    private void hacerMovible(Node n) {
        Paint colorOriginal = ((Shape) n).getFill();;
        n.setOnMousePressed(e ->{
            this.startX = (int)(e.getSceneX() - n.getTranslateX());
            this.startY = (int)(e.getSceneY() - n.getTranslateY());
            ((Shape) n).setFill(Color.RED);
        });

        n.setOnMouseDragged(e ->{
            //EVITA QUE SE VAYA EN LOS BORDES
            if(e.getSceneX() - startX < 0 && e.getSceneY() - startY <= 0){
                n.setTranslateX(0);
                n.setTranslateY(0);
            }else if(e.getSceneX() - startX > 860 && e.getSceneY() - startY < 0){
                n.setTranslateX(860);
                n.setTranslateY(0);
            }else if(e.getSceneX() - startX < 0 && e.getSceneY() - startY >= 701){
                n.setTranslateX(0);
                n.setTranslateY(701);
            }else if(e.getSceneX() - startX > 860 && e.getSceneY() - startY >= 701){
                n.setTranslateX(860);
                n.setTranslateY(701);
            }else if(e.getSceneX() - startX < 0){
                n.setTranslateX(0);
                n.setTranslateY(e.getSceneY() - startY);
            }else if(e.getSceneY() - startY < 0){
                n.setTranslateX(e.getSceneX() - startX);
                n.setTranslateY(0);
            }else if(e.getSceneX() - startX > 860){
                n.setTranslateX(860);
                n.setTranslateY(e.getSceneY() - startY);
            }else if(e.getSceneY() - startY > 701){
                n.setTranslateX(e.getSceneX() - startX);
                n.setTranslateY(701);
            }else{
                //MOVIMIENTO LIBRE
                n.setTranslateX(e.getSceneX() - startX);
                n.setTranslateY(e.getSceneY() - startY);
            }
            //PARA VER LA POSICION DEL NODO O CUADRADO
            //System.out.println("\nX: " + n.getTranslateX());
            //System.out.println("\nY: " + n.getTranslateY());
        });
        
        n.setOnMouseReleased(e ->{
            /*
             * Aca lo que pasa es que se toma la posicion del mouse 
             * se le aproxima un multiplo de 40
             * una vez se consigue ese multiplo de 40 se le sumo 10 en ambos ejes
             * y de esta forma queda centrado en el cuadrado
             * 
             * SI SE QUIERE CONSEGUIR LA POSICION DEL MAPA PARA EL MODELO:
             * ej: tengo la posicion (453.4343, 300.32423)
             * primero se encuentra el multiplo mas cercano osea: (440, 320)
             * desp se divide por 40 ya que es lo q miden los lados de los cuadrados que hacen de mapa
             * (440, 320) ---> (11, 8) ---> new Posicion(11,8)
            */
            ((Shape) n).setFill(colorOriginal);// 458.12312039123 ---> 458.1--> 450---> 11.25 --> 11 13 --> posicion(11, 13)
            double resultadoX = new BigDecimal(n.getTranslateX()).setScale(1, RoundingMode.UP).doubleValue();
            double resultadoY = new BigDecimal(n.getTranslateY()).setScale(1, RoundingMode.UP).doubleValue();
            n.setTranslateX(encontrarMultiploDeMasCercanoA(40, (int)resultadoX) + 10);
            n.setTranslateY(encontrarMultiploDeMasCercanoA(40, (int)resultadoY) + 10);
            double xDouble = encontrarMultiploDeMasCercanoA(40, (int)resultadoX);
            double yDouble = encontrarMultiploDeMasCercanoA(40, (int)resultadoY);
            int x = (Math.round((float)xDouble) / 40) + 1;
            int y = (Math.round((float)yDouble) / 40) + 1;
            this.juegoVista.setPosicionSeleccionada(x, y);

        });
    }

    private double encontrarMultiploDeMasCercanoA(int multiplicador, double coordenada) {
        return multiplicador*(int)(coordenada/(double)multiplicador);
    }

    public Group getContenedor(){
        return this.contenedor;
    }

	private void cargarImagenes() {
	}

    public void insertarConstruccion(Construccion construccion) {
        if(construccion == null)
            System.out.println("\n inserta construccion");
        UnidadEstatica nuevo = crearUnidadEstatica(construccion);
        mapaVista.getChildren().add(nuevo);
    }
    public void insertarUnidad(Individuo unidad) {
        if(unidad == null)
            System.out.println("\n inserta construccion");
        UnidadMovible nuevo = crearUnidadMovible(unidad);
        mapaVista.getChildren().add(nuevo);
    }
}
