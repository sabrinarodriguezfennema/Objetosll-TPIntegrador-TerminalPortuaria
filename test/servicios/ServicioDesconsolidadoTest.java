package servicios;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;

import interfaces.IContainer;

public class ServicioDesconsolidadoTest {
	
	ServicioDesconsolidado s;
	IContainer c;
	
	@BeforeEach 
	void setUp() { 
		c = mock(IContainer.class);
		s = new ServicioDesconsolidado(25);
	}
	
	@Test
	void obtenerPrecio() {
		
		assertEquals(25, s.getPrecio(c), 1);
	}
}
