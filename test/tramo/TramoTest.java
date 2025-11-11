package tramo;

import static org.junit.Assert.*;

import java.time.Duration;

import org.junit.jupiter.api.*;

import interfaces.Terminal;

public class TramoTest {
	
	Tramo tramo;
	Duration duracion;
	int precio;
	Terminal origen;
	Terminal destino;
	
	@BeforeEach
	void setup() {
	
		tramo = new Tramo(origen,destino,duracion,precio);
	}
	
	@Test
    void obtenerDuracion() {
		assertEquals(duracion, tramo.getDuracion());
	}
	
	@Test
    void obtenerPrecio() {
		assertEquals(precio, tramo.getPrecio());
	}
	
	@Test
	void obtenerTerminalOrigen() {
		assertEquals(origen, tramo.getOrigen());
	}
	
	@Test
	void obtenerTerminalDestino() {
		assertEquals(destino, tramo.getDestino());
	}

}
