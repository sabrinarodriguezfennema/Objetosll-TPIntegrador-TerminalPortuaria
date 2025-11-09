package servicios;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.*;

import containers.ContainerReefer;

public class ServicioElectricoTest {

	ServicioElectrico servicio; 
	ContainerReefer c1;
	ContainerReefer c2;
	LocalDateTime fechaIngreso;
	LocalDateTime fechaEgreso;
	
	@BeforeEach
	void setUp() {
		
		fechaIngreso = LocalDateTime.of(2025, 10, 30, 23, 45); 
		fechaEgreso = LocalDateTime.of(2025, 11, 5, 23, 45);
		
		c1 = mock(ContainerReefer.class);
		c2 = mock(ContainerReefer.class);
		servicio = new ServicioElectrico(10, fechaIngreso, fechaEgreso );
		
	}
	
	@Test
	void obtenerPrecioTotal() { // obtener precio total por los dias que el contenedor aplico el servicio
		
		when(c1.getkwPorHora()).thenReturn(10);
		assertEquals(14400, servicio.getPrecio(c1));
		
		// container con otro precio
		
		when(c2.getkwPorHora()).thenReturn(20);
		assertEquals(28800, servicio.getPrecio(c2));
	}
	@Test
	void obtenerDiaEgreso() {
		
		assertEquals(fechaEgreso, servicio.getDiaEgreso());
	}
	@Test
	void obtenerDiaIngreso() {
		
		assertEquals(fechaIngreso, servicio.getDiaIngreso());
	}
	@Test
	void obtenerHorasTotalesPorDiasUtilizandoServicio() {
		
		assertEquals(144, servicio.tiempoEnTerminalEnHoras());
	}
}