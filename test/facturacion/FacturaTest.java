package facturacion;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.*;

import servicios.Servicio;

public class FacturaTest {
	
	Factura factura; 
	Desglose desglose;
	private Set<Servicio> servicios;
	
//	@BeforeEach
//	void setUp() {
//		servicios = new HashSet<Servicio>();
//		Servicio s = mock(Servicio.class);
//		servicios.add(s);
//		when(s.getPrecio()).thenReturn(500);
//		desglose = mock(Desglose.class);		
//		factura = new Factura(servicios);		
//	}
//Para cuando Factura ya ande
	@Test 
	void obtenerMontoTotal() {
			
        when(desglose.montoTotal()).thenReturn(500);
		assertEquals(500, factura.montoTotal());		
		verify(desglose, times(1)).montoTotal();
	}

}
