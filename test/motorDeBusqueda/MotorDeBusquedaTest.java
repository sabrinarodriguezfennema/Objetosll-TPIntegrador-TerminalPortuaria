package motorDeBusqueda;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import filtro.Filtro;
import interfaces.IRutaMaritima;
import interfaces.IViaje;
import interfaces.ITerminal;

class MotorDeBusquedaTest {

	IRutaMaritima ruta1;
	IRutaMaritima ruta2;
	IRutaMaritima ruta3;
	Filtro filtro;
	IViaje viaje1;
	IViaje viaje2;
	IViaje viaje3;
	ITerminal t1;
	ITerminal t2;
	MotorDeBusqueda motorDeBusqueda;
	List<IViaje> todosLosViajes;

	@BeforeEach
	void setUp() throws Exception {
		todosLosViajes = new ArrayList<IViaje>();
		viaje1 = mock(IViaje.class);
		viaje2 = mock(IViaje.class);
		viaje3 = mock(IViaje.class);

		ruta1 = mock(IRutaMaritima.class);
		ruta2 = mock(IRutaMaritima.class);
		ruta3 = mock(IRutaMaritima.class);

		t1 = mock(ITerminal.class);
		t2 = mock(ITerminal.class);

		when(viaje1.rutaMaritimaDesde_Hasta_(t1, t2)).thenReturn(ruta1);
		when(viaje2.rutaMaritimaDesde_Hasta_(t1, t2)).thenReturn(ruta2);
		when(viaje3.rutaMaritimaDesde_Hasta_(t1, t2)).thenReturn(ruta3);

		todosLosViajes.add(viaje1);
		todosLosViajes.add(viaje2);
		todosLosViajes.add(viaje3);

		motorDeBusqueda = new MotorDeBusqueda(todosLosViajes, t1, t2);

		filtro = mock(Filtro.class);
	}

	@Test
	void testUnMotorDeBusquedaAplicaUnFiltroYRetornaTodasSusRutasMaritimas() {
		when(filtro.cumple(any(IRutaMaritima.class))).thenReturn(true);

		motorDeBusqueda.aplicarFiltro(filtro);
		List<IRutaMaritima> resultado = motorDeBusqueda.getRutasFiltradas();

		assertEquals(3, resultado.size());
	}

	@Test
	void testUnMotorDeBusquedaAplicaUnFiltroYNoRetornaTodasSusRutasMaritimas() {
		when(filtro.cumple(any(IRutaMaritima.class)))
					.thenReturn(true)
					.thenReturn(false)
					.thenReturn(false);

		motorDeBusqueda.aplicarFiltro(filtro);
		List<IRutaMaritima> resultado = motorDeBusqueda.getRutasFiltradas();

		assertEquals(1, resultado.size());
	}

	@Test
	void testUnMotorDeBusquedaAplicaUnFiltroYNoRetornaTodasSusRutasMaritimasPeroAlReiniciarLaBusquedaYAplicarOtroFiltroSiLasDevuelve() {

		when(filtro.cumple(any(IRutaMaritima.class)))
					.thenReturn(true)
					.thenReturn(false)
					.thenReturn(false);

		motorDeBusqueda.aplicarFiltro(filtro);
		
		motorDeBusqueda.reiniciarBúsqueda();

		Filtro filtro2 = mock(Filtro.class);
		when(filtro2.cumple(any(IRutaMaritima.class))).thenReturn(true);
		
		motorDeBusqueda.aplicarFiltro(filtro2);
		
		List<IRutaMaritima> resultado = motorDeBusqueda.getRutasFiltradas();
		assertEquals(3, resultado.size());
		
	}

}
