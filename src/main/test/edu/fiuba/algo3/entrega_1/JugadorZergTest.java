package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Criadero;
import edu.fiuba.algo3.modelo.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

// public class JugadorZergTest {
//     //caso de uso 8
//     @Test
//     public void CreamosUnJugadorZergNuevoEIntentamosConstruirAlgoConRecursos(){
//         JugadorZerg jugador = new JugadorZerg();

//         jugador.darMineral(10000000);

//         assertTrue(jugador.construir(new Criadero(new Posicion(1, 1))));
//         assertTrue(jugador.construir(new Extractor(new Posicion(2, 2))));
//         assertTrue(jugador.construir(new Reserva(new Posicion(3, 3))));
//         assertTrue(jugador.construir(new Guardia(new Posicion(4, 4))));
//         assertTrue(jugador.construir(new Espiral(new Posicion(5, 5))));
//     }

//     @Test
//     public void CreamosUnJugadorZergNuevoEIntentamosConstruirAlgoSinRecursos(){
//         JugadorZerg jugador = new JugadorZerg();

//         assertFalse(jugador.construir(new Criadero(new Posicion(1, 1))));
//         assertFalse(jugador.construir(new Extractor(new Posicion(2, 2))));
//         assertFalse(jugador.construir(new Reserva(new Posicion(3, 3))));
//         assertFalse(jugador.construir(new Guardia(new Posicion(4, 4))));
//         assertFalse(jugador.construir(new Espiral(new Posicion(5, 5))));
//     }
// }
