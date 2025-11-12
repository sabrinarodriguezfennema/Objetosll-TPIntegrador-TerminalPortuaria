package cliente;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import clientes.Shipper;
import interfaces.IFactura;

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
		assertTrue(shipper.getFacturasRecibidas().isEmpty());
	}
	
	@Test
    void testRecibirMailAgregaElMensajeALaLista() {
        shipper.recibirMail("Mensaje");

        List<String> mails = shipper.getMailsRecibidos();

        assertEquals(1, mails.size());
        assertEquals("Mensaje", mails.get(0));
    }
	
	@Test
	void testFechaDeExportacion() {
		
		Shipper shipper = new Shipper("Juan", "juan@mail.com");
	    LocalDateTime turno = LocalDateTime.now();
	    
	    shipper.fechaDeExportacion(turno);
	    
	    assertEquals(turno, shipper.getFechaDeExportacion());
	}
	
	@Test
    void testRecibirFacturaAgregaFacturaALaLista() {
        IFactura mockFactura = mock(IFactura.class);

        shipper.recibirFactura(mockFactura);

        List<IFactura> facturas = shipper.getFacturasRecibidas();

        assertEquals(1, facturas.size());
        assertEquals(mockFactura, facturas.get(0));
    }
	
}
