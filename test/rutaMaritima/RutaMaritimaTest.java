package rutaMaritima;

import static org.junit.Assert.*;


import static org.mockito.Mockito.*;

import java.time.Duration;
import java.time.LocalDate;

import org.junit.jupiter.api.*;

import interfaces.Buque;
import interfaces.Circuito;
import interfaces.Terminal;
import interfaces.Viaje;

public class RutaMaritimaTest {
	
	RutaMaritima ruta; 
	Viaje viaje; 
	Circuito circuito;
	Terminal ter1;
	LocalDate fechaInicio;
	
	@BeforeEach
	void setUp() {
		viaje = mock(Viaje.class);
		circuito = mock(Circuito.class);
		ter1 = mock(Terminal.class);
		
		when(viaje.getCircuito()).thenReturn(circuito);

		ruta = new RutaMaritima(viaje,ter1);
		
		fechaInicio = LocalDate.of(2025, 11, 9);
		
		when(viaje.getFechaInicio()).thenReturn(fechaInicio);
	}
	
	@Test
	void fechaEstimadaDeLLegada() {
		
		when(circuito.duracionTotal()).thenReturn(Duration.ofDays(5));
		assertEquals(LocalDate.of(2025, 11, 14), ruta.fechaLlegada());
	}
	
	@Test 
	void fechaDeInicioViaje() {
		
		assertTrue(ruta.fechaSalida().equals(fechaInicio));
	}
	
	@Test
	void circuitoDelViaje() {
		
		assertTrue(ruta.getCircuito().equals(circuito));
	}
	
	@Test
	void puertoDestino() {
		
		assertTrue(ruta.puertoDestino().equals(ter1));
	}
	
	@Test 
	void obtenerViaje() {
		assertEquals(viaje, ruta.getViaje());
	}
	
	@Test
	void getNombre() {
		Buque buque = mock(Buque.class);
		
		when(viaje.getBuque()).thenReturn(buque);
		when(buque.getNombre()).thenReturn("buque");
		assertEquals("buque", ruta.getNombre());
	}
}
