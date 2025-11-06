package containers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

import servicios.Servicio;

public class ContainerTest {
	
	Container c; 
	BillOfLading bl; 
	Consignee consignee;
	List<Servicio> servicios = new ArrayList<>();
	Servicio s1;
	Servicio s2;
	
	@BeforeEach
	void setUp() {
		
		bl = mock(BillOfLading.class);
		consignee = mock(Consignee.class);
		s1 = mock(Servicio.class);
		s2 = mock(Servicio.class);
		c = new Container(10, 5, 30, "ABCD", 1234567, bl, consignee, servicios);
	}
	
	@Test 
	void obtenerAltura() {
		assertEquals(10, c.getAltura());	
	}
	
	@Test
	void obtenerAncho() {
		assertEquals(5, c.getAncho());	
	}
	
	@Test 
	void obtenerLargo() {
		assertEquals(30, c.getLargo());	
	}
	
	@Test
	void obtenerIdentificador() {
		assertEquals("ABCD1234567", c.getIdentificador());
	}
	
	@Test
	void obtenerBL() {
		assertEquals(bl, c.getBL());
	}
	
	@Test
	void obtenerConsignee() {
		assertEquals(consignee, c.getConsignee());
	}
	
	@Test
	void obtenerVolumen() {
		assertEquals(1500, c.volumen());
	}
	
	@Test
	void obtenerPeso() {
		when(bl.pesoTotal()).thenReturn(500);
		assertEquals(500, c.getPeso());
	}
	
	@Test
	void agregarServicioYVerificarSiSeAgrego() {
		
		c.agregarServicio(s1);
		c.agregarServicio(s2);
		
		assertEquals(List.of(s1,s2), c.getServicios());
	}
	
	@Test
	void precioDeTodosLosServicios() {
		
		when(s1.getPrecio(c)).thenReturn(100);
		when(s2.getPrecio(c)).thenReturn(200);
		
		c.agregarServicio(s1);
		c.agregarServicio(s2);
		
		assertEquals(300, c.totalDeLosServicios());
	}
	
	

}
