package edu.fiuba.algo3.modelo;

public class PuertoEstelar extends Edificio {
    private VidaEscudoProtoss vidaYEscudo;

    public PuertoEstelar(Posicion posicion, Mapa mapa) {
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.zona = new ZonaEnergia(this.posicion);
        this.mapa = mapa;
        tiempo = 0;
        this.vidaYEscudo = new VidaEscudoProtoss(600, 600);
    }

    @Override
    public void pasarTiempo() throws NoExisteEdificioCorrelativoException {
        tiempo += 1;
        this.vidaYEscudo.repararEscudo();
        if (estado.puedeConstruirse(10, tiempo)) construir();
    }


    @Override
    public void construir() throws NoExisteEdificioCorrelativoException {
        if (!this.mapa.verificarEdificacionCorrelativa(new Acceso(new Posicion(0,0), new Mapa()))){
            throw new NoExisteEdificioCorrelativoException();
        }
        this.estado = new EstadoConstruido();
    }

    @Override
    public boolean habita(Zona zona) {
        if(this.mapa.hayMohoEnPosicion(this.posicion)) return false;
        if(!this.zona.getClass().equals(zona.getClass())) return false;
        return zona.abarca(posicion);
    }

    public void dañar(int daño){
        this.vidaYEscudo.dañar(daño);
    }

    public boolean tieneVidaCompleta() {
        return this.vidaYEscudo.tieneVidaCompleta();
    }

    public boolean tieneEscudoCompleto() {
        return this.vidaYEscudo.tieneEscudoCompleto();
    }

    @Override
    public boolean agregarAlMapa(Mineral mineral, GasVespeno gas) {
        if(mineral.invertir(150) && gas.invertir(150))
        {
            this.mapa.agregarEnListaConstruccion(this);
            return true;
        }
        return false;
    }

}
