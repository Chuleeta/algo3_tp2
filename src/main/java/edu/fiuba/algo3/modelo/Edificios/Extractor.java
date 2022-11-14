package edu.fiuba.algo3.modelo.Edificios;

import java.util.ArrayList;
import java.util.List;
import edu.fiuba.algo3.modelo.EstadoConstruido;
import edu.fiuba.algo3.modelo.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.GasVespeno;
import edu.fiuba.algo3.modelo.HabitanteMoho;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Mineral;
import edu.fiuba.algo3.modelo.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.VidaZerg;
import edu.fiuba.algo3.modelo.Volcan;
import edu.fiuba.algo3.modelo.VolcanOcupadoException;
import edu.fiuba.algo3.modelo.Zangano;
import edu.fiuba.algo3.modelo.Zona;
import edu.fiuba.algo3.modelo.ZonaEnergia;
import edu.fiuba.algo3.modelo.ZonaMoho;
import edu.fiuba.algo3.modelo.ZonaNeutral;

public class Extractor extends Edificio implements HabitanteMoho {

    private List<Zangano> zanganos;
    private static int VIDA_COMPLETA = 750;
    private GasVespeno gas;
    //private int gas;
    private VidaZerg vida;
    private Volcan volcan;

    public Extractor(Posicion posicion, Volcan volcan, Mapa mapa) throws VolcanOcupadoException {
        this.volcan = volcan;
        this.volcan.ocupar();
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.mapa = mapa;
        zona = new ZonaMoho(this.posicion);
        zanganos = new ArrayList<>();
        gas = new GasVespeno(0);
        tiempo = 0;
        this.vida = new VidaZerg(VIDA_COMPLETA);
    }

    public void pasarTiempo() throws NoExisteEdificioCorrelativoException
    {
        tiempo += 1;
        //regenerarVida();
        // if (estado.puedeConstruirse(6, tiempo))
        //     construir();
        // if (this.estado.estaConstruido())
        //     extraerGas();
        this.estado = this.estado.desarrollar(this, 6, tiempo);
    }

    // private void regenerarVida() {
    //     this.vida.regenerarVida();
    // }

    public Integer obtenerGas()
    {
        return gas.getCantidad();
    }

    public void extraerGas(){
        for(Zangano zangano: zanganos){
            if(estado.estaConstruido()) gas.colectarGas(volcan);
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
    }

    @Override
    public boolean habita(ZonaNeutral zona) {
        return false;
    }

    @Override
    public boolean habita(ZonaMoho zona) {
        return zona.abarca(posicion);
    }

    @Override
    public boolean habita(ZonaEnergia zona) {
        return false;
    }

    /*@Override
    public boolean habita(ZonaMoho zona) {
        return zona.abarca(this.posicion);
    }*/

    public void dañar(int daño){
        this.vida.dañar(daño);
    }

    public boolean tieneVidaCompleta(){
        return this.vida.tieneVidaCompleta();
    }

    @Override
    public boolean agregarAlMapa(Mineral mineral, GasVespeno gas) {
        if(mineral.invertir(100))
        {
            this.mapa.agregarEnListaConstruccion(this);
            return true;
        }
        return false;
    }

    @Override
    public void actualizar(){
        this.vida.regenerarVida();
        extraerGas();
    }

}
