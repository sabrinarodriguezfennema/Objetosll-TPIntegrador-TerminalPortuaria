package facturacion;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.util.Set;

import org.junit.jupiter.api.*;

import servicios.Servicio;

public class FacturaTest {
	
	Factura factura; 
	Desglose desglose;
	Set<Servicio> servicios;
	
	@BeforeEach
	void setUp() {
		
		desglose = mock(Desglose.class);		
		factura = new Factura(servicios);		
	}
	
	@Test 
	void obtenerMontoTotal() {
			
        when(desglose.montoTotal()).thenReturn(500);
		assertEquals(500, factura.montoTotal());		
		verify(desglose, times(1)).montoTotal();
	}

}
