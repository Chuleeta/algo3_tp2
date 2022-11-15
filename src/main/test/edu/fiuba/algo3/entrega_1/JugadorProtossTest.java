package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

// public class JugadorProtossTest {
//     //caso de uso 8
//     @Test
//     public void CreamosUnJugadorProtossNuevoEIntentamosConstruirAlgoConRecursos(){
//         JugadorProtoss jugador = new JugadorProtoss();

//         jugador.darMineral(10000000);

//         assertTrue(jugador.construir(new NexoMineral(new Posicion(1, 1))));
//         assertTrue(jugador.construir(new Pilon(new Posicion(2, 2))));
//         assertTrue(jugador.construir(new Asimilador(new Posicion(3, 3))));
//         assertTrue(jugador.construir(new Acceso(new Posicion(4, 4))));
//         assertTrue(jugador.construir(new PuertoEstelar(new Posicion(5, 5))));
//     }

//     @Test
//     public void CreamosUnJugadorProtossNuevoEIntentamosConstruirAlgoSinRecursos(){
//         JugadorProtoss jugador = new JugadorProtoss();

//         assertFalse(jugador.construir(new NexoMineral(new Posicion(1, 1))));
//         assertFalse(jugador.construir(new Pilon(new Posicion(2, 2))));
//         assertFalse(jugador.construir(new Asimilador(new Posicion(3, 3))));
//         assertFalse(jugador.construir(new Acceso(new Posicion(4, 4))));
//         assertFalse(jugador.construir(new PuertoEstelar(new Posicion(5, 5))));
//     }
// }
