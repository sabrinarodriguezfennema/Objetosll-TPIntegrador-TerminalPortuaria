package viajes.filtro;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class FiltrosTest {

	RutaMaritima unaRutaMaritima;
	Terminal unPuerto;
	
	@BeforeEach
	void setUp() throws Exception {
		unaRutaMaritima = mock(RutaMaritima.class);
		unPuerto        = mock(Terminal.class);
	}

	@Test
	void testFiltroSimplePuertoIgualAUnPuerto() {
		when(unaRutaMaritima.puertoDestino()).thenReturn(unPuerto);
		FiltroSimple unFiltroSimple = new PuertoIgualA(unPuerto);
		
		boolean resultado = unFiltroSimple.cumple(unaRutaMaritima); 
		
		assertTrue(resultado);
		verify(unaRutaMaritima).puertoDestino();
	}
	
	@Test
	void testFiltroSimplePuertoNoEsIgualAOtroPuerto() {
		Terminal otroPuerto = mock(Terminal.class);
		when(unaRutaMaritima.puertoDestino()).thenReturn(otroPuerto);
		FiltroSimple unFiltroSimple = new PuertoIgualA(unPuerto);
		
		boolean resultado = unFiltroSimple.cumple(unaRutaMaritima); 
		
		assertFalse(resultado);
		verify(unaRutaMaritima).puertoDestino();
	}
	
	@Test
	void testFiltroSimpleFechaLlegadaMenorA() {
		Date unaFecha = new Date(2025, 3, 1);
		when(unaRutaMaritima.fechaLlegada()).thenReturn(unaFecha);
		FiltroSimple unFiltroSimple = new FechaLlegadaMenorA(new Date(2025, 4, 1));
		
		boolean resultado = unFiltroSimple.cumple(unaRutaMaritima); 
		
		assertTrue(resultado);
		verify(unaRutaMaritima).fechaLlegada();
	}
	
	@Test
	void testFiltroSimpleFechaLlegadaNoEsMenorA() {
		Date unaFecha = new Date(2025, 3, 1);
		when(unaRutaMaritima.fechaLlegada()).thenReturn(unaFecha);
		FiltroSimple unFiltroSimple = new FechaLlegadaMenorA(new Date(2025, 2, 1));
		
		boolean resultado = unFiltroSimple.cumple(unaRutaMaritima); 
		
		assertFalse(resultado);
		verify(unaRutaMaritima).fechaLlegada();
	}
	
	@Test
	void testFiltroSimpleFechaLlegadaMayorA() {
		Date unaFecha = new Date(2025, 3, 1);
		when(unaRutaMaritima.fechaLlegada()).thenReturn(unaFecha);
		FiltroSimple unFiltroSimple = new FechaLlegadaMayorA(new Date(2025, 2, 1));
		
		boolean resultado = unFiltroSimple.cumple(unaRutaMaritima); 
		
		assertTrue(resultado);
		verify(unaRutaMaritima).fechaLlegada();
	}
	
	@Test
	void testFiltroSimpleFechaLlegadaNoEsMayorA() {
		Date unaFecha = new Date(2025, 3, 1);
		when(unaRutaMaritima.fechaLlegada()).thenReturn(unaFecha);
		FiltroSimple unFiltroSimple = new FechaLlegadaMayorA(new Date(2025, 4, 1));
		
		boolean resultado = unFiltroSimple.cumple(unaRutaMaritima); 
		
		assertFalse(resultado);
		verify(unaRutaMaritima).fechaLlegada();
	}
	
	@Test
	void testFiltroSimpleFechaSalidaMenorA() {
		Date unaFecha = new Date(2025, 3, 1);
		when(unaRutaMaritima.fechaSalida()).thenReturn(unaFecha);
		FiltroSimple unFiltroSimple = new FechaSalidaMenorA(new Date(2025, 4, 1));
		
		boolean resultado = unFiltroSimple.cumple(unaRutaMaritima); 
		
		assertTrue(resultado);
		verify(unaRutaMaritima).fechaSalida();
	}
	
	@Test
	void testFiltroSimpleFechaSalidaNoEsMenorA() {
		Date unaFecha = new Date(2025, 3, 1);
		when(unaRutaMaritima.fechaSalida()).thenReturn(unaFecha);
		FiltroSimple unFiltroSimple = new FechaSalidaMenorA(new Date(2025, 2, 1));
		
		boolean resultado = unFiltroSimple.cumple(unaRutaMaritima); 
		
		assertFalse(resultado);
		verify(unaRutaMaritima).fechaSalida();
	}
	
	@Test
	void testFiltroSimpleFechaSalidaMayorA() {
		Date unaFecha = new Date(2025, 3, 1);
		when(unaRutaMaritima.fechaSalida()).thenReturn(unaFecha);
		FiltroSimple unFiltroSimple = new FechaSalidaMayorA(new Date(2025, 2, 1));
		
		boolean resultado = unFiltroSimple.cumple(unaRutaMaritima); 
		
		assertTrue(resultado);
		verify(unaRutaMaritima).fechaSalida();
	}
	
	@Test
	void testFiltroSimpleFechaSalidaNoEsMayorA() {
		Date unaFecha = new Date(2025, 3, 1);
		when(unaRutaMaritima.fechaSalida()).thenReturn(unaFecha);
		FiltroSimple unFiltroSimple = new FechaSalidaMayorA(new Date(2025, 4, 1));
		
		boolean resultado = unFiltroSimple.cumple(unaRutaMaritima); 
		
		assertFalse(resultado);
		verify(unaRutaMaritima).fechaSalida();
	}

}
