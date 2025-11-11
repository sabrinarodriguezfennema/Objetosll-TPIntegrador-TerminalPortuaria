package buque;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buque.Buque;
import containers.Container;
import reportes.Reporte;

public class BuqueTest {
	
	Buque buque; 
	Container c1;
	Container c2;
	
	@BeforeEach
	void setUp() {
		
		buque = new Buque("nico");
		c1 = mock(Container.class);
		c2 = mock(Container.class);
	}
	
	@Test
	void agregandoContainersYpreguntarPorEl() {
		
		buque.addContainer(c1);
		buque.addContainer(c2);
		assertEquals(List.of(c1, c2), buque.getContainers());
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
		
		buque.eliminarContainers();
		
		assertEquals(List.of(),buque.getContainers());
		
	}
	

}
