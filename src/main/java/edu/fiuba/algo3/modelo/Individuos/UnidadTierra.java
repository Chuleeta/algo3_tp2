package edu.fiuba.algo3.modelo.Individuos;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Vida;

public interface UnidadTierra {
    void recibirDaño(int cantidad);
    Posicion posicion();
    boolean mover(Posicion posicion);
    Vida obtenerVida();

    boolean estaHabilitado(UnidadTierra unidad);
    boolean estaHabilitado(UnidadVoladora unidad);
}
