package terminal;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import clases.MotorDeBusqueda;
import clientes.*;
import interfaces.*;
import ordenes.OrdenDeExportacion;
import ordenes.OrdenDeImportacion;

public class TerminalGestionadaTest {

	private TerminalGestionada terminal;

	private Naviera mockNaviera;
	private Container mockContainer;
	private EmpresaTransportista mockEmpresaTransportista;
	private ICliente mockCliente;
	private IOrdenDeExportacion mockOrdenExportacion;
	private OrdenDeImportacion mockOrdenImportacion;
	private BuqueViaje mockBuqueViaje;
	private IShipper mockShipper;
	private IConsignee mockConsignee;
	private Terminal mockTerminalDestino;
	private RutaMaritima mockRutaMaritima;
	private Servicio mockServicio;
	private Buque mockBuque;
	private Viaje mockViaje;
	private Circuito mockCircuito;
	private EmailService mockEmailService;

	@BeforeEach
	void setUp() {
		mockNaviera = mock(Naviera.class);
		mockContainer = mock(Container.class);
		mockEmpresaTransportista = mock(EmpresaTransportista.class);
		mockCliente = mock(ICliente.class);
		mockOrdenExportacion = mock(IOrdenDeExportacion.class);
		mockOrdenImportacion = mock(OrdenDeImportacion.class);
		mockBuqueViaje = mock(BuqueViaje.class);
		mockShipper = mock(IShipper.class);
		mockConsignee = mock(IConsignee.class);
		mockTerminalDestino = mock(Terminal.class);
		mockRutaMaritima = mock(RutaMaritima.class);
		mockServicio = mock(Servicio.class);
		mockBuque = mock(Buque.class);
		mockViaje = mock(Viaje.class);
		mockCircuito = mock(Circuito.class);

		terminal = new TerminalGestionada("Terminal Gestionada", "Buenos Aires");
	}

	@Test
	void testConstructor() {
		assertEquals("Terminal Gestionada", terminal.getNombre());
		assertEquals("Buenos Aires", terminal.getUbicacion());
	}

	@Test
	void testGetNombre() {
		assertEquals("Terminal Gestionada", terminal.getNombre());
	}
	
	@Test
    void testGetUbicacion() {
        assertEquals("Buenos Aires", terminal.getUbicacion());
    }
	
	@Test
	void testVerificarCargaLlegadaExitoso() {
		String patente = "ABC123";
		String dni = "12345678";
		LocalDateTime turno = LocalDateTime.now();
		LocalDateTime llegada = turno.plusHours(2);

		when(mockOrdenExportacion.getCamion()).thenReturn(patente);
		when(mockOrdenExportacion.getChofer()).thenReturn(dni);
		when(mockOrdenExportacion.turno()).thenReturn(turno);

		terminal.registrarCamion(patente);
		terminal.registrarChofer(dni);
		terminal.registrar(mockOrdenExportacion);

		boolean resultado = terminal.verificarCargaLlegada(patente, dni, llegada);

		assertTrue(resultado);
	}

	@Test
	void testVerificarCargaLlegadaFueraDeHorario() {
		String patente = "ABC123";
		String dni = "12345678";
		LocalDateTime turno = LocalDateTime.now();
		LocalDateTime llegada = turno.plusHours(5);

		when(mockOrdenExportacion.getCamion()).thenReturn(patente);
		when(mockOrdenExportacion.getChofer()).thenReturn(dni);
		when(mockOrdenExportacion.turno()).thenReturn(turno);

		terminal.registrarCamion(patente);
		terminal.registrarChofer(dni);
		terminal.registrar(mockOrdenExportacion);

		boolean resultado = terminal.verificarCargaLlegada(patente, dni, llegada);

		assertFalse(resultado);
	}

	@Test
	void testVerificarCargaLlegadaSinOrden() {
		String patente = "ABC123";
		String dni = "12345678";
		LocalDateTime llegada = LocalDateTime.now();

		terminal.registrarCamion(patente);
		terminal.registrarChofer(dni);

		boolean resultado = terminal.verificarCargaLlegada(patente, dni, llegada);

		assertFalse(resultado);
	}

	@Test
	void testVerificarCargaLlegadaCamionNoRegistrado() {
		String patente = "ABC123";
		String dni = "12345678";
		LocalDateTime turno = LocalDateTime.now();
		LocalDateTime llegada = turno.plusHours(1);

		when(mockOrdenExportacion.getCamion()).thenReturn(patente);
		when(mockOrdenExportacion.getChofer()).thenReturn(dni);
		when(mockOrdenExportacion.turno()).thenReturn(turno);

		terminal.registrarChofer(dni);
		terminal.registrar(mockOrdenExportacion);

		boolean resultado = terminal.verificarCargaLlegada(patente, dni, llegada);

		assertFalse(resultado);
	}

