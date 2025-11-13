package terminal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buqueViaje.Coordenadas;
import motorDeBusqueda.MotorDeBusqueda;
import excepciones.OperacionNoDisponibleException;
import interfaces.*;
import ordenes.OrdenDeImportacion;
import servicios.ServicioLavado;

public class TerminalGestionadaTest {

	private TerminalGestionada terminal;

	private INaviera mockNaviera;
	private IContainer mockContainer;
	private IEmpresaTransportista mockEmpresaTransportista;
	private IOrdenDeExportacion mockOrdenExportacion;
	private OrdenDeImportacion mockOrdenImportacion;
	private IBuqueViaje mockBuqueViaje;
	private IShipper mockShipper;
	private IConsignee mockConsignee;
	private Terminal mockTerminalDestino;
	private IRutaMaritima mockRutaMaritima;
	private IBuque mockBuque;
	private IViaje mockViaje;
	private ICircuito mockCircuito;
	private Coordenadas coordenadas;
	private IFactura mockFactura;
	private String patente;
	private String dni;
	private LocalDateTime turno;
	private String containerId;


	@BeforeEach
	void setUp() {
		mockNaviera = mock(INaviera.class);
		mockContainer = mock(IContainer.class);
		mockEmpresaTransportista = mock(IEmpresaTransportista.class);
		mockOrdenExportacion = mock(IOrdenDeExportacion.class);
		mockOrdenImportacion = mock(OrdenDeImportacion.class);
		mockBuqueViaje = mock(IBuqueViaje.class);
		mockShipper = mock(IShipper.class);
		mockConsignee = mock(IConsignee.class);
		mockTerminalDestino = mock(Terminal.class);
		mockRutaMaritima = mock(IRutaMaritima.class);
		mockBuque = mock(IBuque.class);
		mockViaje = mock(IViaje.class);
		mockCircuito = mock(ICircuito.class);
		mockFactura = mock(IFactura.class);
		coordenadas = new Coordenadas(1, 1);
		terminal = new TerminalGestionada("Terminal Gestionada", coordenadas);
		patente = "ABC123";
	    dni = "12345678";
	    turno = LocalDateTime.now();
	    containerId = "C1";

	    when(mockOrdenExportacion.getCamion()).thenReturn(patente);
	    when(mockOrdenExportacion.getChofer()).thenReturn(dni);
	    when(mockOrdenExportacion.turno()).thenReturn(turno);
	    
	    LocalDateTime fechaSalida = LocalDateTime.now();
		Duration duracion = Duration.ofDays(5);

		Set<IContainer> contenedores = new HashSet<>();
		contenedores.add(mockContainer);

		Set<IBuque> buques = new HashSet<>();
		buques.add(mockBuque);

		Set<IViaje> viajes = new HashSet<IViaje>();
		viajes.add(mockViaje);

		when(mockContainer.getId()).thenReturn(containerId);
		when(mockEmpresaTransportista.asignarCamionPara(mockContainer)).thenReturn(patente);
		when(mockEmpresaTransportista.asignarChoferPara(mockContainer)).thenReturn(dni);
		when(mockNaviera.getBuques()).thenReturn(buques);
		when(mockNaviera.getViajes()).thenReturn(viajes);
		when(mockBuque.getContainers()).thenReturn(contenedores);
		when(mockViaje.getBuque()).thenReturn(mockBuque);
		when(mockViaje.fechaSalida()).thenReturn(fechaSalida);
		when(mockViaje.getCircuito()).thenReturn(mockCircuito);
		when(mockCircuito.duracionTotal()).thenReturn(duracion);
		
		LocalDateTime salida = LocalDateTime.now();
		LocalDateTime llegada = LocalDateTime.now().plusDays(10);
		Terminal terminalDestino = mockTerminalDestino;

		when(mockRutaMaritima.fechaSalida()).thenReturn(salida);
		when(mockRutaMaritima.fechaLlegada()).thenReturn(llegada);
		when(mockRutaMaritima.puertoDestino()).thenReturn(terminalDestino);
		when(mockRutaMaritima.getViaje()).thenReturn(mockViaje);

		when(mockBuqueViaje.getViaje()).thenReturn(mockViaje);
		when(mockBuqueViaje.getViaje()).thenReturn(mockViaje);
		
	}

	@Test
	void testConstructor() {
		assertEquals("Terminal Gestionada", terminal.getNombre());
		assertEquals(coordenadas, terminal.getCoordenadas());
	}

