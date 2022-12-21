package edu.fiuba.algo3.modelo.Recursos;

import edu.fiuba.algo3.modelo.Ocupable;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Edificios.Asimilador;
import edu.fiuba.algo3.modelo.Edificios.Extractor;
import edu.fiuba.algo3.modelo.Edificios.NexoMineral;
import edu.fiuba.algo3.modelo.Exceptions.VolcanOcupadoException;
import edu.fiuba.algo3.modelo.Individuos.Zangano;

public class Volcan implements RecursoInyectable{
    private Posicion posicion;
    private boolean ocupanteExtractor;
    private Extractor extractorTrabajando;
    private int gas;
    private boolean estaOcupado;

    public Volcan(Posicion posicion)
    {
        this.posicion = posicion;
        this.gas = 5000;
        this.estaOcupado = false;
    }

    public void ocupar() throws VolcanOcupadoException {
        if (this.estaOcupado) {
            throw new VolcanOcupadoException();
        }
        this.estaOcupado = true;
    }


    public int extraerGas(int cantidad) {
        if (this.gas >= cantidad)
        {
            this.gas -= cantidad;
            return cantidad;
        }
        return this.gas;
    }

    public Posicion getPosicion() {
        return this.posicion;
    }

    public boolean estaOcupada(Posicion posicionDada) {
        return this.posicion.posicionesIguales(posicionDada);
    }

    @Override
    public boolean inyectarRecurso(Extractor extractor) throws VolcanOcupadoException {
        if(!estaOcupado){
            extractor.setVolcan(this);
            extractorTrabajando = extractor;
            ocupanteExtractor = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean inyectarRecurso(Asimilador asimilador) throws VolcanOcupadoException {
        if(!estaOcupado){
            asimilador.setVolcan(this);
            return true;
        }
        return false;
    }

    @Override
    public boolean inyectarRecurso(NexoMineral nexoMineral) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean inyectarRecurso(Zangano zangano) {
        // TODO Auto-generated method stub
        if(estaOcupado && ocupanteExtractor){
            extractorTrabajando.agregarZangano(zangano);
            return true;
        }
        return false;
    }

    @Override
    public String getSpray(){
        return "/imagenes/volcan.png";
    }

    @Override
    public Posicion mostrarPosicion() {
        return this.posicion;
    }
}