	@Test
	void testVerificarCargaLlegadaChoferNoRegistrado() {
		String patente = "ABC123";
		String dni = "12345678";
		LocalDateTime turno = LocalDateTime.now();
		LocalDateTime llegada = turno.plusHours(1);

		when(mockOrdenExportacion.getCamion()).thenReturn(patente);
		when(mockOrdenExportacion.getChofer()).thenReturn(dni);
		when(mockOrdenExportacion.turno()).thenReturn(turno);

		terminal.registrarCamion(patente);
		terminal.registrar(mockOrdenExportacion);

		boolean resultado = terminal.verificarCargaLlegada(patente, dni, llegada);

		assertFalse(resultado);
	}

	@Test
	void testVerificarCargaRetiroExitoso() {
		String patente = "ABC123";
		String dni = "12345678";

		terminal.registrarCamion(patente);
		terminal.registrarChofer(dni);

		boolean resultado = terminal.verificarCargaRetiro(patente, dni);

		assertTrue(resultado);
	}

	@Test
	void testVerificarCargaRetiroSinCamion() {
		String patente = "ABC123";
		String dni = "12345678";

		terminal.registrarChofer(dni);

		boolean resultado = terminal.verificarCargaRetiro(patente, dni);

		assertFalse(resultado);
	}

	@Test
	void testVerificarCargaRetiroSinChofer() {
		String patente = "ABC123";
		String dni = "12345678";

		terminal.registrarCamion(patente);

		boolean resultado = terminal.verificarCargaRetiro(patente, dni);

		assertFalse(resultado);
	}

	@Test
	void testAvisoDeSalidaAlShipper() {

		when(mockOrdenExportacion.getShipper()).thenReturn(mockShipper);

		terminal.registrar(mockOrdenExportacion);
		terminal.avisoDeSalida(mockBuqueViaje);

		verify(mockShipper, times(1)).recibirMail("Su carga está próxima a salir");
	}

	@Test
	void testAvisoDeLlegadaAlConsignee() {

		when(mockOrdenImportacion.getConsignee()).thenReturn(mockConsignee);

		terminal.registrar(mockOrdenImportacion);
		terminal.avisoDeLlegada(mockBuqueViaje);

		verify(mockConsignee, times(1)).recibirMail("Su carga está próxima a llegar");
	}

	@Test
	void testExportar() {
		String patente = "ABC123";
		String dni = "12345678";
		LocalDate salida = LocalDate.now();
		LocalDate llegada = LocalDate.now().plusDays(10);
		Terminal terminalDestino = mockTerminalDestino;

		when(mockContainer.getId()).thenReturn("C1");
		when(mockEmpresaTransportista.asignarCamionPara(mockContainer)).thenReturn(patente);
		when(mockEmpresaTransportista.asignarChoferPara(mockContainer)).thenReturn(dni);
		when(mockRutaMaritima.fechaSalida()).thenReturn(salida);
		when(mockRutaMaritima.fechaLlegada()).thenReturn(llegada);
		when(mockRutaMaritima.puertoDestino()).thenReturn(terminalDestino);

		List<Servicio> servicios = Arrays.asList(mockServicio);

		terminal.exportar(mockContainer, mockTerminalDestino, mockRutaMaritima, servicios, mockShipper,
				mockEmpresaTransportista);

		verify(mockEmpresaTransportista, times(1)).asignarCamionPara(mockContainer);
		verify(mockEmpresaTransportista, times(1)).asignarChoferPara(mockContainer);
	}

