package rutasMaritimas;

import org.junit.jupiter.api.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

public class NavieraTest {
	
	Naviera naviera;
	
	Viaje v1;
	Viaje v2;
	Viaje v3;
	
	Circuito c1;
	Circuito c2;
	Circuito c3;
	
	Buque b1;
	Buque b2;
	Buque b3;
	
	LocalDate f1;
	LocalDate f2;
	LocalDate f3;
	
	@BeforeEach
	void setUp() {
		
		naviera = new Naviera();
		
		f1 = mock(LocalDate.class);
		f2 = mock(LocalDate.class);
		f3 = mock(LocalDate.class);
		
		c1 = mock(Circuito.class);
		c2 = mock(Circuito.class);
		c3 = mock(Circuito.class);
		
		v1 = mock(Viaje.class);
		v2 = mock(Viaje.class);
		v3 = mock(Viaje.class);
		
		b1 = mock(Buque.class);
		b2 = mock(Buque.class);
		b3 = mock(Buque.class);
		
	}
	
	@Test 
	void agregarViajeYVerificarSiEsta() {
		
		naviera.agregarViaje(v1);
		naviera.agregarViaje(v2);
		naviera.agregarViaje(v3);
		
		assertEquals(List.of(v1,v2,v3), naviera.getViajes());
		
	}
	
	@Test 
	void agregarBuqueYVerificarSiEsta() {
		
		naviera.agregarBuque(b1);
		naviera.agregarBuque(b2);
		naviera.agregarBuque(b3);
		
		assertEquals(List.of(b1,b2,b3), naviera.getBuques());
		
	}
	
	@Test 
	void agregarCircuitoYVerificarSiEsta() {
		
		naviera.agregarCircuito(c1);
		naviera.agregarCircuito(c2);
		naviera.agregarCircuito(c3);
		
		assertEquals(List.of(c1,c2,c3), naviera.getCircuitos());
		
	}
	
	@Test
	void obtenerFechaDeCadaViaje() {
				
		naviera.agregarViaje(v1);
		naviera.agregarViaje(v2);
		naviera.agregarViaje(v3);
		
		when(v1.getFechaInicio()).thenReturn(f1);
		when(v2.getFechaInicio()).thenReturn(f2);
		when(v3.getFechaInicio()).thenReturn(f3);
		
		assertEquals(List.of(f1,f2,f3),naviera.getFechaDeViajes());
		
		
	}
	
	

}
