package facturacion;

import static org.junit.Assert.*;



import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.*;

import containers.Container;
import servicios.Servicio;

public class FacturaTest {
	
    private Factura factura; 
    private Desglose desglose;
    Container c;
    Servicio s;

	private Set<Servicio> servicios;
	@BeforeEach	void setUp() {
		 		servicios = new HashSet<Servicio>();		s = mock(Servicio.class); 
		c = mock(Container.class);
		
		servicios.add(s);		when(s.getPrecio(c)).thenReturn(500.00);		desglose = mock(Desglose.class);				factura = new Factura(servicios);			}
     //Para cuando Factura ya ande
	
	//@Test 
	//void obtenerMontoTotal() {
	//		
    //    when(desglose.montoTotal()).thenReturn(500);
	//	assertEquals(500, factura.montoTotal());		
	//	verify(desglose, times(1)).montoTotal();
	//}
	
	@Test 
	void agregarServicio() {
		factura.agregarServicio(s);
		assertTrue(servicios.contains(s));
	}
}
