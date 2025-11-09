package containers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;

public class ConteinerReeferTest {
	
	ContainerReefer c; 
	
	@BeforeEach
	void setUp() {
		c = new ContainerReefer(0,0,0,null,0,null,null,null,50); 
		
	}
	
	@Test
	void obtenerKWPorHora() { 
		assertEquals(50,c.getkwPorHora());
	}
}
