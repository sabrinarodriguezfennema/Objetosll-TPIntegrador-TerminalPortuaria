package terminal;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import clases.MotorDeBusqueda;
import interfaces.Viaje;
import interfaces.Cliente;
import interfaces.Shipper;
import interfaces.Buque;
import interfaces.BuqueViaje;
import interfaces.Container;
import interfaces.Naviera;
import interfaces.RutaMaritima;
import interfaces.Servicio;
import interfaces.Consignee;
import interfaces.Orden;
import ordenes.OrdenDeExportacion;
import ordenes.OrdenDeImportacion;
import interfaces.EmpresaTransportista;
import interfaces.IOrdenDeExportacion;
import interfaces.IOrdenDeImportacion;

public class TerminalGestionada extends Terminal implements GestionLogistica, GestionEnvio {

	private String nombre;
	private String ubicacion;
	private Set<Naviera> navierasRegistradas;
	private Set<Container> containers;
	private Set<EmpresaTransportista> empresasTransportistas;
	private Set<String> camionesRegistrados;
	private Set<String> choferesRegistrados;
	private Set<Cliente> clientesRegistrados;
	private Set<IOrdenDeImportacion> ordenesImportacion;
	private Set<IOrdenDeExportacion> ordenesExportacion;
	private Map<String, IOrdenDeExportacion> ordenesActivasPorPatente;
	private Map<String, Orden> ordenPorContainer;


	public TerminalGestionada(String nombre, String ubicacion) {

		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.navierasRegistradas = new HashSet<>();
		this.containers = new HashSet<>();
		this.ordenesImportacion = new HashSet<>();
		this.ordenesExportacion = new HashSet<>();
		this.empresasTransportistas = new HashSet<>();
		this.camionesRegistrados = new HashSet<>();
		this.choferesRegistrados = new HashSet<>();
		this.clientesRegistrados = new HashSet<>();
		this.ordenesActivasPorPatente = new HashMap<>();
		this.ordenPorContainer = new HashMap<>();

	}

	public void registrarNaviera(Naviera naviera) {
		navierasRegistradas.add(naviera);
	}

	private void registrarOrden(Orden orden) {
		orden.registrarEn(this);
	}

	public void registrar(OrdenDeImportacion ordenDeImportacion) {
		ordenesImportacion.add(ordenDeImportacion);
	}

	public void registrar(IOrdenDeExportacion orden) {
		ordenesExportacion.add(orden);
		ordenesActivasPorPatente.put(orden.getCamion(), orden);
	}

	private void registrarContainer(Container container) {
		this.containers.add(container);
	}

	public void registrarEmpresaTransportista(EmpresaTransportista empresa) {
		this.empresasTransportistas.add(empresa);
	}

	private void registrarCliente(Cliente cliente) {
		this.clientesRegistrados.add(cliente);
	}

	@Override
	public void registrarCamion(String patenteCamion) {
		this.camionesRegistrados.add(patenteCamion);

	}

	@Override
	public void registrarChofer(String dniChofer) {
		this.choferesRegistrados.add(dniChofer);

	}

	@Override
	public boolean verificarCargaLlegada(String patenteCamion, String dniChofer, LocalDateTime hora) {

		IOrdenDeExportacion orden = ordenesActivasPorPatente.get(patenteCamion);

		if (orden == null) {
	        return false; 
	    }

		boolean validarCamion = this.camionesRegistrados.contains(patenteCamion) && orden.getCamion() == patenteCamion;

		boolean validarChofer = this.choferesRegistrados.contains(dniChofer) && orden.getChofer() == dniChofer;

		long horasDiferencia = Math.abs(Duration.between(orden.turno(), hora).toHours());
		boolean validarHorario = horasDiferencia <= 3;

		boolean esValido = validarCamion && validarChofer && validarHorario;

		if (esValido) {
			ordenesActivasPorPatente.remove(patenteCamion);
		}

		return esValido;
	}

	@Override
	public boolean verificarCargaRetiro(String patenteCamion, String dniChofer) {

		return this.camionesRegistrados.contains(patenteCamion) && this.choferesRegistrados.contains(dniChofer);
	}

	@Override
	public void avisoDeSalida(BuqueViaje bv) {

		for (IOrdenDeExportacion orden : ordenesExportacion) {
			Shipper shipper = orden.getShipper();
			shipper.recibirMail("Su carga está próxima a salir");
		}
	}

	@Override
	public void avisoDeLlegada(BuqueViaje bv) {

		for (IOrdenDeImportacion orden : ordenesImportacion) {
			Consignee consignee = orden.getConsignee();
			consignee.recibirMail("Su carga está próxima a llegar");
		}
	}

	@Override
	public MotorDeBusqueda cronogramaExportacion(Terminal terminalDestino) {
		List<Viaje> viajesDisponibles = new ArrayList<>();

		for (Naviera naviera : navierasRegistradas) {
			viajesDisponibles.addAll(naviera.getViajes());
		}
		return new MotorDeBusqueda(viajesDisponibles, this, terminalDestino);
	}

	@Override
	public void exportar(Container c, Terminal t, RutaMaritima rm, List<Servicio> servicios, Shipper exportador,
			EmpresaTransportista empresa) {
		LocalDate salida = rm.fechaSalida();
		LocalDate llegada = rm.fechaLlegada();
		String patenteCamion = empresa.asignarCamionPara(c);
		String dniChofer = empresa.asignarChoferPara(c);
		OrdenDeExportacion orden = new OrdenDeExportacion(exportador, c, patenteCamion, dniChofer, salida, llegada,
				rm.puertoDestino());
		registrarOrden(orden);
		registrarContainer(c);
		registrarCamion(patenteCamion);
		registrarChofer(dniChofer);
		registrarEmpresaTransportista(empresa);
		registrarCliente(exportador);
		ordenPorContainer.put(c.getId(), orden);
	}

	@Override
	public void datosParaElRetiro(Consignee importador, EmpresaTransportista empresa, Container c) {

		Buque buque = navierasRegistradas.stream().flatMap(naviera -> naviera.getBuques().stream())
				.filter(b -> b.getContainers().contains(c)).findFirst()
				.orElseThrow(() -> new RuntimeException("Contenedor no encontrado en ningún buque registrado"));

		Viaje viajeDelContenedor = buque.getViaje();

		LocalDateTime fechaYHoraLlegada = viajeDelContenedor.fechaSalida() 
				.atStartOfDay() 
				.plus(viajeDelContenedor.getCircuito().duracionTotal());

		String patenteCamion = empresa.asignarCamionPara(c);
		String dniChofer = empresa.asignarChoferPara(c);

		OrdenDeImportacion orden = new OrdenDeImportacion(importador, c, patenteCamion, dniChofer, fechaYHoraLlegada);

		registrarOrden(orden);
		registrarEmpresaTransportista(empresa);
		registrarCliente(importador);
		ordenPorContainer.put(c.getId(), orden);
	}

	public String getNombre() {
		return nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

}
