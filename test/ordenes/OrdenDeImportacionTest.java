package ordenes;

import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import clientes.Cliente;
import interfaces.Container;
import interfaces.ICliente;
import interfaces.IConsignee;
import interfaces.IFactura;
import ordenes.OrdenDeImportacion;
import servicios.Servicio;
import terminal.TerminalGestionada;

class OrdenDeImportacionTest {

	private TerminalGestionada mockTerminalGestionada;
	private IConsignee mockCliente;
	private Container mockContainer;
	private String patenteCamion;
	private String dniChofer;
	private IFactura mockFactura;
	private OrdenDeImportacion orden;
	private LocalDateTime llegada;
	private LocalDateTime retiro;

	@BeforeEach
	void setUp() {

		mockTerminalGestionada = mock(TerminalGestionada.class);

		mockContainer = mock(Container.class);
		mockFactura = mock(IFactura.class);

		patenteCamion = "ABC123";
		dniChofer = "12345678";

		llegada = LocalDateTime.of(2025, 10, 28, 10, 0);
		retiro = LocalDateTime.of(2025, 10, 29, 11, 0);

		orden = new OrdenDeImportacion(mockCliente, mockContainer, patenteCamion, dniChofer, llegada);
	}

	@Test
	void testGetDatosDeCarga() {
		Container resultado = orden.getDatosDeCarga();

		assertEquals(mockContainer, resultado);
	}

	@Test
	void testGetDNIChofer() {
		String chofer = orden.getChofer();

		assertEquals("12345678", chofer);
	}

	@Test
	void testGetPatenteCamion() {
		String camion = orden.getCamion();

		assertEquals("ABC123", camion);
	}

	@Test
	void testVerificarAutorizacionTrue() {

		assertTrue(orden.verificarAutorizacion(patenteCamion, dniChofer));
	}

	@Test
	void testVerificarAutorizacionFalse() {
		String otraPatente = "XYZ789";
		String otroDni = "87654321";

		assertFalse(orden.verificarAutorizacion(otraPatente, otroDni));
	}

	@Test
	void testRegistrarEn_deberiaLlamarAlMetodoRegistrar() {
		orden.registrarEn(mockTerminalGestionada);

		verify(mockTerminalGestionada, times(1)).registrar(orden);
	}

	@Test
	void testGetConsignee_deberiaRetornarElClienteCorrecto() {
		ICliente resultado = orden.getCliente();

		assertEquals(mockCliente, resultado);
	}

}
