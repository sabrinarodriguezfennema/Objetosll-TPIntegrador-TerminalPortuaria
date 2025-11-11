package servicios;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;

import containers.Container;

public class ServicioAlmacenamientoExcedenteTest {
	
	ServicioAlmacenamientoExcedente servicio;
	Container c1;
	
	@BeforeEach
	void setUp() {
		c1 = mock(Container.class);
		servicio = new ServicioAlmacenamientoExcedente(50, 5);
	}
	
	@Test
	void precio() {
		assertEquals(250, servicio.getPrecio(c1), 1);
	}
	
	@Test
	void obtenerCostoExcedente() {
		assertEquals(50, servicio.getCostoPorExcedente(), 1);
	}
	
	@Test
	void obtenerDiasExcedidos() {
		assertEquals(5, servicio.getDiasExcedidos(), 1);
	}
}