package ordenes;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import interfaces.Container;
import interfaces.ICliente;
import interfaces.IShipper;
import ordenes.OrdenDeExportacion;
import terminal.Terminal;
import terminal.TerminalGestionada;

class OrdenDeExportacionTest {

	private IShipper mockShipper;
	private Container mockContainer;
	private String patenteCamion;
	private String dniChofer;
	private Terminal mockTerminalDestino;
	private TerminalGestionada mockTerminalGestionada;
	private LocalDate fechaSalida;
	private LocalDate fechaLlegada;
	private OrdenDeExportacion orden;

	@BeforeEach
	void setUp() {

		//setUp
		mockShipper = mock(IShipper.class);
		mockContainer = mock(Container.class);
		mockTerminalGestionada = mock(TerminalGestionada.class);
		mockTerminalDestino = mock(Terminal.class);
		patenteCamion = "ABC123";
		dniChofer = "40555999";

		fechaSalida = LocalDate.of(2025, 10, 29);
		fechaLlegada = LocalDate.of(2025, 11, 2);

		orden = new OrdenDeExportacion(mockShipper, mockContainer, patenteCamion, dniChofer, fechaSalida, fechaLlegada, mockTerminalDestino);
	}

	@Test
	void testGetShipper() {
		// Ejecución
		ICliente resultado = orden.getCliente();

		// Verificación
		assertEquals(mockShipper, resultado);
	}
	
	 @Test
	    void testGetDatosDeCarga() {
	        Container resultado = orden.getDatosDeCarga();
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
		// Ejecución
		Terminal terminal = orden.getTerminalDestino();

		// Verificación
		assertEquals(mockTerminalDestino, terminal);
	}


	@Test
	void testVerificarQueLeAsigneElTurnoAlShipper() {
		//setUp
		LocalDateTime turno = LocalDateTime.of(2025, 10, 28, 15, 0);

		//Ejecucion
		orden.asignarTurno(turno);

		//Assert
		verify(mockShipper).fechaDeExportacion(LocalDateTime.of(2025, 10, 28, 15, 0));
	}
	
	@Test
	void testGetFechaLlegada() {
		// Ejecución
		LocalDate resultado = orden.getFechaLlegada();
		
		// Verificación
		assertNotNull(resultado, "La fecha de llegada no debería ser null");
		assertEquals(fechaLlegada, resultado);
		assertEquals(2025, resultado.getYear());
		assertEquals(11, resultado.getMonthValue());
		assertEquals(2, resultado.getDayOfMonth());
	}
	
	@Test
	void testGetFechaSalida() {
		// Ejecución
		LocalDate resultado = orden.getFechaSalida();
		
		// Verificación
		assertNotNull(resultado, "La fecha de salida no debería ser null");
		assertEquals(fechaSalida, resultado);
		assertEquals(2025, resultado.getYear());
		assertEquals(10, resultado.getMonthValue());
		assertEquals(29, resultado.getDayOfMonth());
	}
	
	@Test
	void testRegistrarEn_deberiaLlamarAlMetodoRegistrar() {
		// Ejecución
		orden.registrarEn(mockTerminalGestionada);

		// Verificación
		verify(mockTerminalGestionada, times(1)).registrar(orden);
	}
	
	@Test
	void testRegistrarEn_verificarQueSeRegistraLaOrdenCorrecta() {
		// Ejecución
		orden.registrarEn(mockTerminalGestionada);

		// Verificación con ArgumentCaptor para capturar el argumento
		verify(mockTerminalGestionada).registrar(eq(orden));
	}
	
	@Test
	void testAsignarTurnoYRegistrar() {
		// Setup
		LocalDateTime turno = LocalDateTime.of(2025, 10, 28, 15, 0);

		// Ejecución
		orden.asignarTurno(turno);
		orden.registrarEn(mockTerminalGestionada);

		// Verificación
		verify(mockShipper).fechaDeExportacion(turno);
		verify(mockTerminalGestionada).registrar(orden);
		assertEquals(turno, orden.turno());
	}
	
	

}
