package rutasMaritimas;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import containers.Container;

public class BuqueTest {
	
	Buque buque;
	Container c1;
	
	@BeforeEach
	void setUp() {
		
		buque = new Buque("nico");
		c1 = mock(Container.class);
		
	}
	
	@Test
	void agregandoContainers() {
		buque.addContainer(c1);
		
		assertTrue(buque.getContainers().contains(c1));
		
	}
	
	@Test
	void obtenerNombre() {
	      
		assertEquals("nico", buque.getNombre());
	}
	

}
