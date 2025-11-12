package rutaMaritima;

import static org.junit.Assert.*;


import static org.mockito.Mockito.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.*;

import interfaces.Buque;
import interfaces.ICircuito;
import interfaces.ITerminal;
import interfaces.IViaje;

public class RutaMaritimaTest {
	
	RutaMaritima ruta; 
	IViaje viaje; 
	ICircuito circuito;
	ITerminal terA;
	ITerminal terB;
	ITerminal terC;
	
	@BeforeEach
	void setUp() {
		
		viaje = mock(IViaje.class);
		terA = mock(ITerminal.class);
		terB = mock(ITerminal.class);
		terC = mock(ITerminal.class);
		circuito = mock(ICircuito.class);
		
		when(viaje.getCircuito()).thenReturn(circuito);
		
		when(circuito.getTodasLasTerminales()).thenReturn(List.of(terA, terB, terC));
		
		when(viaje.getCircuito()).thenReturn(circuito);
        when(viaje.cronograma()).thenReturn(
                Map.of( terA, LocalDate.of(2025, 11, 10),
                		terB, LocalDate.of(2025, 11, 13),
                		terC, LocalDate.of(2025, 11, 16)));
        
        ruta = new RutaMaritima(viaje,terA, terC);
	}
	
	@Test
    void obtenerPuertoDestino() {
        assertEquals(terC, ruta.puertoDestino());
    }

    @Test
    void fechaSalida() {
        assertEquals(LocalDate.of(2025, 11, 10), ruta.fechaSalida());
    }
    
    @Test
    void fechaLlegada() {
        assertEquals(LocalDate.of(2025, 11, 16), ruta.fechaLlegada());
    }
    	
	@Test
	void circuitoDelViaje() {
		
		assertTrue(ruta.getCircuito().equals(circuito));
	}
		
	@Test 
	void obtenerViaje() {
		assertEquals(viaje, ruta.getViaje());
	}

}
