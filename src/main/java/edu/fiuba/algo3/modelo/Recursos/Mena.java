package edu.fiuba.algo3.modelo.Recursos;

import edu.fiuba.algo3.modelo.Ocupable;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Edificios.Asimilador;
import edu.fiuba.algo3.modelo.Edificios.Extractor;
import edu.fiuba.algo3.modelo.Edificios.NexoMineral;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruccion;
import edu.fiuba.algo3.modelo.Exceptions.MenaOcupadaException;
import edu.fiuba.algo3.modelo.Exceptions.VolcanOcupadoException;
import edu.fiuba.algo3.modelo.Individuos.Zangano;

public class Mena implements RecursoInyectable{
    private Posicion posicion;
    private int minerales;
    private boolean estaOcupado;

    public Mena(Posicion posicion)
    {
        this.posicion = posicion;
        this.minerales = 2000;
        this.estaOcupado = false;
    }

    public int extraerMineral(int cantidad)
    {
        if (this.minerales >= cantidad)
        {
            this.minerales -= cantidad; 
            return cantidad;
        }
        return this.minerales;   
    }

    public void ocupar() throws MenaOcupadaException {
        if (this.estaOcupado) {
            throw new MenaOcupadaException();
        }
        this.estaOcupado = true;
    }

    public boolean estaOcupada(Posicion posicionDada) {
        return this.posicion.equals(posicionDada);
    }

    public Posicion getPosicion() {
        return this.posicion;
    }

    @Override
    public boolean inyectarRecurso(Extractor extractor) throws VolcanOcupadoException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean inyectarRecurso(Asimilador asimilador) throws VolcanOcupadoException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean inyectarRecurso(NexoMineral nexoMineral) throws MenaOcupadaException {
        if(estaOcupada(nexoMineral.posicion) && !estaOcupado){
            nexoMineral.setMena(this);
            return true;
        }
        return false;
    }

    @Override
    public boolean inyectarRecurso(Zangano zangano) throws MenaOcupadaException {
        if(!estaOcupado){
            zangano.ocuparMena(this);
            return true;
        }
        return false;
    }

    @Override
    public String getSpray(){
        return "azul";
    }

    @Override
    public Posicion mostrarPosicion() {
        return this.posicion;
    }
}
