package buque;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import interfaces.IContainer;

public class BuqueTest {
	
	Buque buque; 
	IContainer c1;
	IContainer c2;
	
	@BeforeEach
	void setUp() {
		
		buque = new Buque("nico");
		c1 = mock(IContainer.class);
		c2 = mock(IContainer.class);
	}
 
	@Test
	void agregandoContainersYpreguntarPorEl() {
		
		buque.addContainer(c1);
		buque.addContainer(c2);
		assertEquals(Set.of(c1, c2), buque.getContainers());
	}
	
	@Test
	void obtenerNombre() {
	      
		//when(buque.getNombre()).thenReturn("nico");
		assertEquals("nico", buque.getNombre());
	}
	
	@Test
	void eliminarCarga() {
		
		buque.addContainer(c1);
		buque.addContainer(c2);
		
		buque.removeContainer(c1);
		
		assertEquals(Set.of(c2),buque.getContainers());
		
	}
	

}
