package edu.fiuba.algo3.modelo;

public class Posicion {

    private int x;
    private int y;

    public Posicion(int i, int j) {
        x = i;
        y = j;
    }

    public boolean adentro(float radio, Posicion centro) {
        return ((centro.distX(x) * centro.distX(x)) + (centro.distY(y) * centro.distY(y)) <= (radio * radio));
    }

    public boolean adentro(int inicioX, int inicioY, int finalX, int finalY) {
        return ((x > inicioX && x < finalX) && (y > inicioY && y < finalY));
    }

    private float distX(float axisX) {
        return x - axisX;
    }

    private float distY(float axisY) {
        return y - axisY;
    }

    public Posicion clone() {
        return new Posicion(x, y);
    }

    public int coordenadaY() {
        return y;
    }

    public int coordenadaX() {
        return x;
    }

}
