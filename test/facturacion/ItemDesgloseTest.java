package facturacion;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.*;

public class ItemDesgloseTest {
	
	ItemDesglose itd;
	LocalDate fecha;
	@BeforeEach
	void setUp() {
		
		fecha = LocalDate.of(2025, 11, 9);
		
		itd = new ItemDesglose("servicio",500,fecha); 
		
	}
	
	@Test
	void obtenerMonto() {
		assertEquals(500, itd.getMonto());
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
