package rutasMaritimas;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.jupiter.api.*;

public class ViajeTest {
	 
	Viaje viaje;
	Buque buque;
	Circuito circuito;
	LocalDate fecha;
	
	@BeforeEach
	void setup() {
	
		viaje = new Viaje(fecha, buque, circuito);
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
		assertEquals(fecha, viaje.getFechaInicio());
	}

}
