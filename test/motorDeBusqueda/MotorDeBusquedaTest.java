package motorDeBusqueda;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import filtro.Filtro;
import interfaces.Terminal;
import motorDeBusqueda.MotorDeBusqueda;
import paraMock.RutaMaritima;

class MotorDeBusquedaTest {
	
	List<RutaMaritima> rutasMaritimas;
	RutaMaritima ruta1;
	RutaMaritima ruta2;
	RutaMaritima ruta3;
	Filtro filtro;

	@BeforeEach
	void setUp() throws Exception {
		rutasMaritimas = new ArrayList<RutaMaritima>();
		ruta1 = mock(RutaMaritima.class);
		ruta2 = mock(RutaMaritima.class);
		ruta3 = mock(RutaMaritima.class);
		rutasMaritimas.add(ruta1);
		rutasMaritimas.add(ruta2);
		rutasMaritimas.add(ruta3);
		
		filtro = mock(Filtro.class);
	}

	@Test
	void testUnMotorDeBusquedaAplicaUnFiltroYRetornaTodasSusRutasMaritimas() {
		when(filtro.cumple(ruta1)).thenReturn(true);
		when(filtro.cumple(ruta2)).thenReturn(true);
		when(filtro.cumple(ruta3)).thenReturn(true);
		
		
		MotorDeBusqueda unMotorDeBusqueda = new MotorDeBusqueda(rutasMaritimas);
		
		unMotorDeBusqueda.aplicarFiltro(filtro);
		List<RutaMaritima> resultado = unMotorDeBusqueda.getRutasFiltradas();
		
		assertEquals(3, resultado.size());
	}
	
	@Test
	void testUnMotorDeBusquedaAplicaUnFiltroYNoRetornaTodasSusRutasMaritimas() {
		when(filtro.cumple(ruta1)).thenReturn(true);
		when(filtro.cumple(ruta2)).thenReturn(false);
		when(filtro.cumple(ruta3)).thenReturn(false);
		
		
		MotorDeBusqueda unMotorDeBusqueda = new MotorDeBusqueda(rutasMaritimas);
		
		unMotorDeBusqueda.aplicarFiltro(filtro);
		List<RutaMaritima> resultado = unMotorDeBusqueda.getRutasFiltradas();
		
		assertEquals(1, resultado.size());
	}
	
	@Test
	void testUnMotorDeBusquedaAplicaUnFiltroYNoRetornaTodasSusRutasMaritimasPeroAlReiniciarLaBusquedaYAplicarOtroFiltroSiLasDevuelve() {
		when(filtro.cumple(ruta1)).thenReturn(true);
		when(filtro.cumple(ruta2)).thenReturn(false);
		when(filtro.cumple(ruta3)).thenReturn(false);
		
		
		MotorDeBusqueda unMotorDeBusqueda = new MotorDeBusqueda(rutasMaritimas);
		
		unMotorDeBusqueda.aplicarFiltro(filtro);
		
		unMotorDeBusqueda.reiniciarBúsqueda();
		
		Filtro filtro2 = mock(Filtro.class);
		
		when(filtro2.cumple(ruta1)).thenReturn(true);
		when(filtro2.cumple(ruta2)).thenReturn(true);
		when(filtro2.cumple(ruta3)).thenReturn(true);
		unMotorDeBusqueda.aplicarFiltro(filtro2);
		
		
		List<RutaMaritima> resultado = unMotorDeBusqueda.getRutasFiltradas();
		
		assertEquals(3, resultado.size());
	}


//	List<Viaje> todosLosViajes = new ArrayList<Viaje>();
//	Viaje viaje1 = mock(Viaje.class);
//	Viaje viaje2 = mock(Viaje.class);
//	Viaje viaje3 = mock(Viaje.class);
	
//	todosLosViajes.add(viaje1);
//	todosLosViajes.add(viaje2);
//	todosLosViajes.add(viaje3);
	
//	Terminal t1 = mock(Terminal.class);
//	Terminal t2 = mock(Terminal.class);
	
//	MotorDeBusqueda unMotorDeBusqueda = new MotorDeBusqueda(todasLosViajes, t1, t2);

}
