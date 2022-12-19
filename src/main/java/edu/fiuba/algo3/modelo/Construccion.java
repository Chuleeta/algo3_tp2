package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Edificios.Correlativa;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import edu.fiuba.algo3.modelo.Zonas.ZonaEnergia;
import edu.fiuba.algo3.modelo.Zonas.ZonaMoho;
import edu.fiuba.algo3.modelo.Zonas.ZonaNeutral;

public interface Construccion extends Correlativa {
    void construir() throws NoExisteEdificioCorrelativoException;
    boolean habita(ZonaNeutral zona);
    boolean habita(ZonaMoho zona);
    boolean habita(ZonaEnergia zona);
    void pasarTiempo() throws NoExisteEdificioCorrelativoException;
    boolean agregarAlMapa(Mineral mineral, GasVespeno gas);
    void desactivar();
    boolean estaOcupada(Posicion posicionDada);
    Posicion mostrarPosicion ();
    Jugador mostrarJugador();
}
