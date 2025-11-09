package servicios;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;

import containers.Container;

public class ServicioRevisionDiariaTest {
	
	ServicioRevisionDiaria s;
	Container c;
	
	@BeforeEach 
	void setUp() { 
		c = mock(Container.class);
		s = new ServicioRevisionDiaria(50, 10);
	}
	
	@Test
	void obtenerPrecio() {
		assertEquals(500, s.getPrecio(c));
	}

}
