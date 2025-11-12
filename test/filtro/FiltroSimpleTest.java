package filtro;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
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
		LocalDate unaFecha = LocalDate.of(2025, 3, 1);
		when(unaRutaMaritima.fechaLlegada()).thenReturn(unaFecha);
		FiltroSimple unFiltroSimple = new FechaLlegadaAntesDe(LocalDate.of(2025, 4, 1));
		
		boolean resultado = unFiltroSimple.cumple(unaRutaMaritima); 
		
		assertTrue(resultado);
		verify(unaRutaMaritima).fechaLlegada();
	}
	
	@Test
	void testFiltroSimpleFechaLlegadaNoEsMenorA() {
		LocalDate unaFecha = LocalDate.of(2025, 3, 1);
		when(unaRutaMaritima.fechaLlegada()).thenReturn(unaFecha);
		FiltroSimple unFiltroSimple = new FechaLlegadaAntesDe(LocalDate.of(2025, 2, 1));
		
		boolean resultado = unFiltroSimple.cumple(unaRutaMaritima); 
		
		assertFalse(resultado);
		verify(unaRutaMaritima).fechaLlegada();
	}
	
	@Test
	void testFiltroSimpleFechaLlegadaMayorA() {
		LocalDate unaFecha = LocalDate.of(2025, 3, 1);
		when(unaRutaMaritima.fechaLlegada()).thenReturn(unaFecha);
		FiltroSimple unFiltroSimple = new FechaLlegadaDespuesDe(LocalDate.of(2025, 2, 1));
		
		boolean resultado = unFiltroSimple.cumple(unaRutaMaritima); 
		
		assertTrue(resultado);
		verify(unaRutaMaritima).fechaLlegada();
	}
	
	@Test
	void testFiltroSimpleFechaLlegadaNoEsMayorA() {
		LocalDate unaFecha = LocalDate.of(2025, 3, 1);
		when(unaRutaMaritima.fechaLlegada()).thenReturn(unaFecha);
		FiltroSimple unFiltroSimple = new FechaLlegadaDespuesDe(LocalDate.of(2025, 4, 1));
		
		boolean resultado = unFiltroSimple.cumple(unaRutaMaritima); 
		
		assertFalse(resultado);
		verify(unaRutaMaritima).fechaLlegada();
	}
	
	@Test
	void testFiltroSimpleFechaSalidaMenorA() {
		LocalDate unaFecha = LocalDate.of(2025, 3, 1);
		when(unaRutaMaritima.fechaSalida()).thenReturn(unaFecha);
		FiltroSimple unFiltroSimple = new FechaSalidaAntesDe(LocalDate.of(2025, 4, 1));
		
		boolean resultado = unFiltroSimple.cumple(unaRutaMaritima); 
		
		assertTrue(resultado);
		verify(unaRutaMaritima).fechaSalida();
	}
	
	@Test
	void testFiltroSimpleFechaSalidaNoEsMenorA() {
		LocalDate unaFecha = LocalDate.of(2025, 3, 1);
		when(unaRutaMaritima.fechaSalida()).thenReturn(unaFecha);
		FiltroSimple unFiltroSimple = new FechaSalidaAntesDe(LocalDate.of(2025, 2, 1));
		
		boolean resultado = unFiltroSimple.cumple(unaRutaMaritima); 
		
		assertFalse(resultado);
		verify(unaRutaMaritima).fechaSalida();
	}
	
	@Test
	void testFiltroSimpleFechaSalidaMayorA() {
		LocalDate unaFecha = LocalDate.of(2025, 3, 1);
		when(unaRutaMaritima.fechaSalida()).thenReturn(unaFecha);
		FiltroSimple unFiltroSimple = new FechaSalidaDespuesDe(LocalDate.of(2025, 2, 1));
		
		boolean resultado = unFiltroSimple.cumple(unaRutaMaritima); 
		
		assertTrue(resultado);
		verify(unaRutaMaritima).fechaSalida();
	}
	
	@Test
	void testFiltroSimpleFechaSalidaNoEsMayorA() {
		LocalDate unaFecha = LocalDate.of(2025, 3, 1);
		when(unaRutaMaritima.fechaSalida()).thenReturn(unaFecha);
		FiltroSimple unFiltroSimple = new FechaSalidaDespuesDe(LocalDate.of(2025, 4, 1));
		
		boolean resultado = unFiltroSimple.cumple(unaRutaMaritima); 
		
		assertFalse(resultado);
		verify(unaRutaMaritima).fechaSalida();
	}

}
