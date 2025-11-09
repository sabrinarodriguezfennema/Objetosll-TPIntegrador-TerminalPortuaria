package ordenes;

import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import interfaces.Container;
import interfaces.Consignee;
import interfaces.Factura;
import ordenes.OrdenDeImportacion;
import terminal.TerminalGestionada;

class OrdenDeImportacionTest {
	
	private TerminalGestionada mockTerminalGestionada;
	private Consignee mockCliente;
	private Container mockContainer;
	private String patenteCamion;
	private String dniChofer;
	private Factura mockFactura;
	private OrdenDeImportacion orden;
	private LocalDateTime llegada;
	private LocalDateTime retiro;

	@BeforeEach
	void setUp() {
		
		mockTerminalGestionada = mock(TerminalGestionada.class);
		mockCliente = mock(Consignee.class);
		mockContainer = mock(Container.class);
		mockFactura = mock(Factura.class);
		
		patenteCamion = "ABC123";
		dniChofer = "12345678";

		llegada = LocalDateTime.of(2025, 10, 28, 10, 0);
		retiro = LocalDateTime.of(2025, 10, 29, 11, 0);

		orden = new OrdenDeImportacion(mockCliente, mockContainer, patenteCamion, dniChofer, llegada);
	}
	
	
	
	@Test
	void testRetiroContainerConExcedente() {
		
		llegada = LocalDateTime.of(2025, 10, 28, 10, 0);
		retiro = LocalDateTime.of(2025, 10, 29, 11, 0);

		orden = new OrdenDeImportacion(mockCliente, mockContainer, patenteCamion, dniChofer, llegada);

		//Ejecución
		orden.retiroContainer(retiro, 100.0, mockFactura);

		//Assert
		verify(mockFactura).setServicioDeAlmacenamiento(any());
	}
	
	@Test
	void testRetiroContainerSinExcedente() {
		
		llegada = LocalDateTime.of(2025, 10, 28, 10, 0);
		retiro = LocalDateTime.of(2025, 10, 29, 9, 0);

		orden = new OrdenDeImportacion(mockCliente, mockContainer, patenteCamion, dniChofer, llegada);
		
		//Ejecución
		orden.retiroContainer(retiro, 100.0, mockFactura);

		//Assert
		verifyNoInteractions(mockFactura);
	}

	@Test
	void testVerificarAutorizacionTrue() {

		//Assert
		assertTrue(orden.verificarAutorizacion(patenteCamion, dniChofer));
	}

	@Test
	void testVerificarAutorizacionFalse() {

		//setUp
		String otraPatente = "XYZ789";
		String otroDni = "87654321";

		//Assert
		assertFalse(orden.verificarAutorizacion(otraPatente, otroDni));
	}
	
	@Test
	void testRegistrarEn_deberiaLlamarAlMetodoRegistrar() {
		// Ejecución
		orden.registrarEn(mockTerminalGestionada);
		
		// Verificación
		verify(mockTerminalGestionada, times(1)).registrar(orden);
	}
	
	@Test
	void testGetConsignee_deberiaRetornarElClienteCorrecto() {
		// Ejecución
		Consignee resultado = orden.getConsignee();
		
		// Verificación
		assertEquals(mockCliente, resultado);
	}

}
