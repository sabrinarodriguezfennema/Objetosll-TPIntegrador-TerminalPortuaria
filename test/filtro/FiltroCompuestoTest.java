package filtro;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import filtro.Filtro;
import filtro.FiltroCompuesto;
import filtro.FiltroCompuestoAND;
import filtro.FiltroCompuestoOR;
import filtro.FiltroSimple;
import paraMock.RutaMaritima;

class FiltroCompuestoTest {

	Filtro filtro1;
	Filtro filtro2;
	
	@BeforeEach
	void setUp() throws Exception {
		filtro1 = mock(Filtro.class);
		filtro2 = mock(Filtro.class);
	}

	@Test
	void testFiltroCompuestoAndRetornaTrueSiSusFiltrosCumplen() {
		when(filtro1.cumple(any())).thenReturn(true);
		when(filtro2.cumple(any())).thenReturn(true);
		
		FiltroCompuesto filtroCompuesto = new FiltroCompuestoAND(filtro1, filtro2);
		
		boolean resultado = filtroCompuesto.cumple(mock(RutaMaritima.class));
		
		assertTrue(resultado);
	}
	
	@Test
	void testFiltroCompuestoAndRetornaFalseSiAlMenosUnoDeSusFiltrosNoCumple() {
		when(filtro1.cumple(any())).thenReturn(true);
		when(filtro2.cumple(any())).thenReturn(false);
		
		FiltroCompuesto filtroCompuesto = new FiltroCompuestoAND(filtro1, filtro2);
		
		boolean resultado = filtroCompuesto.cumple(mock(RutaMaritima.class));
		
		assertFalse(resultado);
	}
	
	@Test
	void testFiltroCompuestoOrRetornaTrueSiAlMenosUnoDeSusFiltrosCumple() {
		when(filtro1.cumple(any())).thenReturn(true);
		when(filtro2.cumple(any())).thenReturn(false);
		
		FiltroCompuesto filtroCompuesto = new FiltroCompuestoOR(filtro1, filtro2);
		
		boolean resultado = filtroCompuesto.cumple(mock(RutaMaritima.class));
		
		assertTrue(resultado);
	}
	
	@Test
	void testFiltroCompuestoOrRetornaFalseSiSusFiltrosNoCumplen() {
		when(filtro1.cumple(any())).thenReturn(false);
		when(filtro2.cumple(any())).thenReturn(false);
		
		FiltroCompuesto filtroCompuesto = new FiltroCompuestoOR(filtro1, filtro2);
		
		boolean resultado = filtroCompuesto.cumple(mock(RutaMaritima.class));
		
		assertFalse(resultado);
	}
	
	@Test
	void testUnFiltroCompuestoPuedeContenerOtroFiltroCompuesto() {
		Filtro filtro3 = mock(FiltroSimple.class);
		when(filtro1.cumple(any())).thenReturn(false);
		when(filtro2.cumple(any())).thenReturn(true);
		
		when(filtro3.cumple(any())).thenReturn(true);
		
		FiltroCompuesto filtroCompuesto1 = new FiltroCompuestoOR(filtro1, filtro2);
		FiltroCompuesto filtroCompuesto2 = new FiltroCompuestoAND(filtroCompuesto1, filtro3);
		
		boolean resultado = filtroCompuesto2.cumple(mock(RutaMaritima.class));
		
		assertTrue(resultado);
		verify(filtro3).cumple(any());
		verify(filtro1).cumple(any());
	}

}
