package edu.fiuba.algo3.modelo;

public class Guarida extends Edificio{
    private static int VIDA_COMPLETA = 1250;
    private VidaZerg vida;

    public Guarida(Posicion posicion, Mapa mapa)
    {
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.mapa = mapa;
        this.zona = new ZonaMoho(this.posicion);
        tiempo = 0;
        this.vida = new VidaZerg(VIDA_COMPLETA);
    }

    public void pasarTiempo() throws NoExisteEdificioCorrelativoException {
        tiempo += 1;
        regenerarVida();
        if (estado.puedeConstruirse(12, tiempo)) construir();
    }

    private void regenerarVida() {
        this.vida.regenerarVida();
    }

    @Override
    public void construir() throws NoExisteEdificioCorrelativoException {
        if (!this.mapa.verificarEdificacionCorrelativa(new ReservaDeProduccion(new Posicion(0,0), new Mapa()))){
            throw new NoExisteEdificioCorrelativoException();
        }
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
        if(mineral.invertir(200)&& gas.invertir(100))
        {
            this.mapa.agregarEnListaConstruccion(this);
            return true;
        }
        return false;
    }
}
