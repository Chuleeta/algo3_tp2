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
        zona = new ZonaMoho(this.posicion);
        zanganos = new ArrayList<>();
        gas = new GasVespeno(0);
        tiempo = 0;
        vida = 750;
    }

    public void pasarTiempo()
    {
        tiempo += 1;
        regenerarVida();
        if (estado.puedeConstruirse(6, tiempo)) construir();
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
        //mapa.agregarZona(zona);
    }

    @Override
    public boolean habita(Zona zona) {
        if(!this.zona.getClass().equals(zona.getClass())) return false;
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
