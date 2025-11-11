package facturacion;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

<<<<<<< HEAD
=======
import java.util.HashSet;
import java.util.List;
>>>>>>> branch 'main' of https://github.com/sabrinarodriguezfennema/Objetosll-TPIntegrador-TerminalPortuaria.git
import java.util.Set;

import org.junit.jupiter.api.*;

import servicios.Servicio;

public class FacturaTest {
	
	Factura factura; 
	Desglose desglose;
<<<<<<< HEAD
	Set<Servicio> servicios;
=======
	private Set<Servicio> servicios;
>>>>>>> branch 'main' of https://github.com/sabrinarodriguezfennema/Objetosll-TPIntegrador-TerminalPortuaria.git
	
<<<<<<< HEAD
	@BeforeEach
	void setUp() {
		
		desglose = mock(Desglose.class);		
		factura = new Factura(servicios);		
	}
	
=======
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
>>>>>>> branch 'main' of https://github.com/sabrinarodriguezfennema/Objetosll-TPIntegrador-TerminalPortuaria.git
	@Test 
	void obtenerMontoTotal() {
			
        when(desglose.montoTotal()).thenReturn(500);
		assertEquals(500, factura.montoTotal());		
		verify(desglose, times(1)).montoTotal();
	}

}