	@Test
	void testGetNombre() {
		assertEquals("Terminal Gestionada", terminal.getNombre());
	}

	@Test
	void testGetCoordenadas() {
		assertEquals(coordenadas, terminal.getCoordenadas());
	}

	@Test
	void testVerificarCargaLlegadaExitoso() {

		LocalDateTime llegada = turno.plusHours(2);

		terminal.registrarCamion(patente);
		terminal.registrarChofer(dni);
		terminal.registrar(mockOrdenExportacion);

		boolean resultado = terminal.verificarCargaLlegada(patente, dni, llegada);

		assertTrue(resultado);
	}

	@Test
	void testVerificarCargaLlegadaFueraDeHorario() {
		LocalDateTime llegada = turno.plusHours(5);

		terminal.registrarCamion(patente);
		terminal.registrarChofer(dni);
		terminal.registrar(mockOrdenExportacion);

		boolean resultado = terminal.verificarCargaLlegada(patente, dni, llegada);

		assertFalse(resultado);
	}

	@Test
	void testVerificarCargaLlegadaSinOrden() {
		LocalDateTime llegada = LocalDateTime.now();

		terminal.registrarCamion(patente);
		terminal.registrarChofer(dni);

		boolean resultado = terminal.verificarCargaLlegada(patente, dni, llegada);

		assertFalse(resultado);
	}

	@Test
	void testVerificarCargaLlegadaCamionNoRegistrado() {
		LocalDateTime llegada = turno.plusHours(1);

		terminal.registrarChofer(dni);
		terminal.registrar(mockOrdenExportacion);

		boolean resultado = terminal.verificarCargaLlegada(patente, dni, llegada);

		assertFalse(resultado);
	}

	@Test
	void testVerificarCargaLlegadaChoferNoRegistrado() {

		LocalDateTime llegada = turno.plusHours(1);

		terminal.registrarCamion(patente);
		terminal.registrar(mockOrdenExportacion);

		boolean resultado = terminal.verificarCargaLlegada(patente, dni, llegada);

		assertFalse(resultado);
	}

	@Test
	void testVerificarCargaRetiroExitoso() {
		terminal.registrarCamion(patente);
		terminal.registrarChofer(dni);

		boolean resultado = terminal.verificarCargaRetiro(patente, dni);

		assertTrue(resultado);
	}

	@Test
	void testVerificarCargaRetiroSinCamion() {
		terminal.registrarChofer(dni);
		boolean resultado = terminal.verificarCargaRetiro(patente, dni);

		assertFalse(resultado);
	}

	@Test
	void testVerificarCargaRetiroSinChofer() {
		terminal.registrarCamion(patente);
		boolean resultado = terminal.verificarCargaRetiro(patente, dni);

		assertFalse(resultado);
	}

	@Test
	void testInminenteArriboAlConsignee() throws OperacionNoDisponibleException {
		terminal.registrarNaviera(mockNaviera);
		terminal.datosParaElRetiro(mockConsignee, mockEmpresaTransportista, mockContainer);
		terminal.inminenteArribo(mockBuqueViaje);

		verify(mockConsignee, times(1)).recibirMail("Su carga esta llegando a la terminal");
	}
	
	

	@Test
	void testAvisoDeSalida_NotificaShippersYDeshabilitaPagos() throws OperacionNoDisponibleException {
		terminal.exportar(mockContainer, mockTerminalDestino, mockRutaMaritima, mockShipper,
				mockEmpresaTransportista);
		terminal.avisoDeSalida(mockBuqueViaje);

		verify(mockShipper).recibirMail("Su carga ya ha salido de la terminal");
	}

	@Test
	void testAvisoDeLlegada_ProcesaExportaciones() throws OperacionNoDisponibleException {
		terminal.exportar(mockContainer, mockTerminalDestino, mockRutaMaritima, mockShipper,
				mockEmpresaTransportista);
		terminal.avisoDeLlegada(mockBuqueViaje);

		verify(mockBuque).addContainer(mockContainer);
		verify(mockBuqueViaje).inicioDeTrabajo();
		verify(mockBuqueViaje).depart();

	}
	
	@Test
	void testAvisoDeLlegada_ProcesaImportaciones() throws OperacionNoDisponibleException {
		terminal.registrarNaviera(mockNaviera);
		terminal.datosParaElRetiro(mockConsignee, mockEmpresaTransportista, mockContainer);
		terminal.avisoDeLlegada(mockBuqueViaje);
		
		verify(mockBuque).removeContainer(mockContainer);
		verify(mockBuqueViaje).inicioDeTrabajo();
		verify(mockBuqueViaje).depart();
	}

