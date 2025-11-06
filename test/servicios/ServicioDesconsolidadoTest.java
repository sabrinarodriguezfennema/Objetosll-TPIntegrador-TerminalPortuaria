package servicios;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;

import containers.Container;

public class ServicioDesconsolidadoTest {
	
	Servicio s;
	Container c;
	
	@BeforeEach 
	void setUp() { 
		c = mock(Container.class);
		s = mock(ServicioDesconsolidado.class);
	}
	
	@Test
	void obtenerPrecio() {
		
		when(s.getPrecio(c)).thenReturn(25);
		assertEquals(25, s.getPrecio(c));
		verify(s, times(1)).getPrecio(c);
	}

}
