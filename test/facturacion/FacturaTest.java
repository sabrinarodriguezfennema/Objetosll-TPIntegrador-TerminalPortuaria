package facturacion;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;

public class FacturaTest {
	
	Factura factura; 
	Desglose desglose;
	
	@BeforeEach
	void setUp() {
		
		desglose = mock(Desglose.class);		
//		factura = new Factura(desglose);		
	}
	
	@Test 
	void obtenerMontoTotal() {
			
        when(desglose.montoTotal()).thenReturn(500);
		assertEquals(500, factura.montoTotal());		
		verify(desglose, times(1)).montoTotal();
	}

}