	@Test
	void testExportar_NoLanzaExcepcion() throws OperacionNoDisponibleException {
		terminal.exportar(mockContainer, mockTerminalDestino, mockRutaMaritima, mockShipper,
				mockEmpresaTransportista);
		terminal.avisoDeLlegada(mockBuqueViaje);

		verify(mockBuque).addContainer(mockContainer);
		verify(mockBuqueViaje).inicioDeTrabajo();
		verify(mockBuqueViaje).depart();

		assertDoesNotThrow(() -> {
			terminal.registrarPago(mockFactura);
		});
	}

	@Test
	void testDatosParaElRetiro() throws OperacionNoDisponibleException {
		terminal.registrarNaviera(mockNaviera);
		terminal.datosParaElRetiro(mockConsignee, mockEmpresaTransportista, mockContainer);

		verify(mockEmpresaTransportista, times(1)).asignarCamionPara(mockContainer);
		verify(mockEmpresaTransportista, times(1)).asignarChoferPara(mockContainer);
	}

	@Test
	void testDatosParaElRetiroContenedorNoEncontrado() {
		Set<IContainer> contenedoresVacio = new HashSet<IContainer>();
		when(mockBuque.getContainers()).thenReturn(contenedoresVacio);
		terminal.registrarNaviera(mockNaviera);

		assertThrows(RuntimeException.class, () -> {
			terminal.datosParaElRetiro(mockConsignee, mockEmpresaTransportista, mockContainer);
		});
	}

	@Test
	void testVerificarCargaLlegadaAntes() {
		LocalDateTime turno = LocalDateTime.now();
		LocalDateTime llegada = turno.minusHours(2);

		terminal.registrarCamion(patente);
		terminal.registrarChofer(dni);
		terminal.registrar(mockOrdenExportacion);

		boolean resultado = terminal.verificarCargaLlegada(patente, dni, llegada);

		assertTrue(resultado);
	}

	@Test
	void testCronogramaExportacion() {
		INaviera mockNaviera2 = mock(INaviera.class);
		INaviera mockNaviera3 = mock(INaviera.class);

		IViaje mockViaje1 = mock(IViaje.class);
		IViaje mockViaje2 = mock(IViaje.class);
		IViaje mockViaje3 = mock(IViaje.class);

		Set<IViaje> viajes1 = new HashSet<>();
		viajes1.add(mockViaje1);

		Set<IViaje> viajes2 = new HashSet<>();
		viajes2.add(mockViaje2);

		Set<IViaje> viajes3 = new HashSet<>();
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
	void testRetiroDeContainer() throws OperacionNoDisponibleException {
		LocalDateTime fechaRetiro = LocalDateTime.now();
		Duration duracionViaje = Duration.ofDays(5);
		when(mockContainer.getTipo()).thenReturn("dry");
		when(mockCircuito.duracionTotal()).thenReturn(duracionViaje);
		terminal.registrarNaviera(mockNaviera);
		terminal.registrarCamion(patente);
		terminal.registrarChofer(dni);
		terminal.datosParaElRetiro(mockConsignee, mockEmpresaTransportista, mockContainer);
		
		terminal.retiroDeContainer(patente, dni, containerId, fechaRetiro);

		assertEquals(terminal.cantidadDeFacturas(), 1);
	}
	
	
	
	@Test
	void testEntregaDeContainer_VerificacionExitosa_AgregaContainer() {
	    LocalDateTime fechaEntrega = LocalDateTime.now();
	    String idContainer = "C1";

	    TerminalGestionada spyTerminal = spy(terminal);
	    doReturn(true).when(spyTerminal).verificarCargaLlegada(patente, dni, fechaEntrega);
	    spyTerminal.registrarOrden(mockOrdenImportacion);
	    spyTerminal.ordenPorContainer.put(idContainer, mockOrdenImportacion);
	    spyTerminal.entregaDeContainer(patente, dni, mockContainer, fechaEntrega);

	    assertTrue(spyTerminal.containers.contains(mockContainer));
	}
	
	@Test
	void testAgregarServicioDeLavado_AgregaServicioALaOrdenCorrecta() {
	    terminal.ordenPorContainer.put(containerId, mockOrdenImportacion);
	    terminal.agregarServicioDeLavado(mockContainer);

	    verify(mockOrdenImportacion).agregarServicio(any(ServicioLavado.class));
	}

}