package containers;

import static org.junit.Assert.*;

import org.junit.jupiter.api.*;


public class ConteinerDryTest {
	
	ContainerDry c;
	
	@BeforeEach
	void setUp() {
		
		c = new ContainerDry(10, 5, 30, "ABCD", 1234567, null, null);
	}
	
	@Test
	void obtenerTipo() {
		assertEquals("Dry", c.tipo());
	}
	
//	@Test
//	void puedeAgregarServicioEspecial() {
//		
//		when(bl.tieneMasDeUnImportador()).thenReturn(true);
//		c.agregarServicioEspecial(s);
//		assertEquals(List.of(s), c.getServicios());
//	}
	
//	@Test
//	void errorAlAgregarServicioEspecial() {
//		
//		when(bl.tieneMasDeUnImportador()).thenReturn(false);
//		IllegalArgumentException exception = assertThrows(
//		        IllegalArgumentException.class, 
//		        () -> c.agregarServicioEspecial(s));
//		assertEquals("Solo tiene un importador", exception.getMessage());
//			
//		assertEquals(List.of(), c.getServicios());
//	}
}
