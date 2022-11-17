package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Edificios.Criadero;
import edu.fiuba.algo3.modelo.Edificios.Espiral;
import edu.fiuba.algo3.modelo.Edificios.Guarida;
import edu.fiuba.algo3.modelo.Edificios.ReservaDeReproduccion;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Individuos.Hidralisco;
import edu.fiuba.algo3.modelo.Individuos.Mutalisco;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EspiralTest {
    //caso de uso 10

    @Test
    public void seRegeneraTodaLaVidaDespuesDeAlgunosTurnos() throws NoExisteEdificioCorrelativoException {
        //given
        Mapa mapa = new Mapa();
        Criadero criadero = new Criadero(new Posicion(1,3), mapa);
        mapa.agregarConstruccion(criadero, new Mineral(10000), new GasVespeno(10000));
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        ReservaDeReproduccion reserva = new ReservaDeReproduccion(new Posicion(1,2), mapa);
        mapa.agregarConstruccion(reserva, new Mineral(10000), new GasVespeno(10000));
        for(int i = 0; i < 13; i += 1){
            reserva.pasarTiempo();
        }
        Guarida guarida = new Guarida(new Posicion(1,1), mapa);
        mapa.agregarConstruccion(guarida, new Mineral(10000), new GasVespeno(10000));
        guarida.pasarTiempo();
        guarida.pasarTiempo();
        guarida.pasarTiempo();
        guarida.pasarTiempo();
        guarida.pasarTiempo();
        guarida.pasarTiempo();
        guarida.pasarTiempo();
        guarida.pasarTiempo();
        guarida.pasarTiempo();
        guarida.pasarTiempo();
        guarida.pasarTiempo();
        guarida.pasarTiempo();

        Espiral espiral = new Espiral(new Posicion(1,1), mapa);
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();

        //when
        espiral.dañar(200);
        espiral.pasarTiempo();
        espiral.pasarTiempo();

        //then
        assertTrue(espiral.tieneVidaCompleta());
    }

    @Test
    public void seRegeneraLaVidaParcialmenteDespuesDeUnTurno() throws NoExisteEdificioCorrelativoException {
        //given
        Espiral espiral = new Espiral(new Posicion(1,1), new Mapa());
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();

        //when
        espiral.dañar(200);
        espiral.pasarTiempo();

        //then
        assertFalse(espiral.tieneVidaCompleta());
    }

    // Caso de uso 17
    @Test
    public void noSePuedeConstruirEspiralSiNoHayGuarida() throws NoExisteEdificioCorrelativoException {
        //given
        Espiral espiral = new Espiral(new Posicion(1,1), new Mapa());
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        espiral.pasarTiempo();
        assertThrows(NoExisteEdificioCorrelativoException.class, () ->{ espiral.pasarTiempo();});
    }


    // caso 22
    @Test
    public void seEngendraMutaliscoExitosamente() throws NoExisteEdificioCorrelativoException, RequerimientosInsuficientesException {
        //given
        Mapa mapa = new Mapa();
        // es el edificio correlativo
        ReservaDeReproduccion reservaDeReproduccion = new ReservaDeReproduccion(new Posicion(2,2), mapa);
        reservaDeReproduccion.agregarAlMapa(new Mineral(150), new GasVespeno(100));
        Guarida guarida = new Guarida(new Posicion(1,1), mapa);
        guarida.agregarAlMapa(new Mineral(200), new GasVespeno(100));
        Espiral espiral = new Espiral(new Posicion(3,3), mapa);
        espiral.agregarAlMapa(new Mineral(150), new GasVespeno(100));
        for (int i = 0; i < 10; i++)
            espiral.pasarTiempo();

        Mineral mineral = new Mineral(100);
        GasVespeno gas = new GasVespeno(100);
        Mutalisco mutalisco = espiral.generarMutalisco(mineral, gas, new Larva());
        assertNotNull(mutalisco);
    }
}