	@Test
	void testDatosParaElRetiro() {
		String patente = "XYZ789";
		String dni = "87654321";
		String containerId = "C2";
		LocalDate fechaSalida = LocalDate.now();
		Duration duracion = Duration.ofDays(5);

		Set<Container> contenedores = new HashSet<>();
		contenedores.add(mockContainer);

		Set<Buque> buques = new HashSet<>();
		buques.add(mockBuque);

		when(mockContainer.getId()).thenReturn(containerId);
		when(mockEmpresaTransportista.asignarCamionPara(mockContainer)).thenReturn(patente);
		when(mockEmpresaTransportista.asignarChoferPara(mockContainer)).thenReturn(dni);
		when(mockNaviera.getBuques()).thenReturn(buques);
		when(mockBuque.getContainers()).thenReturn(contenedores);
		when(mockBuque.getViaje()).thenReturn(mockViaje);
		when(mockViaje.fechaSalida()).thenReturn(fechaSalida);
		when(mockViaje.getCircuito()).thenReturn(mockCircuito);
		when(mockCircuito.duracionTotal()).thenReturn(duracion);

		terminal.registrarNaviera(mockNaviera);

		terminal.datosParaElRetiro(mockConsignee, mockEmpresaTransportista, mockContainer);

		verify(mockEmpresaTransportista, times(1)).asignarCamionPara(mockContainer);
		verify(mockEmpresaTransportista, times(1)).asignarChoferPara(mockContainer);
	}

	@Test
	void testDatosParaElRetiroContenedorNoEncontrado() {
		String containerId = "C3";
		Set<Container> contenedoresVacio = new HashSet<>();
		Set<Buque> buques = new HashSet<>();
		buques.add(mockBuque);

		when(mockContainer.getId()).thenReturn(containerId);
		when(mockNaviera.getBuques()).thenReturn(buques);
		when(mockBuque.getContainers()).thenReturn(contenedoresVacio);

		terminal.registrarNaviera(mockNaviera);

		assertThrows(RuntimeException.class, () -> {
			terminal.datosParaElRetiro(mockConsignee, mockEmpresaTransportista, mockContainer);
		});
	}
	
	@Test
    void testVerificarCargaLlegadaAntes() {
        String patente = "ABC123";
        String dni = "12345678";
        LocalDateTime turno = LocalDateTime.now();
        LocalDateTime llegada = turno.minusHours(2);

        when(mockOrdenExportacion.getCamion()).thenReturn(patente);
        when(mockOrdenExportacion.getChofer()).thenReturn(dni);
        when(mockOrdenExportacion.turno()).thenReturn(turno);

        terminal.registrarCamion(patente);
        terminal.registrarChofer(dni);
        terminal.registrar(mockOrdenExportacion);

        boolean resultado = terminal.verificarCargaLlegada(patente, dni, llegada);
        
        assertTrue(resultado); 
    }
	
	@Test
    void testCronogramaExportacion() {
        Naviera mockNaviera2 = mock(Naviera.class);
        Naviera mockNaviera3 = mock(Naviera.class);
        
        Viaje mockViaje1 = mock(Viaje.class);
        Viaje mockViaje2 = mock(Viaje.class);
        Viaje mockViaje3 = mock(Viaje.class);
        
        Set<Viaje> viajes1 = new HashSet<>();
        viajes1.add(mockViaje1);
        
        Set<Viaje> viajes2 = new HashSet<>();
        viajes2.add(mockViaje2);
        
        Set<Viaje> viajes3 = new HashSet<>();
        viajes3.add(mockViaje3);
        
        when(mockNaviera.getViajes()).thenReturn(viajes1);
        when(mockNaviera2.getViajes()).thenReturn(viajes2);
        when(mockNaviera3.getViajes()).thenReturn(viajes3);
        
        terminal.registrarNaviera(mockNaviera);
        terminal.registrarNaviera(mockNaviera2);
        terminal.registrarNaviera(mockNaviera3);
        
        MotorDeBusqueda motor = terminal.cronogramaExportacion(mockTerminalDestino);
        
        assertNotNull(motor);
        verify(mockNaviera, times(1)).getViajes();
        verify(mockNaviera2, times(1)).getViajes();
        verify(mockNaviera3, times(1)).getViajes();
    }
	
	@Test
	void testRetiroDeContainer() {
		String patente = "ABC123";
        String dni = "12345678";
        when(mockContainer.getId()).thenReturn("asd");
        String idContainer = mockContainer.getId();
        List<Buque> listaConBuque = new ArrayList<Buque>();
        listaConBuque.add(mockBuque);
		when(mockNaviera.getBuques()).thenReturn(listaConBuque);
		List<Container> listaConContainer = new ArrayList<Container>();
		listaConContainer.add(mockContainer);
		when(mockBuque.getContainers()).thenReturn(listaConContainer);
        
        terminal.registrarCamion(patente);
        terminal.registrarChofer(dni);
        terminal.datosParaElRetiro(mockConsignee, mockEmpresaTransportista, mockContainer);
        
		terminal.retiroDeContainer(patente, dni, idContainer);
		assertEquals(terminal.cantidadDeFacturas(), 1);
	}

}