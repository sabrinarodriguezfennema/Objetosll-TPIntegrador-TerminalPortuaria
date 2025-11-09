package rutaMaritima;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.*;

import circuito.Circuito;
import tramo.*;
import viaje.Viaje;

public class RutaMaritimaTest {
	
	RutaMaritima ruta; 
	Viaje viaje; 
	Circuito circuito;
	Tramo tramo1, tramo2, tramo3;
	Terminal ter1, ter2, ter3, ter4;
	LocalDate fechaInicio;
	
	@BeforeEach
	void setUp() {
		viaje = mock(Viaje.class);
		circuito = mock(Circuito.class);
		tramo1 = mock(Tramo.class);
		tramo2 = mock(Tramo.class);
		tramo3 = mock(Tramo.class);
		ter1 = mock(Terminal.class);
		ter2 = mock(Terminal.class);
		ter3 = mock(Terminal.class);
		ter4 = mock(Terminal.class);
		
		when(viaje.getCircuito()).thenReturn(circuito);

		ruta = new RutaMaritima(viaje);
		
		fechaInicio = LocalDate.of(2025, 11, 9);
		
		when(viaje.getFechaInicio()).thenReturn(fechaInicio);
	}
	
	@Test 
	void contieneDestino() {
	    
	    when(circuito.getTodasLasTerminalesDestino()).thenReturn(List.of(ter2,ter3,ter4));
	    assertTrue(ruta.contienePuertoDestino(ter2));
	}
	
	@Test
	void noContieneDestino() {
		
	    when(circuito.getTodasLasTerminalesDestino()).thenReturn(List.of(ter2, ter3, ter4));
	    assertFalse(ruta.contienePuertoDestino(ter1));
	}

	@Test
	void fechaEstimadaDeLLegada() {
		
		when(circuito.duracionTotal()).thenReturn(Duration.ofDays(5));
		assertEquals(LocalDate.of(2025, 11, 14), ruta.fechaEstimadaDeLlegada());
	}
	
	@Test
	void llegaAntesDe() {
		
		LocalDate fechaLimite = LocalDate.of(2025, 11, 15); 
		
		when(circuito.duracionTotal()).thenReturn(Duration.ofDays(3));
		assertTrue(ruta.llegaAntesDe(fechaLimite));
	}
	
	@Test
	void noLlegaAntesDe() {
	
		LocalDate fechaLimite = LocalDate.of(2025, 11, 15); 
		
		when(circuito.duracionTotal()).thenReturn(Duration.ofDays(6));
		assertFalse(ruta.llegaAntesDe(fechaLimite));
	}
	
	@Test
	void saleDespuesDe() {
		
		LocalDate fecha = LocalDate.of(2025, 11, 5); 
		assertTrue(ruta.saleDespuesDe(fecha));
	}
	
	@Test
	void noSaleDespuesDe() {
		
		LocalDate fecha = LocalDate.of(2025, 11, 15); 
		assertFalse(ruta.saleDespuesDe(fecha));
	}

}
