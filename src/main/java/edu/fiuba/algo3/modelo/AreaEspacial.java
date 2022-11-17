package edu.fiuba.algo3.modelo;

public class AreaEspacial {
    private int inicioX, finalX;
    private int inicioY, finalY;
    public AreaEspacial(int inicioX, int inicioY, int finalX, int finalY)
    {
        this.inicioX = inicioX;
        this.inicioY = inicioY;
        this.finalX = finalX;
        this.finalY = finalY;
    }

    public boolean abarca(Posicion posicion)
    {
        return posicion.adentro(inicioX, inicioY, finalX, finalY);
    }
}
