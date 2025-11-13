package facturacion;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.*;

public class ItemDesgloseTest {
	
	ItemDesglose itd;
	LocalDateTime fecha;
	@BeforeEach
	void setUp() {
		
		fecha = LocalDateTime.of(2025, 11, 9, 0, 0, 0, 0);
		
		itd = new ItemDesglose("servicio",500,fecha); 
		
	}
	
	@Test
	void obtenerMonto() {
		assertEquals(500, itd.getMonto(),1);
	}
	
	@Test
	void obtenerDetalle() {
		assertEquals("servicio", itd.getDetalle());
	}
	
	@Test
	void obtenerFecha() {
		assertEquals(fecha, itd.getFecha());
	}

}
