package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AsimiladorTest {
    
    @Test
    public void asimiladorConsigueVeinteDeGasEnUnTurno() 
    {
        GasVespeno mockGasVespeno = mock(GasVespeno.class);
        EstadoConstruido estado = mock(EstadoConstruido.class);
        doCallRealMethod().when(estado).estaConstruido();
        when(estado.puedeConstruirse(6,6)).thenReturn(true);
        doCallRealMethod().when(mockGasVespeno).colectar(20);
        Asimilador asimilador = new Asimilador(new Posicion(1, 1), new Mapa());
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        asimilador.pasarTiempo();
        assertEquals(20, asimilador.obtenerGas());
    }

    @Test
    public void asimiladorEmpiezaSinGasRecolectado() 
    {
        Asimilador asimilador = new Asimilador(new Posicion(1, 1), new Mapa());
        assertEquals(0, asimilador.obtenerGas());    
    }

    @Test
    public void asimiladorConsigueVeinteDeGasEnCadaTurno() 
    {
        Asimilador asimilador = new Asimilador(new Posicion(1, 1), new Mapa());
        GasVespeno mockGasVespeno = mock(GasVespeno.class);
        EstadoConstruido estado = mock(EstadoConstruido.class);
        doCallRealMethod().when(estado).estaConstruido();
        when(estado.puedeConstruirse(6,6)).thenReturn(true);
        doCallRealMethod().when(mockGasVespeno).colectar(20);
        asimilador.pasarTiempo();
        assertEquals(0, asimilador.obtenerGas());
        asimilador.pasarTiempo();
        assertEquals(0, asimilador.obtenerGas());
        asimilador.pasarTiempo();
        assertEquals(0, asimilador.obtenerGas());
        asimilador.pasarTiempo();
        assertEquals(0, asimilador.obtenerGas());
        asimilador.pasarTiempo();
        assertEquals(0, asimilador.obtenerGas());
        asimilador.pasarTiempo();
        assertEquals(20, asimilador.obtenerGas());
        asimilador.pasarTiempo();
        assertEquals(40, asimilador.obtenerGas());
        asimilador.pasarTiempo();
        assertEquals(60, asimilador.obtenerGas());
        asimilador.pasarTiempo();
        assertEquals(80, asimilador.obtenerGas());
        asimilador.pasarTiempo();
        assertEquals(100, asimilador.obtenerGas());
    }

}
