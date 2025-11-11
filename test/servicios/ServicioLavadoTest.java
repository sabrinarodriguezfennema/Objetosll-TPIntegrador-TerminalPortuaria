package servicios;



import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;

import containers.Container;

public class ServicioLavadoTest {
	
	ServicioLavado s;
	Container c; 
	
	@BeforeEach 
	void setUp() {
		
		s = new ServicioLavado(200.00, 100.00);
		c = mock(Container.class);
	}
	
	@Test
	void obtenerPrecioSuperando70() {
		assertEquals(200.00, s.getMontoSupera70(), 1);
	}
	
	@Test
	void obtenerPrecioDebajo70() {
		assertEquals(100, s.getMontoDebajo70(),1);
	}
	
	@Test
	void precioSuperando70() {
		
		when(c.volumen()).thenReturn(90d); 
		
		assertEquals(200, s.getPrecio(c), 1);
	}
	
	@Test
	void precioDebajo70() {
		
		when(c.volumen()).thenReturn(50d); 
		
		assertEquals(100, s.getPrecio(c), 1);
	}
	

}
