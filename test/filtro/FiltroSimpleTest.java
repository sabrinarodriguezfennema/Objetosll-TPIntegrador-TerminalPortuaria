package filtro;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import filtro.FechaLlegadaAntesDe;
import filtro.FechaLlegadaDespuesDe;
import filtro.FechaSalidaAntesDe;
import filtro.FechaSalidaDespuesDe;
import filtro.FiltroSimple;
import filtro.PuertoIgualA;
import interfaces.IRutaMaritima;
import interfaces.ITerminal;
import paraMock.RutaMaritima;



class FiltroSimpleTest {

	IRutaMaritima unaRutaMaritima;
	ITerminal unPuerto;
	
	@BeforeEach
	void setUp() throws Exception {
		unaRutaMaritima = mock(IRutaMaritima.class);
		unPuerto        = mock(ITerminal.class);
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
		ITerminal otroPuerto = mock(ITerminal.class);
		when(unaRutaMaritima.puertoDestino()).thenReturn(otroPuerto);
		FiltroSimple unFiltroSimple = new PuertoIgualA(unPuerto);
		
		boolean resultado = unFiltroSimple.cumple(unaRutaMaritima); 
		
		assertFalse(resultado);
		verify(unaRutaMaritima).puertoDestino();
	}
	
	@Test
	void testFiltroSimpleFechaLlegadaMenorA() {
		LocalDateTime unaFecha = LocalDateTime.of(2025, 3, 1, 0, 0);
		when(unaRutaMaritima.fechaLlegada()).thenReturn(unaFecha);
		FiltroSimple unFiltroSimple = new FechaLlegadaAntesDe(LocalDateTime.of(2025, 4, 1, 0, 0));
		
		boolean resultado = unFiltroSimple.cumple(unaRutaMaritima); 
		
		assertTrue(resultado);
		verify(unaRutaMaritima).fechaLlegada();
	}
	
	@Test
	void testFiltroSimpleFechaLlegadaNoEsMenorA() {
		LocalDateTime unaFecha = LocalDateTime.of(2025, 3, 1, 0, 0);
		when(unaRutaMaritima.fechaLlegada()).thenReturn(unaFecha);
		FiltroSimple unFiltroSimple = new FechaLlegadaAntesDe(LocalDateTime.of(2025, 2, 1, 0, 0));
		
		boolean resultado = unFiltroSimple.cumple(unaRutaMaritima); 
		
		assertFalse(resultado);
		verify(unaRutaMaritima).fechaLlegada();
	}
	
	@Test
	void testFiltroSimpleFechaLlegadaMayorA() {
		LocalDateTime unaFecha = LocalDateTime.of(2025, 3, 1, 0, 0);
		when(unaRutaMaritima.fechaLlegada()).thenReturn(unaFecha);
		FiltroSimple unFiltroSimple = new FechaLlegadaDespuesDe(LocalDateTime.of(2025, 2, 1, 0, 0));
		
		boolean resultado = unFiltroSimple.cumple(unaRutaMaritima); 
		
		assertTrue(resultado);
		verify(unaRutaMaritima).fechaLlegada();
	}
	
	@Test
	void testFiltroSimpleFechaLlegadaNoEsMayorA() {
		LocalDateTime unaFecha = LocalDateTime.of(2025, 3, 1, 0, 0);
		when(unaRutaMaritima.fechaLlegada()).thenReturn(unaFecha);
		FiltroSimple unFiltroSimple = new FechaLlegadaDespuesDe(LocalDateTime.of(2025, 4, 1, 0, 0));
		
		boolean resultado = unFiltroSimple.cumple(unaRutaMaritima); 
		
		assertFalse(resultado);
		verify(unaRutaMaritima).fechaLlegada();
	}
	
	@Test
	void testFiltroSimpleFechaSalidaMenorA() {
		LocalDateTime unaFecha = LocalDateTime.of(2025, 3, 1, 0, 0);
		when(unaRutaMaritima.fechaSalida()).thenReturn(unaFecha);
		FiltroSimple unFiltroSimple = new FechaSalidaAntesDe(LocalDateTime.of(2025, 4, 1, 0, 0));
		
		boolean resultado = unFiltroSimple.cumple(unaRutaMaritima); 
		
		assertTrue(resultado);
		verify(unaRutaMaritima).fechaSalida();
	}
	
	@Test
	void testFiltroSimpleFechaSalidaNoEsMenorA() {
		LocalDateTime unaFecha = LocalDateTime.of(2025, 3, 1, 0, 0);
		when(unaRutaMaritima.fechaSalida()).thenReturn(unaFecha);
		FiltroSimple unFiltroSimple = new FechaSalidaAntesDe(LocalDateTime.of(2025, 2, 1, 0, 0));
		
		boolean resultado = unFiltroSimple.cumple(unaRutaMaritima); 
		
		assertFalse(resultado);
		verify(unaRutaMaritima).fechaSalida();
	}
	
	@Test
	void testFiltroSimpleFechaSalidaMayorA() {
		LocalDateTime unaFecha = LocalDateTime.of(2025, 3, 1, 0, 0);
		when(unaRutaMaritima.fechaSalida()).thenReturn(unaFecha);
		FiltroSimple unFiltroSimple = new FechaSalidaDespuesDe(LocalDateTime.of(2025, 2, 1, 0, 0));
		
		boolean resultado = unFiltroSimple.cumple(unaRutaMaritima); 
		
		assertTrue(resultado);
		verify(unaRutaMaritima).fechaSalida();
	}
	
	@Test
	void testFiltroSimpleFechaSalidaNoEsMayorA() {
		LocalDateTime unaFecha = LocalDateTime.of(2025, 3, 1, 0, 0);
		when(unaRutaMaritima.fechaSalida()).thenReturn(unaFecha);
		FiltroSimple unFiltroSimple = new FechaSalidaDespuesDe(LocalDateTime.of(2025, 4, 1, 0, 0));
		
		boolean resultado = unFiltroSimple.cumple(unaRutaMaritima); 
		
		assertFalse(resultado);
		verify(unaRutaMaritima).fechaSalida();
	}

}
