package edu.fiuba.algo3.modelo;

import javafx.geometry.Pos;

public class Posicion {

    private int x;
    private int y;
    private int z;

    public Posicion(int i, int j) {
        x = i;
        y = j;
        z = 0;
    }

    public boolean adentro(float radio, Posicion centro)
    {
        return ((centro.distX(x, radio)*centro.distX(x, radio)) + (centro.distY(y, radio)*centro.distY(y, radio)) <= (radio*radio));
    }

    public boolean adentro(int inicioX, int inicioY, int finalX, int finalY)
    {
        return ((x > inicioX && x < finalX) && (y > inicioY && y < finalY));
    }

    private float distX(float axisX, float radio)
    {
        return x - axisX;
    }

    private float distY(float axisY, float radio)
    {
        return y - axisY;
    }

    public Posicion clone()
    {
        return new Posicion(x, y);
    }

    public void ascender()
    {
        z = 5; //Todos los aereos van a volar en el nivel 5 de altura
    }

    public void descender()
    {
        z = 0; //Todos los aereos van a volar en el nivel 5 de altura
    }


}
