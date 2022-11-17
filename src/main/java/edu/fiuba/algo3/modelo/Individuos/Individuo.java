package edu.fiuba.algo3.modelo.Individuos;

import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Vida;
import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruccion;

public abstract class Individuo 
{
    protected EstadoConstruccion estado;
    protected Vida vida;
    protected int unidadesDeDaño;
    protected float rangoDeAtaque;
    protected Posicion posicion;
    protected Mapa mapa;

    public Posicion posicion()
    {
        return this.posicion;
    }

    public void recibirDaño(int cantidad)
    {
        this.vida.dañar(cantidad);
    }

    public boolean estaDentroDelRango(Posicion posicion) {
        return posicion.adentro(this.rangoDeAtaque, this.posicion);
    }

    public boolean atacar(Edificio edificio) {
        if (estado.estaConstruido()) {
            if (estaDentroDelRango(edificio.posicion())) {
                edificio.dañar(unidadesDeDaño);
                return true;
            }
        }
        return false;
    }

    public boolean tieneVidaCompleta()
    {
        return this.vida.tieneVidaCompleta();
    }

    public abstract boolean atacar(UnidadTierra unidad);
    public abstract boolean atacar(UnidadVoladora unidad);
}
