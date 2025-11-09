package cliente;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import clientes.Shipper;

class ShipperTest {

	private Shipper shipper;
	
	@BeforeEach
	void setUp() {
		shipper = new Shipper("Juan", "juan@mail.com");
	}

	@Test
	void testShipperConstructor() {
		
		assertEquals("Juan", shipper.getNombre());
		assertEquals("juan@mail.com", shipper.getEmail());
		assertTrue(shipper.getMailsRecibidos().isEmpty());
	}
	
	@Test
	void testShipperRecibirMail() {

		shipper.recibirMail("Mensaje 1");
		
	    assertEquals(1, shipper.getMailsRecibidos().size());
	    assertEquals("Mensaje 1", shipper.getMailsRecibidos().get(0));
	}
	
	@Test
	void testFechaDeExportacion() {
		
		Shipper shipper = new Shipper("Juan", "juan@mail.com");
	    LocalDateTime turno = LocalDateTime.now();
	    
	    shipper.fechaDeExportacion(turno);
	    
	    assertEquals(turno, shipper.getFechaDeExportacion());
	    
	    
	}
	
}
