package edu.fiuba.algo3.javafx;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import edu.fiuba.algo3.javafx.Eventos.AtacarHandler;
import edu.fiuba.algo3.modelo.AreaEspacial;
import edu.fiuba.algo3.modelo.Construccion;
import edu.fiuba.algo3.modelo.Individuos.Individuo;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Recursos.RecursoInyectable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.AreaChart;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
        actualizar();
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

    public UnidadIndividuo crearUnidadMovible(Individuo individuo, Jugador oponente) {
        int coordenadaX = ((individuo.posicion().coordenadaX()-1) * 40);
        int coordenadaY = ((individuo.posicion().coordenadaY()-1) * 40);
        AtacarHandler action = new AtacarHandler(juegoVista, oponente, individuo);
        UnidadIndividuo unidad = new UnidadIndividuo(individuo, coordenadaX, coordenadaY, action);
        hacerMovible(unidad);
        this.juegoVista.agregarSuscriptorPasarTurno(unidad);
        return unidad;
    }
    public UnidadEdificio crearUnidadEstatica(Construccion construccion) {
        int coordenadaX = ((construccion.mostrarPosicion().coordenadaX()-1) * 40);
        int coordenadaY = ((construccion.mostrarPosicion().coordenadaY()-1) * 40);
        UnidadEdificio unidad = new UnidadEdificio(construccion, coordenadaX, coordenadaY);
        unidad.setOnMouseReleased(e ->{
            mostrarPosicion(unidad);
        });
        this.juegoVista.agregarSuscriptorPasarTurno(unidad);
        return unidad;
    }

    public void actualizarConstrucciones() {
        ArrayList<Construccion> construcciones = this.juego.mostrarConstrucciones();
        if(construcciones.size() > 0){
            for (Construccion construccion : construcciones) {
                this.insertarConstruccion(construccion);
            }
        }
    }

    public void actualizarZonasAereas() {
        ArrayList<AreaEspacial> areasEspaciales = this.juego.mostrarAreaEspacial();
        if(areasEspaciales.size() > 0){
            for (AreaEspacial areaEspacial : areasEspaciales) {
                this.insertarAreaEspacial(areaEspacial);
            }
        }
    }

    private void insertarAreaEspacial(AreaEspacial areaEspacial) {
        int posInicialX = areaEspacial.getPosicionInicialX();
        int posInicialY = areaEspacial.getPosicionInicialY();
        int posFinalX = areaEspacial.getPosicionFinalX();
        int posFinalY = areaEspacial.getPosicionFinalY();
        for(int i = posInicialX; i <= posFinalX; i++){
            for(int j = posInicialY; j >= posFinalY; j--){
                int coordenadaX = ((i-1) * 40);
                int coordenadaY = ((j-1) * 40);
                Rectangle r = new Rectangle(coordenadaX, coordenadaY, 40, 40);
                r.setFill(Color.BLACK);
                r.setStroke(Color.BLACK);
                r.setX(coordenadaX);
                r.setY(coordenadaY);
                mapaVista.getChildren().add(r);
            }
        }
    }

    public void actualizarRecursos() {
        ArrayList<RecursoInyectable> recursos = juego.mostrarRecursos();
        for (RecursoInyectable recurso : recursos) {
            int coordenadaX = ((recurso.mostrarPosicion().coordenadaX() - 1) * 40);
            int coordenadaY = ((recurso.mostrarPosicion().coordenadaY() - 1) * 40);
            UnidadRecurso unidad = new UnidadRecurso(recurso, coordenadaX /*- 1*/, coordenadaY /*- 1*/);
            unidad.setOnMouseReleased(e ->{
                mostrarPosicion(unidad);
            });
            mostrarPosicion(unidad);
            mapaVista.getChildren().add(unidad);
        }
    }
    public void actualizarUnidades() {
        ArrayList<Individuo> individuos = this.juego.mostrarUnidades();
        if(individuos.size() != 0){
            for (Individuo individuo : individuos) {
                this.insertarUnidad(individuo, juego.getJugadorUno());
            }
        }

    }

    private void seleccionarVerPosicion(Node n){
        
    }

    private void hacerMovible(UnidadIndividuo n) {
        Paint colorOriginal = ((Shape) n).getFill();;
        n.setOnMousePressed(e ->{
            this.startX = (int)(e.getSceneX() - n.getTranslateX());
            this.startY = (int)(e.getSceneY() - n.getTranslateY());
            ((Shape) n).setFill(Color.RED);
        });

        n.setOnMouseDragged(e ->{
            //EVITA QUE SE VAYA EN LOS BORDES
            if(e.getSceneX() - startX < 0 && e.getSceneY() - startY <= 0){
                if (n.moverUnidad(calcularPosicion(n))){
                    n.setTranslateX(0);
                    n.setTranslateY(0);
                }
            }else if(e.getSceneX() - startX > 860 && e.getSceneY() - startY < 0){
                if (n.moverUnidad(calcularPosicion(n))){
                    n.setTranslateX(860);
                    n.setTranslateY(0);
                }
            }else if(e.getSceneX() - startX < 0 && e.getSceneY() - startY >= 701){
                if (n.moverUnidad(calcularPosicion(n))){
                    n.setTranslateX(0);
                    n.setTranslateY(701);
                }
            }else if(e.getSceneX() - startX > 860 && e.getSceneY() - startY >= 701){
                if (n.moverUnidad(calcularPosicion(n))){
                    n.setTranslateX(860);
                    n.setTranslateY(701);
                }
            }else if(e.getSceneX() - startX < 0){
                if (n.moverUnidad(calcularPosicion(n))){
                    n.setTranslateX(0);
                    n.setTranslateY(e.getSceneY() - startY);
                }
            }else if(e.getSceneY() - startY < 0){
                if (n.moverUnidad(calcularPosicion(n))){
                    n.setTranslateX(e.getSceneX() - startX);
                    n.setTranslateY(0);
                }
            }else if(e.getSceneX() - startX > 860){
                if (n.moverUnidad(calcularPosicion(n))){
                    n.setTranslateX(860);
                    n.setTranslateY(e.getSceneY() - startY);
                }
            }else if(e.getSceneY() - startY > 701){
                if (n.moverUnidad(calcularPosicion(n))){
                    n.setTranslateX(e.getSceneX() - startX);
                    n.setTranslateY(701);
                }
            }else{
                //MOVIMIENTO LIBRE
                if (n.moverUnidad(calcularPosicion(n))){
                    n.setTranslateX(e.getSceneX() - startX);
                    n.setTranslateY(e.getSceneY() - startY);
                }
            }
        });
        
        n.setOnMouseReleased(e ->{

           mostrarPosicion(n);
           n.setFill(colorOriginal);

        });
    }

    private void mostrarPosicion(Rectangle n) {
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
        Paint colorOriginal = ((Shape) n).getFill();;
        ((Shape) n).setFill(colorOriginal);// 458.12312039123 ---> 458.1--> 450---> 11.25 --> 11 13 --> posicion(11, 13)
        this.juegoVista.setPosicionSeleccionada(calcularPosicion(n));

    }

    private Posicion calcularPosicion(Rectangle n) {
        double resultadoX = new BigDecimal(n.getTranslateX()).setScale(1, RoundingMode.UP).doubleValue();
        double resultadoY = new BigDecimal(n.getTranslateY()).setScale(1, RoundingMode.UP).doubleValue();
        n.setTranslateX(encontrarMultiploDeMasCercanoA(40, (int)resultadoX));
        n.setTranslateY(encontrarMultiploDeMasCercanoA(40, (int)resultadoY));
        double xDouble = encontrarMultiploDeMasCercanoA(40, (int)resultadoX);
        double yDouble = encontrarMultiploDeMasCercanoA(40, (int)resultadoY);
        int x = (Math.round((float)xDouble) / 40) + 1;
        int y = (Math.round((float)yDouble) / 40) + 1;
        return new Posicion(x, y);
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
        UnidadEdificio nuevo = crearUnidadEstatica(construccion);
        mapaVista.getChildren().add(nuevo);
    }
    public void insertarUnidad(Individuo unidad, Jugador oponente) {
        if(unidad != null) {
            UnidadIndividuo nuevo = crearUnidadMovible(unidad, oponente);
            mapaVista.getChildren().add(nuevo);
        } else {
            System.out.println("La unidad es null");
        }
    }

    public void actualizar() {
        mapaVista.getChildren().clear();
        crearGrilla();
        actualizarZonasAereas();
        actualizarRecursos();
        actualizarConstrucciones();
        actualizarUnidades();
    }
}
