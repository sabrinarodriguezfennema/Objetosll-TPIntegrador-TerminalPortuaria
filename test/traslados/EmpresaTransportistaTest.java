package traslados;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import traslado.EmpresaTransportista;

class EmpresaTransportistaTest {
	
	private EmpresaTransportista empresa;

	@BeforeEach
	void setUp() {
		empresa = new EmpresaTransportista("TransporteTerminal");
	}
	
	 @Test
	 void testAgregarCamion() {
		 
		 empresa.agregarCamion("ABC123");
		 
		 assertTrue(empresa.perteneceCamion("ABC123"));
	 }
	 
	@Test
	void testAgregarChofer() {
		
		empresa.agregarChofer("40555999");
		
		assertTrue(empresa.perteneceChofer("40555999"));

	}

	@Test
	void testNoDuplicados() {
		empresa.agregarCamion("ABC123");
		empresa.agregarCamion("ABC123");

		assertEquals(1, empresa.cantidadDeCamiones());

		empresa.agregarChofer("40555999");
		empresa.agregarChofer("40555999");
		
		assertEquals(1, empresa.cantidadDeChoferes());
	    }
}

