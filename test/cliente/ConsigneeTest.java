package cliente;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import clientes.Consignee;
import interfaces.IFactura;


class ConsigneeTest {
	
	private Consignee consignee;
	
	@BeforeEach
	void setUp() {
	    consignee = new Consignee("Ana", "ana@mail.com");
	}

	
	@Test
	void testConsigneeConstructor() {
		
		assertEquals("Ana", consignee.getNombre());
		assertEquals("ana@mail.com", consignee.getEmail());
		assertTrue(consignee.getMailsRecibidos().isEmpty());
		
	}
	
	@Test
    void testRecibirMailAgregaElMensajeALaLista() {
        consignee.recibirMail("Mensaje");

        List<String> mails = consignee.getMailsRecibidos();

        assertEquals(1, mails.size());
        assertEquals("Mensaje", mails.get(0));
    }
	
	 @Test
	    void testRecibirFacturaAgregaFacturaALaLista() {
	        IFactura mockFactura = mock(IFactura.class);
	        consignee.recibirFactura(mockFactura);
	        
	        List<IFactura> facturas = consignee.getFacturasRecibidas();
	        
	        assertEquals(1, facturas.size());
	        assertEquals(mockFactura, facturas.get(0));
	    }

}
