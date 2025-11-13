package ordenes;

import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import interfaces.IContainer;
import interfaces.ICliente;
import interfaces.IConsignee;
import interfaces.IFactura;
import interfaces.IViaje;
import terminal.TerminalGestionada;

class OrdenDeImportacionTest {

	private TerminalGestionada mockTerminalGestionada;
	private IConsignee mockCliente;
	private IContainer mockContainer;
	private String patenteCamion;
	private String dniChofer;
	private OrdenDeImportacion orden;
	private LocalDateTime llegada;
	private IViaje mockViaje;

	@BeforeEach
	void setUp() {

		mockTerminalGestionada = mock(TerminalGestionada.class);

		mockContainer = mock(IContainer.class);
		mockViaje = mock(IViaje.class);
		patenteCamion = "ABC123";
		dniChofer = "12345678";

		llegada = LocalDateTime.of(2025, 10, 28, 10, 0);


		orden = new OrdenDeImportacion(mockCliente, mockContainer, patenteCamion, dniChofer, llegada);
	}

	@Test
	void testGetDatosDeCarga() {
		IContainer resultado = orden.getDatosDeCarga();

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
	
	
	
	@Test
    void testGenerarFacturaDentroDeLaTolerancia() {
        LocalDateTime fechaRetiro = llegada.plusHours(5);
        when(mockContainer.getTipo()).thenReturn("Dry");
        
        IFactura factura = orden.generarFactura(fechaRetiro, 100.0, 200.00, mockViaje);
        
        assertNotNull(factura);        
    }
	
	@Test
    void testGenerarFacturaFueraDeLaTolerancia() {
        LocalDateTime fechaRetiro = llegada.plusDays(2);
        when(mockContainer.getTipo()).thenReturn("Dry");
        
        IFactura factura = orden.generarFactura(fechaRetiro, 100.0, 200.00, mockViaje);
        
        assertNotNull(factura);
    }
	
	
	@Test
    void testGenerarFacturaFueraDeLaToleranciaAgregaOtroServicio() {
        LocalDateTime fechaRetiro = llegada.plusDays(2);
        when(mockContainer.getTipo()).thenReturn("Dry");
        
        orden.generarFactura(fechaRetiro, 100.0,200.00, mockViaje);
        
        assertEquals(1, orden.getServicios().size());
    }

}
