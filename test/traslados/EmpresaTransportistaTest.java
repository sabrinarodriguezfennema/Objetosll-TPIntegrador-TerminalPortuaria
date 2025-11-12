package traslados;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import interfaces.IContainer;
import traslado.EmpresaTransportista;

class EmpresaTransportistaTest {

	private EmpresaTransportista empresa;
	private IContainer mockContainer;

	@BeforeEach
	void setUp() {

		empresa = new EmpresaTransportista("Transporte XYZ");
		mockContainer = mock(IContainer.class);
	}

	@Test
	void testAgregarYVerificarCamion() {
		empresa.agregarCamion("ABC123");

		assertTrue(empresa.perteneceCamion("ABC123"));
		assertEquals(1, empresa.cantidadDeCamiones());
	}

	@Test
	void testAgregarYVerificarChofer() {
		empresa.agregarChofer("12345678");

		assertTrue(empresa.perteneceChofer("12345678"));
		assertEquals(1, empresa.cantidadDeChoferes());
	}

	@Test
	void testAsignarCamionParaDisponible() {
		empresa.agregarCamion("ABC123");
		String camionAsignado = empresa.asignarCamionPara(mockContainer);

		assertEquals("ABC123", camionAsignado);
	}

	@Test
	void testAsignarChoferParaDisponible() {
		empresa.agregarChofer("12345678");
		String choferAsignado = empresa.asignarChoferPara(mockContainer);

		assertEquals("12345678", choferAsignado);

	}

	@Test
	void testAsignarCamionCuandoNoHay() {
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			empresa.asignarCamionPara(mockContainer);
		});
		assertEquals("No hay camiones disponibles", exception.getMessage());
	}

	@Test
	void testAsignarChoferCuandoNoHay() {
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			empresa.asignarChoferPara(mockContainer);
		});
		assertEquals("No hay choferes disponibles", exception.getMessage());
	}

	@Test
	void testAgregarMultiplesCamionesYChoferes() {
		empresa.agregarCamion("CAM1");
		empresa.agregarCamion("CAM2");
		empresa.agregarChofer("CHO1");
		empresa.agregarChofer("CHO2");

		String camion1 = "CAM1";
		String chofer1 = "CHO1";

		assertEquals(2, empresa.cantidadDeCamiones());
		assertEquals(2, empresa.cantidadDeChoferes());
	}
}
