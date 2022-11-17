package edu.fiuba.algo3.modelo;

import javafx.geometry.Pos;

public class Posicion {

    private int x;
    private int y;
    private int z;

    public Posicion(int i, int j) {
        this.x = i;
        this.y = j;
        this.z = 0;
    }

    public boolean adentro(float radio, Posicion centro)
    {
        return ((centro.distX(this.x, radio)*centro.distX(this.x, radio)) + (centro.distY(this.y, radio)*centro.distY(this.y, radio)) <= (radio*radio));
    }

    public boolean adentro(int inicioX, int inicioY, int finalX, int finalY)
    {
        return ((this.x > inicioX && this.x < finalX) && (this.y > inicioY && this.y < finalY));
    }

    private float distX(float axisX, float radio)
    {
        return this.x - axisX;
    }

    private float distY(float axisY, float radio)
    {
        return this.y - axisY;
    }

    public Posicion clone()
    {
        return new Posicion(this.x, this.y);
    }

    public void ascender()
    {
        this.z = 5; //Todos los aereos van a volar en el nivel 5 de altura
    }

    public void descender()
    {
        this.z = 0; //Todos los aereos van a volar en el nivel 5 de altura
    }


}
