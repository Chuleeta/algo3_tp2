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
    public boolean posicionAdentro(Posicion posicion) {
        boolean resultadoX = (inicioX < posicion.coordenadaX()) && (finalX > posicion.coordenadaX());
        boolean resultadoY = (inicioY > posicion.coordenadaY()) && (finalY < posicion.coordenadaY());
        return (resultadoY && resultadoX);
    }
    
    public int getPosicionInicialX(){
        return this.inicioX;
    }

    public int getPosicionInicialY(){
        return this.inicioY;
    }

    public int getPosicionFinalX(){
        return this.finalX;
    }

    public int getPosicionFinalY(){
        return this.finalY;
    }
}
