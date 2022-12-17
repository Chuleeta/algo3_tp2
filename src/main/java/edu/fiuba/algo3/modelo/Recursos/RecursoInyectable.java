package edu.fiuba.algo3.modelo.Recursos;

import edu.fiuba.algo3.modelo.Ocupable;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Edificios.Asimilador;
import edu.fiuba.algo3.modelo.Edificios.Extractor;
import edu.fiuba.algo3.modelo.Edificios.NexoMineral;
import edu.fiuba.algo3.modelo.Exceptions.VolcanOcupadoException;
import edu.fiuba.algo3.modelo.Individuos.Zangano;

public interface RecursoInyectable extends Ocupable{
    boolean inyectarRecurso(Extractor extractor) throws VolcanOcupadoException;
    boolean inyectarRecurso(Asimilador asimilador) throws VolcanOcupadoException;
    boolean inyectarRecurso(NexoMineral nexoMineral);
    boolean inyectarRecurso(Zangano zangano);

    String getSpray();
    Posicion mostrarPosicion ();
}
