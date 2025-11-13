package containers;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;

import bl.BillOfLading;
import interfaces.IConsignee;

public class ContainerReeferTest {


	private ContainerReefer c;
	private BillOfLading bl;
	private IConsignee consignee;

	@BeforeEach
	void setUp() {

		bl = mock(BillOfLading.class);
		consignee = mock(IConsignee.class);

		c = new ContainerReefer(2.59, 2.44, 12.19, "ABCD", 123456, bl, consignee, 50.00);
	}


	@Test
	void obtenerTipo() {
		assertEquals("Reefer", c.getTipo());
	}
	
	@Test 
	void obtenerAltura() {
		assertEquals(2.59, c.getAltura(), 1);	
	}
	
	@Test
	void obtenerAncho() {
		assertEquals(2.44, c.getAncho(), 1);	
	}
	
	@Test 
	void obtenerLargo() {
		assertEquals(12.19, c.getLargo(), 1);	
	}
	
	@Test
	void obtenerIdentificador() {
		assertEquals("ABCD123456", c.getId());
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
		assertEquals(12.19 * 2.44 * 2.59, c.volumen(), 1);
	}
	
	@Test
	void obtenerPeso() {
		when(bl.peso()).thenReturn(500.00);
		assertEquals(500, c.getPeso(),1 );
	}
	

}
