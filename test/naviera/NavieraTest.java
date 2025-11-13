package naviera;

import org.junit.jupiter.api.*;

import interfaces.Buque;
import interfaces.IBuque;
import interfaces.ICircuito;
import interfaces.IViaje;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class NavieraTest {
	
	Naviera naviera;
	
	IViaje v1;
	IViaje v2;
	IViaje v3;
	
	ICircuito c1;
	ICircuito c2;
	ICircuito c3;
	
	IBuque b1;
	IBuque b2;
	IBuque b3;
	
	LocalDateTime f1;
	LocalDateTime f2;
	LocalDateTime f3;
	
	@BeforeEach
	void setUp() {
		
		naviera = new Naviera();
		
		f1 = mock(LocalDateTime.class);
		f2 = mock(LocalDateTime.class);
		f3 = mock(LocalDateTime.class);
		
		c1 = mock(ICircuito.class);
		c2 = mock(ICircuito.class);
		c3 = mock(ICircuito.class);
		
		v1 = mock(IViaje.class);
		v2 = mock(IViaje.class);
		v3 = mock(IViaje.class);
		
		b1 = mock(IBuque.class);
		b2 = mock(IBuque.class);
		b3 = mock(IBuque.class);
		
	}
	
	@Test 
	void agregarViajeYVerificarSiEsta() {
		
		naviera.agregarViaje(v1);
		naviera.agregarViaje(v2);
		naviera.agregarViaje(v3);
		
		assertEquals(Set.of(v1, v2, v3), naviera.getViajes());
		
	}
	
	@Test 
	void agregarBuqueYVerificarSiEsta() {
		
		naviera.agregarBuque(b1);
		naviera.agregarBuque(b2);
		naviera.agregarBuque(b3);
		
		assertEquals(Set.of(b1,b2,b3), naviera.getBuques());
		
	}
	
	@Test 
	void agregarCircuitoYVerificarSiEsta() {
		
		naviera.agregarCircuito(c1);
		naviera.agregarCircuito(c2);
		naviera.agregarCircuito(c3);
		
		assertEquals(Set.of(c1,c2,c3), naviera.getCircuitos());
		
	}
	
	@Test
	void obtenerFechaDeCadaViaje() {
				
		naviera.agregarViaje(v1);
		naviera.agregarViaje(v2);
		naviera.agregarViaje(v3);
		
		when(v1.fechaSalida()).thenReturn(f1);
		when(v2.fechaSalida()).thenReturn(f2);
		when(v3.fechaSalida()).thenReturn(f3);
		
		assertEquals(Set.of(f1,f2,f3),naviera.getFechaDeViajes());
		
		
	}
	
	

}
