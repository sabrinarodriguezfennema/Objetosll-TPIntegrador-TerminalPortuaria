package cliente;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import clientes.Consignee;


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
	void testRecibirMailConsignee() {

		consignee.recibirMail("Mensaje 1");
		
		assertEquals(1, consignee.getMailsRecibidos().size());
		assertEquals("Mensaje 1", consignee.getMailsRecibidos().get(0));
	}

}
