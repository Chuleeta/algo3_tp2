package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class Extractor extends Edificio implements HabitanteMoho {

    private List<Zangano> zanganos;
    private static int VIDA_COMPLETA = 750;
    private GasVespeno gas;

    public Extractor(Posicion posicion, Mapa mapa)
    {
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.mapa = mapa;
        zona = new ZonaNeutral();
        zanganos = new ArrayList<>();
        gas = new GasVespeno(0);
        TURNOS_PARA_CONSTRUIRSE = 6;
        tiempo = 0;
        vida = 750;
    }

    public void pasarTiempo()
    {
        tiempo += 1;
        regenerarVida();
        if (estado.puedeConstruirse(TURNOS_PARA_CONSTRUIRSE, tiempo)) construir();
        extraerGas();
    }

    private void regenerarVida() {
        vida += 100;
        if(vida > VIDA_COMPLETA) vida = VIDA_COMPLETA;
    }

    public Integer obtenerGas()
    {
        return gas.getCantidad();
    }

    public void extraerGas(){
        for(Zangano zangano: zanganos){
            if(estado.estaConstruido()) gas.colectar(10);
        }
    }

    public void agregarZangano() 
    {
        if(zanganos.size() < 3)
            zanganos.add(new Zangano());
    }


    @Override
    public void construir()
    {
        estado = new EstadoConstruido();
        zona = new ZonaNeutral();
        mapa.agregarZona(zona);
    }

    @Override
    public boolean habita(Zona zona) {
        //return this.zona.equals(zona);
        if(!this.zona.equals(zona)) return false;
        return zona.abarca(posicion);
    }

    /*@Override
    public boolean habita(ZonaMoho zona) {
        return zona.abarca(this.posicion);
    }*/

    public void dañar(int daño){
        vida -= daño;
    }

    public boolean tieneVidaCompleta(){
        return vida == VIDA_COMPLETA;
    }

}
