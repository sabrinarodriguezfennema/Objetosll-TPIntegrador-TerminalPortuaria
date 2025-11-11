package containers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

import bl.BillOfLading;
import clientes.Consignee;
import servicios.Servicio;
import servicios.ServicioDesconsolidado;

public class ConteinerDryTest {
	
	ServicioDesconsolidado s; 
	ContainerDry c; 
	BillOfLading bl;
	Consignee consignee;
	List<Servicio> servicios = new ArrayList<>();
	
	@BeforeEach
	void setUp() {
		
		s = mock(ServicioDesconsolidado.class);
		bl = mock(BillOfLading.class);
		c = new ContainerDry(10, 5, 30, "ABCD", 1234567, bl, consignee);
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
