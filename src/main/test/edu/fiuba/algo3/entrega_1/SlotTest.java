package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Criadero;
import edu.fiuba.algo3.modelo.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

// public class SlotTest {
    
//     // Caso de uso 3
//     @Test
//     public void seAgregaUnExtractorAUnSlotConGas() 
//     {
//         Slot slot = new Slot(0, 0);
//         slot.contaminar(new Gas());
//         assertTrue(slot.ocupar(new Extractor()));
//         assertFalse(slot.ocupar(new Extractor()));    
//     }

//     @Test
//     public void seAgregaUnAsimiladorAUnSlotConGas() 
//     {
//         Slot slot = new Slot(0, 0);
//         slot.contaminar(new Gas());
//         assertTrue(slot.ocupar(new Asimilador()));
//         assertFalse(slot.ocupar(new Asimilador()));    
//     }

//     @Test
//     public void noSePuedeAgregarUnCriaderoAUnSlotConGas() 
//     {
//         Slot slot = new Slot(0, 0);
//         slot.contaminar(new Gas());
//         assertFalse(slot.ocupar(new Criadero()));    
//     }
//     /* EXPANDIR PARA EL RESTO DE LAS CONSTRUCCIONES */
// }   
