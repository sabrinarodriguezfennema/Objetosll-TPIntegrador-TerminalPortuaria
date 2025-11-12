package ordenes;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import interfaces.IContainer;
import interfaces.ICliente;
import interfaces.IFactura;
import interfaces.IShipper;
import interfaces.IViaje;
import interfaces.IServicio;
import terminal.Terminal;
import terminal.TerminalGestionada;

class OrdenDeExportacionTest {

	private IShipper mockShipper;
	private IContainer mockContainer;
	private String patenteCamion;
	private String dniChofer;
	private Terminal mockTerminalDestino;
	private TerminalGestionada mockTerminalGestionada;
	private LocalDate fechaSalida;
	private LocalDate fechaLlegada;
	private OrdenDeExportacion orden;
	private IViaje mockViaje;
	private IServicio mockServicio;

	@BeforeEach
	void setUp() {

		// setUp
		mockShipper = mock(IShipper.class);
		mockContainer = mock(IContainer.class);
		mockTerminalGestionada = mock(TerminalGestionada.class);
		mockTerminalDestino = mock(Terminal.class);
		mockViaje = mock(IViaje.class);
		patenteCamion = "ABC123";
		dniChofer = "40555999";
		mockServicio = mock(IServicio.class);

		fechaSalida = LocalDate.of(2025, 10, 29);
		fechaLlegada = LocalDate.of(2025, 11, 2);

		orden = new OrdenDeExportacion(mockShipper, mockContainer, patenteCamion, dniChofer, fechaSalida, fechaLlegada,
				mockTerminalDestino);
	}

	@Test
	void testGetCliente() {
		ICliente resultado = orden.getCliente();

		assertEquals(mockShipper, resultado);
	}

	@Test
	void testGetDatosDeCarga() {
		IContainer resultado = orden.getDatosDeCarga();

		assertEquals(mockContainer, resultado);
	}

	@Test
	void testGetDNIChofer() {
		String chofer = orden.getChofer();

		assertEquals("40555999", chofer);
	}

	@Test
	void testGetPatenteCamion() {
		String camion = orden.getCamion();

		assertEquals("ABC123", camion);
	}

	@Test
	void testGetTerminal() {
		Terminal terminal = orden.getTerminalDestino();

		assertEquals(mockTerminalDestino, terminal);
	}

	@Test
	void testVerificarQueLeAsigneElTurnoAlShipper() {
		LocalDateTime turno = LocalDateTime.of(2025, 10, 28, 15, 0);

		orden.asignarTurno(turno);

		verify(mockShipper).fechaDeExportacion(LocalDateTime.of(2025, 10, 28, 15, 0));
	}

	@Test
	void testGetFechaLlegada() {
		LocalDate resultado = orden.getFechaLlegada();

		assertEquals(fechaLlegada, resultado);

	}

	@Test
	void testGetFechaSalida() {
		LocalDate resultado = orden.getFechaSalida();

		assertEquals(fechaSalida, resultado);
	}

	@Test
	void testRegistrarEn_deberiaLlamarAlMetodoRegistrar() {
		orden.registrarEn(mockTerminalGestionada);

		verify(mockTerminalGestionada, times(1)).registrar(orden);
	}

	@Test
	void testRegistrarEn_verificarQueSeRegistraLaOrdenCorrecta() {
		orden.registrarEn(mockTerminalGestionada);

		verify(mockTerminalGestionada).registrar(eq(orden));
	}

	@Test
	void testAsignarTurnoYRegistrar() {
		LocalDateTime turno = LocalDateTime.of(2025, 10, 28, 15, 0);

		orden.asignarTurno(turno);
		orden.registrarEn(mockTerminalGestionada);

		verify(mockShipper).fechaDeExportacion(turno);
		verify(mockTerminalGestionada).registrar(orden);
		assertEquals(turno, orden.turno());
	}
	
	@Test
    void testGenerarFactura() {
		LocalDateTime fecha = LocalDateTime.of(2025, 10, 28, 15, 0);
        IFactura factura = orden.generarFactura(fecha, 100.0, mockViaje);
        
        assertNotNull(factura);
        
    }
	
	@Test
    void testAgregarServicioALaFactura() {
		LocalDateTime fecha = LocalDateTime.of(2025, 10, 28, 15, 0);    
		
		orden.agregarServicio(mockServicio);
     
        orden.generarFactura(fecha, 100.0, mockViaje);
        
        assertEquals(1, orden.getServicios().size());
    }

}
