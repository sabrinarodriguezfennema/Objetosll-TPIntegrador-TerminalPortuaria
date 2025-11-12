package containers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;

import interfaces.BillOfLading;
import interfaces.IConsignee;

public class ContainerTanqueTest {
	
	ContainerTanque c;
	BillOfLading bl;
	IConsignee consignee;
	
	
	@BeforeEach
	void setUp() {
		bl = mock(BillOfLading.class);
		consignee = mock(IConsignee.class);
		
		c = new ContainerTanque(10, 5, 30, "ABCD", 1234567, bl, consignee);
	}
	
	@Test
	void obtenerTipo() {
		assertEquals("Tanque", c.getTipo());
	}
	
	@Test 
	void obtenerAltura() {
		assertEquals(10, c.getAltura(), 1);	
	}
	
	@Test
	void obtenerAncho() {
		assertEquals(5, c.getAncho(), 1);	
	}
	
	@Test 
	void obtenerLargo() {
		assertEquals(30, c.getLargo(), 1);	
	}
	
	@Test
	void obtenerIdentificador() {
		assertEquals("ABCD1234567", c.getId());
	}
	
	@Test
	void obtenerBL() {
		assertEquals(bl, c.getBL());
	}
	
	@Test
	void obtenerDueño() {
		assertEquals(consignee, c.getDueño());
	}
	
	@Test
	void obtenerVolumen() {
		assertEquals(1500, c.volumen(), 1);
	}
	
	@Test
	void obtenerPeso() {
		when(bl.peso()).thenReturn(500.00);
		assertEquals(500, c.getPeso(),1 );
	}
}
