package edu.fiuba.algo3.modelo;

public class Guarida extends Edificio{
    private static int VIDA_COMPLETA = 1250;
    private VidaZerg vida;

    public Guarida(Posicion posicion, Mapa mapa)
    {
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.mapa = mapa;
        TURNOS_PARA_CONSTRUIRSE = 12;
        tiempo = 0;
        this.vida = new VidaZerg(VIDA_COMPLETA);
    }

    public void pasarTiempo()
    {
        tiempo += 1;
        regenerarVida();
        if (estado.puedeConstruirse(TURNOS_PARA_CONSTRUIRSE, tiempo)) construir();
    }

    private void regenerarVida() {
        this.vida.regenerarVida();
    }

    @Override
    public void construir()
    {
        estado = new EstadoConstruido();
        zona = new ZonaNeutral();
        mapa.agregarZona(this.zona);
    }

    @Override
    public boolean habita(Zona zona) {
        if(!this.zona.equals(zona)) return false;
        return zona.abarca(posicion);
    }

    public void dañar(int daño){
        this.vida.dañar(daño);
    }

    public boolean tieneVidaCompleta(){
        return this.vida.tieneVidaCompleta();
    }
}
