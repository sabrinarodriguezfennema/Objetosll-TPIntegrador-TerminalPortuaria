package viaje;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.*;

import interfaces.Buque;
import interfaces.ICircuito;
import interfaces.IRutaMaritima;
import interfaces.IBuque;
import interfaces.ITerminal;
import interfaces.ITramo;

public class ViajeTest {

	Viaje viaje;
	IBuque buque;
	ICircuito circuito;
	LocalDateTime fechaInicio;
	ITramo tramo1;
	ITramo tramo2;
	ITerminal t1;
	ITerminal t2;
	ITerminal t3;

	@BeforeEach
	void setup() {

		fechaInicio = LocalDateTime.of(2025, 11, 10, 0, 0);
		circuito = mock(ICircuito.class);

		tramo1 = mock(ITramo.class);
		tramo2 = mock(ITramo.class);
		t1 = mock(ITerminal.class);
		t2 = mock(ITerminal.class);
		t3 = mock(ITerminal.class);

		viaje = new Viaje(fechaInicio, buque, circuito);
	}

	@Test
	void obtenerCircuito() {
		assertEquals(circuito, viaje.getCircuito());
	}

	@Test
	void obtenerBuque() {
		assertEquals(buque, viaje.getBuque());
	}

	@Test
	void obtenerFechaInicio() {
		assertEquals(fechaInicio, viaje.fechaSalida());
	}

	@Test
	void obtenerCronograma() {

		when(tramo1.getOrigen()).thenReturn(t1);
		when(tramo1.getDestino()).thenReturn(t2);

		when(tramo2.getOrigen()).thenReturn(t2);
		when(tramo2.getDestino()).thenReturn(t3);

		when(circuito.getTramos()).thenReturn(List.of(tramo1, tramo2));
		when(circuito.getTodasLasTerminales()).thenReturn(List.of(t1, t2, t3));

		Map<ITerminal, LocalDateTime> cronograma = viaje.cronograma();

		assertEquals(3, cronograma.size());
		assertEquals(fechaInicio, cronograma.get(t1));
		assertEquals(fechaInicio.plusDays(2), cronograma.get(t2));
		assertEquals(fechaInicio.plusDays(5), cronograma.get(t3));
	}

	@Test
	void montoViaje() {

		when(viaje.precioTotal()).thenReturn(500.00);
		assertEquals(500.00, viaje.precioTotal(), 1);
	}

	@Test
	void rutaMaritimaDesdeHasta_DeberiaCrearUnaRutaMaritimaCorrecta() {
		when(tramo1.getOrigen()).thenReturn(t1);
		when(tramo1.getDestino()).thenReturn(t2);

		when(tramo2.getOrigen()).thenReturn(t2);
		when(tramo2.getDestino()).thenReturn(t3);

		when(circuito.getTramos()).thenReturn(List.of(tramo1, tramo2));
		when(circuito.getTodasLasTerminales()).thenReturn(List.of(t1, t2, t3));
		
		IRutaMaritima ruta = viaje.rutaMaritimaDesde_Hasta_(t1, t2);

		assertNotNull(ruta);
	}

	@Test
	void rutaMaritimaDesdeHastaEnListaSiExiste_DeberiaAgregarRutaSiAmbasTerminalesExistenYEnOrden() {
		when(circuito.getTodasLasTerminales()).thenReturn(Arrays.asList(t1, t2, t3));

		List<IRutaMaritima> rutas = viaje.rutaMaritimaDesde_Hasta_EnListaSiExiste(t1, t2);

		assertEquals(1, rutas.size());

	}

	@Test
	void rutaMaritimaDesdeHastaEnListaSiExiste_NoAgregaSiTerminalesEnOrdenInverso() {
		when(circuito.getTodasLasTerminales()).thenReturn(Arrays.asList(t1, t2, t3));

		List<IRutaMaritima> rutas = viaje.rutaMaritimaDesde_Hasta_EnListaSiExiste(t2, t1);

		assertTrue(rutas.isEmpty());
	}

	@Test
	void rutaMaritimaDesdeHastaEnListaSiExiste_NoAgregaSiUnaTerminalNoPertenece() {
		when(circuito.getTodasLasTerminales()).thenReturn(Arrays.asList(t1, t2));

		List<IRutaMaritima> rutas = viaje.rutaMaritimaDesde_Hasta_EnListaSiExiste(t1, t3);

		assertTrue(rutas.isEmpty());
	}

}
