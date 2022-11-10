package edu.fiuba.algo3.modelo;

public class ReservaDeProduccion extends Edificio{
    private static int VIDA_COMPLETA = 1000;
    private VidaZerg vida;

    public ReservaDeProduccion(Posicion posicion, Mapa mapa)
    {
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.mapa = mapa;
        zona = new ZonaMoho(this.posicion);
        tiempo = 0;
        this.vida = new VidaZerg(VIDA_COMPLETA);
    }

    public void pasarTiempo()
    {
        tiempo += 1;
        this.vida.regenerarVida();
        if (estado.puedeConstruirse(12, tiempo)) construir();
    }


    @Override
    public void construir()
    {
        estado = new EstadoConstruido();
        
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

    @Override
    public boolean agregarAlMapa(Mineral mineral, GasVespeno gas) {
        if(mineral.invertir(150))
        {
            this.mapa.agregarEnListaConstruccion(this);
            return true;
        }
        return false;
    }
}
