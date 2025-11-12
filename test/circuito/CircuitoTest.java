package circuito;

import static org.junit.Assert.*;



import static org.mockito.Mockito.*;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.*;

import interfaces.ITerminal;
import interfaces.ITramo;


public class CircuitoTest {
	
	Circuito circuito;
	ITramo t1;
	ITramo t2;
	ITramo t3;
	ITramo t4;
	ITerminal ter1;
	ITerminal ter2;
	ITerminal ter3;
	ITerminal ter4;
	
	@BeforeEach
	void setUp() {
		
		circuito = new Circuito();
		t1 = mock(ITramo.class);
		t2 = mock(ITramo.class);
		t3 = mock(ITramo.class);
		t4 = mock(ITramo.class); 
		
		ter1 = mock(ITerminal.class);
		ter2 = mock(ITerminal.class);
		ter3 = mock(ITerminal.class);
		ter4 = mock(ITerminal.class);

		when(t1.getOrigen()).thenReturn(ter1);
		when(t1.getDestino()).thenReturn(ter2);
		when(t2.getOrigen()).thenReturn(ter2);
		when(t2.getDestino()).thenReturn(ter3);
		when(t3.getOrigen()).thenReturn(ter3);
		when(t3.getDestino()).thenReturn(ter4);
		
		circuito.agregarTramo(t1);
		circuito.agregarTramo(t2);
		circuito.agregarTramo(t3);
		
	}
	
	@Test
	void agregarTramoYPreguntar() {	
		
		assertEquals(List.of(t1,t2,t3),circuito.getTramos());
	}
	
	@Test
	void obtenerPrecioTotal() {
		
		when(t1.getPrecio()).thenReturn(50);
		when(t2.getPrecio()).thenReturn(100);
		when(t3.getPrecio()).thenReturn(150);
		
		assertEquals(300, circuito.precioTotal());
	}
	
	@Test
    void obtenerDuracionTotal() {
		
		when(t1.getDuracion()).thenReturn(Duration.ofMinutes(200));
		when(t2.getDuracion()).thenReturn(Duration.ofMinutes(100));
		when(t3.getDuracion()).thenReturn(Duration.ofMinutes(300));
		
		assertEquals(Duration.ofMinutes(600), circuito.duracionTotal());
	}
	
	@Test
	void siElTramoEstaObtieneSuPrecio() {
		
		when(t1.getPrecio()).thenReturn(55);
		
		assertEquals(55,circuito.precioDe(t1));
	}
	
	@Test
	void siElTramoEstaObtieneSuDuracion() {
		
		when(t1.getDuracion()).thenReturn(Duration.ofMinutes(150));
		
		assertEquals(Duration.ofMinutes(150),circuito.duracionDe(t1));
	}
	
	@Test
	void lanzaExcepcionSiElTramoNoPertenece() {
	    IllegalArgumentException exception = assertThrows(
	        IllegalArgumentException.class,
	        () -> circuito.precioDe(t4));

	    assertEquals("El tramo no pertenece al circuito", exception.getMessage());
	}
	
	@Test
	void lanzaExcepcionSiNoEsUnTramoValidoParaAgregar() {
	    IllegalArgumentException exception = assertThrows(
	        IllegalArgumentException.class,
	        () -> circuito.agregarTramo(t2));

	    assertEquals("el tramo no es el mismo que el origen", exception.getMessage());
	}
	
	@Test
	void cantidadDeTerminales() {
		
		assertEquals(4,circuito.cantidadDeTerminales());
	}
	
	@Test
	void todasLasTerminales() {
	
		assertEquals(List.of(ter1,ter2,ter3,ter4), circuito.getTodasLasTerminales());
	}
	
	@Test
	void todasLasTerminalesDestino() {
            
		assertEquals(List.of(ter2,ter3,ter4), circuito.terminalesDestino());
	}
	
}
