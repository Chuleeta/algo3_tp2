package edu.fiuba.algo3.modelo;

public class Posicion {

    private float x;
    private float y;

    public Posicion(int i, int j) {
        this.x = i;
        this.y = j;
    }

    public boolean adentro(float radio, Posicion centro)
    {
        return (centro.distX(this.x, radio)*centro.distX(this.x, radio) + centro.distY(this.y, radio)*centro.distY(this.y, radio) <= radio*radio);
    }

    private float distX(float axisX, float radio)
    {
        return this.x - axisX;
    }

    private float distY(float axisY, float radio)
    {
        return this.y - axisY;
    }

}
