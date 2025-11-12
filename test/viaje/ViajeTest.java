package viaje;

import static org.junit.Assert.*;


import static org.mockito.Mockito.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.*;

import interfaces.Buque;
import interfaces.Circuito;
import interfaces.Terminal;
import interfaces.Tramo;

public class ViajeTest {
	 
	Viaje viaje;
	Buque buque;
	Circuito circuito;
	LocalDate fechaInicio;
	Tramo tramo1;
    Tramo tramo2;
    Terminal t1;
    Terminal t2;
    Terminal t3;
	
	@BeforeEach
	void setup() {
		
		fechaInicio = LocalDate.of(2025, 11, 10);
		circuito = mock(Circuito.class);
	
		tramo1 = mock(Tramo.class);
	    tramo2 = mock(Tramo.class);
	    t1 = mock(Terminal.class);
	    t2 = mock(Terminal.class);
	    t3 = mock(Terminal.class);
	    
	    
	    viaje = new Viaje(fechaInicio, buque, circuito);
	}
	
	@Test
    void obtenerCircuito() {
		assertEquals(circuito, viaje.getCircuito());
	}
	
	@Test
    void obtenerBuque() {
		assertEquals(buque, viaje.getBuque());
	}
	
	@Test
    void obtenerFechaInicio() {
		assertEquals(fechaInicio, viaje.getFechaInicio());
	}
	
	@Test
	void obtenerCronograma() {
		
		  when(tramo1.getOrigen()).thenReturn(t1);
	      when(tramo1.getDestino()).thenReturn(t2);
	      when(tramo1.getDuracion()).thenReturn(Duration.ofDays(2));

	      when(tramo2.getOrigen()).thenReturn(t2);
	      when(tramo2.getDestino()).thenReturn(t3);
	      when(tramo2.getDuracion()).thenReturn(Duration.ofDays(3));

	      when(circuito.getTramos()).thenReturn(List.of(tramo1, tramo2));
	      
	      Map<Terminal, LocalDate> cronograma = viaje.cronograma();

	      assertEquals(3, cronograma.size());
	      assertEquals(fechaInicio, cronograma.get(t1));  
	      assertEquals(fechaInicio.plusDays(2), cronograma.get(t2)); 
	      assertEquals(fechaInicio.plusDays(5), cronograma.get(t3));  
	}
	
	@Test
	void montoViaje() {
		
		when(viaje.precioTotal()).thenReturn(500.00);
		assertEquals(500.00, viaje.precioTotal(),1);
	}

}
