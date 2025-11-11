package containers;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;

import interfaces.BillOfLading;
import interfaces.Consignee;

public class ContainerReeferTest {
	
	ContainerReefer c; 
	BillOfLading bl;
	Consignee consignee;
	
	@BeforeEach
	void setUp() {
		bl = mock(BillOfLading.class);
		consignee = mock(Consignee.class);
		c = new ContainerReefer(10,5,30,"ABCD",1234567, bl, consignee,50); 
		
	}
	
	@Test
	void obtenerTipo() {
		assertEquals("Reefer", c.tipo());
	}
	
	@Test
	void obtenerKWPorHora() { 
		assertEquals(50,c.getkwPorHora());
	}
}
