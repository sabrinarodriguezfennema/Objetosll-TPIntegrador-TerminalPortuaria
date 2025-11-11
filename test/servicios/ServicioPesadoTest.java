package servicios;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;

import containers.Container;

public class ServicioPesadoTest {

	ServicioPesado s;
	Container c;
	
	@BeforeEach 
	void setUp() {
		
		s = new ServicioPesado(200);
		c = mock(Container.class);
	}
	
	@Test
	void precioDelServicio() {
		
		assertEquals(200, s.getPrecio(c), 1);
	}
	
	
}
