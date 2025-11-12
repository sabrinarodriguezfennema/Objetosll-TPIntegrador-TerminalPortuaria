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

import buqueViaje.Coordenadas;
import clases.MotorDeBusqueda;
import clientes.*;
import excepciones.OperacionNoDisponibleException;
import interfaces.*;
import ordenes.OrdenDeExportacion;
import ordenes.OrdenDeImportacion;

public class TerminalGestionadaTest {

	private TerminalGestionada terminal;

	private INaviera mockNaviera;
	private IContainer mockContainer;
	private IEmpresaTransportista mockEmpresaTransportista;
	private ICliente mockCliente;
	private IOrdenDeExportacion mockOrdenExportacion;
	private OrdenDeImportacion mockOrdenImportacion;
	private IBuqueViaje mockBuqueViaje;
	private IShipper mockShipper;
	private IConsignee mockConsignee;
	private Terminal mockTerminalDestino;
	private IRutaMaritima mockRutaMaritima;
	private IServicio mockServicio;
	private IBuque mockBuque;
	private IViaje mockViaje;
	private ICircuito mockCircuito;
	private Coordenadas coordenadas;
	private IFactura mockFactura;

	@BeforeEach
	void setUp() {
		mockNaviera = mock(INaviera.class);
		mockContainer = mock(IContainer.class);
		mockEmpresaTransportista = mock(IEmpresaTransportista.class);
		mockCliente = mock(ICliente.class);
		mockOrdenExportacion = mock(IOrdenDeExportacion.class);
		mockOrdenImportacion = mock(OrdenDeImportacion.class);
		mockBuqueViaje = mock(IBuqueViaje.class);
		mockShipper = mock(IShipper.class);
		mockConsignee = mock(IConsignee.class);
		mockTerminalDestino = mock(Terminal.class);
		mockRutaMaritima = mock(IRutaMaritima.class);
		mockServicio = mock(IServicio.class);
		mockBuque = mock(IBuque.class);
		mockViaje = mock(IViaje.class);
		mockCircuito = mock(ICircuito.class);
		mockFactura = mock(IFactura.class);
		coordenadas = new Coordenadas(1, 1);

		terminal = new TerminalGestionada("Terminal Gestionada", coordenadas);
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
	void testInminenteArriboAlConsignee() throws OperacionNoDisponibleException {
		String patente = "XYZ789";
		String dni = "87654321";
		String containerId = "C2";
		LocalDate fechaSalida = LocalDate.now();
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

		when(mockBuqueViaje.getViaje()).thenReturn(mockViaje);

		terminal.registrarNaviera(mockNaviera);

		terminal.datosParaElRetiro(mockConsignee, mockEmpresaTransportista, mockContainer);

		terminal.inminenteArribo(mockBuqueViaje);

		verify(mockConsignee, times(1)).recibirMail("Su carga esta llegando a la terminal");
	}

	@Test
	void testAvisoDeSalida_NotificaShippersYDeshabilitaPagos() throws OperacionNoDisponibleException {
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
		when(mockRutaMaritima.getViaje()).thenReturn(mockViaje);

		when(mockBuqueViaje.getViaje()).thenReturn(mockViaje);

		List<IServicio> servicios = Arrays.asList(mockServicio);

		terminal.exportar(mockContainer, mockTerminalDestino, mockRutaMaritima, servicios, mockShipper,
				mockEmpresaTransportista);

		terminal.avisoDeSalida(mockBuqueViaje);

		verify(mockShipper).recibirMail("Su carga ya ha salido de la terminal");
	}

	@Test
	void testAvisoDeLlegada_ProcesaExportaciones() throws OperacionNoDisponibleException {

		String patente = "ABC123";
		String dni = "12345678";
		LocalDate salida = LocalDate.now();
		LocalDate llegada = LocalDate.now().plusDays(10);

		Set<IContainer> contenedores = new HashSet<>();
		contenedores.add(mockContainer);

		Set<IBuque> buques = new HashSet<>();
		buques.add(mockBuque);

		Set<IViaje> viajes = new HashSet<IViaje>();
		viajes.add(mockViaje);

		List<IServicio> servicios = Arrays.asList(mockServicio);

		when(mockContainer.getId()).thenReturn("C1");
		when(mockEmpresaTransportista.asignarCamionPara(mockContainer)).thenReturn(patente);
		when(mockEmpresaTransportista.asignarChoferPara(mockContainer)).thenReturn(dni);
		when(mockRutaMaritima.fechaSalida()).thenReturn(salida);
		when(mockRutaMaritima.fechaLlegada()).thenReturn(llegada);
		when(mockRutaMaritima.puertoDestino()).thenReturn(mockTerminalDestino);
		when(mockRutaMaritima.getViaje()).thenReturn(mockViaje);
		when(mockBuqueViaje.getViaje()).thenReturn(mockViaje);
		when(mockViaje.getBuque()).thenReturn(mockBuque);

		when(mockBuque.getContainers()).thenReturn(contenedores);

		terminal.exportar(mockContainer, mockTerminalDestino, mockRutaMaritima, servicios, mockShipper,
				mockEmpresaTransportista);
		terminal.avisoDeLlegada(mockBuqueViaje);

		verify(mockBuque).addContainer(mockContainer);
		verify(mockBuqueViaje).inicioDeTrabajo();
		verify(mockBuqueViaje).depart();

	}
	
	@Test
	void testAvisoDeLlegada_ProcesaImportaciones() throws OperacionNoDisponibleException {
		String patente = "XYZ789";
		String dni = "87654321";
		String containerId = "C2";
		LocalDate fechaSalida = LocalDate.now();
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
		when(mockBuqueViaje.getViaje()).thenReturn(mockViaje);
		when(mockCircuito.duracionTotal()).thenReturn(duracion);

		terminal.registrarNaviera(mockNaviera);
		
		terminal.datosParaElRetiro(mockConsignee, mockEmpresaTransportista, mockContainer);
		
		terminal.avisoDeLlegada(mockBuqueViaje);
		
		verify(mockBuque).removeContainer(mockContainer);
		verify(mockBuqueViaje).inicioDeTrabajo();
		verify(mockBuqueViaje).depart();
	}

	@Test
	void testRegistrarPago_CuandoPagosNoDisponibles_LanzaExcepcion() throws OperacionNoDisponibleException {
		terminal.setSePuedenRealizarPagos(false);

		assertThrows(OperacionNoDisponibleException.class, () -> {

			terminal.registrarPago(mockFactura);
		});
	}

	@Test
	void testRegistrarPago_CuandoPagosDisponibles_NoLanzaExcepcion() throws OperacionNoDisponibleException {

		terminal.setSePuedenRealizarPagos(true);

		assertDoesNotThrow(() -> {
			terminal.registrarPago(mockFactura);
		});
	}

	@Test
	void testExportar_NoLanzaExcepcion() throws OperacionNoDisponibleException {
		String patente = "ABC123";
		String dni = "12345678";
		LocalDate salida = LocalDate.now();
		LocalDate llegada = LocalDate.now().plusDays(10);

		Set<IContainer> contenedores = new HashSet<>();
		contenedores.add(mockContainer);

		Set<IBuque> buques = new HashSet<>();
		buques.add(mockBuque);

		Set<IViaje> viajes = new HashSet<IViaje>();
		viajes.add(mockViaje);

		List<IServicio> servicios = Arrays.asList(mockServicio);

		when(mockContainer.getId()).thenReturn("C1");
		when(mockEmpresaTransportista.asignarCamionPara(mockContainer)).thenReturn(patente);
		when(mockEmpresaTransportista.asignarChoferPara(mockContainer)).thenReturn(dni);
		when(mockRutaMaritima.fechaSalida()).thenReturn(salida);
		when(mockRutaMaritima.fechaLlegada()).thenReturn(llegada);
		when(mockRutaMaritima.puertoDestino()).thenReturn(mockTerminalDestino);
		when(mockRutaMaritima.getViaje()).thenReturn(mockViaje);
		when(mockBuqueViaje.getViaje()).thenReturn(mockViaje);
		when(mockViaje.getBuque()).thenReturn(mockBuque);

		when(mockBuque.getContainers()).thenReturn(contenedores);

		terminal.exportar(mockContainer, mockTerminalDestino, mockRutaMaritima, servicios, mockShipper,
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
	void testExportar_LanzaException() throws OperacionNoDisponibleException {

		terminal.setSePuedenInformarImportacionesYExportaciones(false);

		String patente = "ABC123";
		String dni = "12345678";
		LocalDate salida = LocalDate.now();
		LocalDate llegada = LocalDate.now().plusDays(10);

		when(mockContainer.getId()).thenReturn("C1");
		when(mockEmpresaTransportista.asignarCamionPara(mockContainer)).thenReturn(patente);
		when(mockEmpresaTransportista.asignarChoferPara(mockContainer)).thenReturn(dni);
		when(mockRutaMaritima.fechaSalida()).thenReturn(salida);
		when(mockRutaMaritima.fechaLlegada()).thenReturn(llegada);
		when(mockRutaMaritima.puertoDestino()).thenReturn(null);

		List<IServicio> servicios = List.of(mockServicio);

		assertThrows(OperacionNoDisponibleException.class, () -> terminal.exportar(mockContainer, null,
				mockRutaMaritima, servicios, mockShipper, mockEmpresaTransportista));
	}

	@Test
	void testDatosParaElRetiro_NoLanzaExcepcion() throws OperacionNoDisponibleException {

		terminal.setSePuedenInformarImportacionesYExportaciones(false);

		String patente = "XYZ789";
		String dni = "87654321";
		String containerId = "C2";
		LocalDate fechaSalida = LocalDate.now();
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

		terminal.registrarNaviera(mockNaviera);
		
		assertThrows(OperacionNoDisponibleException.class, () -> {
			terminal.datosParaElRetiro(mockConsignee, mockEmpresaTransportista, mockContainer);
		});
	}

	@Test
	void testDatosParaElRetiro() throws OperacionNoDisponibleException {
		String patente = "XYZ789";
		String dni = "87654321";
		String containerId = "C2";
		LocalDate fechaSalida = LocalDate.now();
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

		terminal.registrarNaviera(mockNaviera);

		terminal.datosParaElRetiro(mockConsignee, mockEmpresaTransportista, mockContainer);

		verify(mockEmpresaTransportista, times(1)).asignarCamionPara(mockContainer);
		verify(mockEmpresaTransportista, times(1)).asignarChoferPara(mockContainer);
	}

	@Test
	void testDatosParaElRetiroContenedorNoEncontrado() {
		String containerId = "C3";
		Set<IContainer> contenedoresVacio = new HashSet<IContainer>();
		Set<IBuque> buques = new HashSet<IBuque>();
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

		LocalDate fecha = LocalDate.now();
		LocalDateTime fechaRetiro = LocalDateTime.now();
		String patente = "ABC123";
		String dni = "12345678";
		String idContainer = "C1";
		double excedente = 100.00;
		Duration duracionViaje = Duration.ofDays(5);

		when(mockContainer.getId()).thenReturn(idContainer);

		Set<IContainer> listaConContainer = new HashSet<IContainer>();
		listaConContainer.add(mockContainer);
		when(mockBuque.getContainers()).thenReturn(listaConContainer);
		when(mockCircuito.duracionTotal()).thenReturn(duracionViaje);

		when(mockViaje.getBuque()).thenReturn(mockBuque);
		when(mockViaje.fechaSalida()).thenReturn(fecha);
		when(mockViaje.getCircuito()).thenReturn(mockCircuito);

		when(mockEmpresaTransportista.asignarCamionPara(mockContainer)).thenReturn(patente);
		when(mockEmpresaTransportista.asignarChoferPara(mockContainer)).thenReturn(dni);

		Set<IBuque> listaBuques = new HashSet<IBuque>();
		listaBuques.add(mockBuque);
		Set<IViaje> listaViajes = new HashSet<IViaje>();
		listaViajes.add(mockViaje);

		when(mockNaviera.getBuques()).thenReturn(listaBuques);
		when(mockNaviera.getViajes()).thenReturn(listaViajes);

		terminal.registrarNaviera(mockNaviera);
		terminal.registrarCamion(patente);
		terminal.registrarChofer(dni);

		terminal.datosParaElRetiro(mockConsignee, mockEmpresaTransportista, mockContainer);

		terminal.retiroDeContainer(patente, dni, idContainer, fechaRetiro, excedente);

		assertEquals(terminal.cantidadDeFacturas(), 1);
	}

}