package edu.fiuba.algo3.modelo.Individuos;

import edu.fiuba.algo3.modelo.Posicion;

public interface UnidadVoladora {
    void recibirDaño(int cantidad);
    Posicion posicion();
    boolean mover(Posicion posicion);
}
