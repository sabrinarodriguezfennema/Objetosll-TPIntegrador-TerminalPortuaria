package containers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;

public class ConteinerReeferTest {
	
	ContainerReefer c; 
	
	@BeforeEach
	void setUp() {
		c = mock(ContainerReefer.class);
	}
	
	@Test
	void obtenerKWPorHora() { 
		
		when(c.getkwPorHora()).thenReturn(50); 
		assertEquals(50,c.getkwPorHora());
		verify(c, times(1)).getkwPorHora();
	}
	
	
	

}
