package servicios;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;

import containers.Container;

public class ServicioDesconsolidadoTest {
	
	ServicioDesconsolidado s;
	Container c;
	
	@BeforeEach 
	void setUp() { 
		c = mock(Container.class);
		s = new ServicioDesconsolidado(25);
	}
	
	@Test
	void obtenerPrecio() {
		
		assertEquals(25, s.getPrecio(c));
	}
}
