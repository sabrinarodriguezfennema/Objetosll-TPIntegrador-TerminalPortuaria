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

import buqueViaje.Coordenadas;
import clases.MotorDeBusqueda;
import excepciones.OperacionNoDisponibleException;
import interfaces.IFactura;
import interfaces.IViaje;
import interfaces.ICliente;
import interfaces.IShipper;
import interfaces.IBuque;
import interfaces.IBuqueViaje;
import interfaces.IContainer;
import interfaces.Naviera;
import interfaces.IRutaMaritima;
import interfaces.IServicio;
import interfaces.IConsignee;
import interfaces.IOrden;
import ordenes.OrdenDeExportacion;
import ordenes.OrdenDeImportacion;
import interfaces.EmpresaTransportista;
import interfaces.IOrdenDeExportacion;
import interfaces.IOrdenDeImportacion;

public class TerminalGestionada extends Terminal implements GestionLogistica, GestionEnvio {

	private Set<Naviera> navierasRegistradas;
	private Set<IContainer> containers;
	private Set<EmpresaTransportista> empresasTransportistas;
	private Set<String> camionesRegistrados;
	private Set<String> choferesRegistrados;
	private Set<ICliente> clientesRegistrados;
	private Set<IOrdenDeImportacion> ordenesImportacion;
	private Set<IOrdenDeExportacion> ordenesExportacion;
	private Map<String, IOrdenDeExportacion> ordenesActivasPorPatente;
	private Map<String, IOrden> ordenPorContainer;
	private Set<IFactura> facturas;
	private Map<IContainer, IViaje> containersPorViaje;
	private boolean sePuedenRealizarPagos;
	private boolean sePuedenInformarImportacionesYExportaciones;


	public TerminalGestionada(String nombre, Coordenadas ubicacion) {

		this.nombre = nombre;
		this.ubicacion = ubicacion;
		sePuedenRealizarPagos = true;
		sePuedenInformarImportacionesYExportaciones = true;
		this.navierasRegistradas = new HashSet<Naviera>();
		this.containers = new HashSet<IContainer>();
		this.ordenesImportacion = new HashSet<IOrdenDeImportacion>();
		this.ordenesExportacion = new HashSet<IOrdenDeExportacion>();
		this.empresasTransportistas = new HashSet<EmpresaTransportista>();
		this.camionesRegistrados = new HashSet<String>();
		this.choferesRegistrados = new HashSet<String>();
		this.clientesRegistrados = new HashSet<ICliente>();
		this.ordenesActivasPorPatente = new HashMap<String, IOrdenDeExportacion>();
		this.ordenPorContainer = new HashMap<String, IOrden>();
		this.containersPorViaje = new HashMap<IContainer, IViaje>();
		this.facturas = new HashSet<IFactura>();

	}

	public void registrarNaviera(Naviera naviera) {
		navierasRegistradas.add(naviera);
	}

	private void registrarOrden(IOrden orden) {
		orden.registrarEn(this);
	}

	public void registrar(OrdenDeImportacion ordenDeImportacion) {
		ordenesImportacion.add(ordenDeImportacion);
	}

	public void registrar(IOrdenDeExportacion orden) {
		ordenesExportacion.add(orden);
		ordenesActivasPorPatente.put(orden.getCamion(), orden);
	}

	private void registrarContainer(IContainer container) {
		this.containers.add(container);
	}

	public void registrarEmpresaTransportista(EmpresaTransportista empresa) {
		this.empresasTransportistas.add(empresa);
	}

	private void registrarCliente(ICliente cliente) {
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
	public void avisoDeSalida(IBuqueViaje bv) {
		//avisarle a los shippers que hayan exportado su container en ese bv
		IViaje viajeDelBuqueViaje = bv.getViaje();
		ICliente shipper;
		for (IOrdenDeExportacion orden : ordenesExportacion) {
			if(containersPorViaje.get(orden.getDatosDeCarga()) == viajeDelBuqueViaje) {
				shipper = orden.getCliente();
				shipper.recibirMail("Su carga ya ha salido de la terminal");
			}
		}
		this.setSePuedenRealizarPagos(false);//Pasa a outbound, donde no se pueden realizar pagos hasta departing
	}
	
	
	

	@Override
	public void inminenteArribo(IBuqueViaje bv) {
		this.setSePuedenInformarImportacionesYExportaciones(false); //Pasa a Arrived, donde no se pueden informar Importaciones ni Exportaciones
		
		IViaje viajeDelBuqueViaje = bv.getViaje();
		ICliente consignee;
		for (IOrdenDeImportacion orden : ordenesImportacion) {
			if(viajeDelBuqueViaje.equals(containersPorViaje.get(orden.getDatosDeCarga()))) {
				consignee = orden.getCliente();
				consignee.recibirMail("Su carga esta llegando a la terminal");
			}
		}
	}
	
	@Override
	public void avisoDeLlegada(IBuqueViaje bv) {
		this.setSePuedenInformarImportacionesYExportaciones(true); //deja de estar en Arrived, donde ya se pueden informar Importaciones y Exportaciones
		
		bv.inicioDeTrabajo();
		IViaje viajeDelBuqueViaje = bv.getViaje();
		IBuque buqueDelBuqueViaje =  viajeDelBuqueViaje.getBuque();
		Set<IContainer> containersDelBuqueViaje = buqueDelBuqueViaje.getContainers(); 
		
		for (IContainer container : containersDelBuqueViaje) {
			if(containersPorViaje.get(container) == viajeDelBuqueViaje) {
				IOrden orden = ordenPorContainer.get(container.getId());
				if(ordenesExportacion.contains(orden)) { //Si es de exportación
					buqueDelBuqueViaje.addContainer(container);
					orden.generarFactura(LocalDateTime.now(), 300d, viajeDelBuqueViaje); //TODO hacer que la orden de exportacion gener factura también
					
					containers.remove(container);
					ordenPorContainer.remove(container.getId());
					containersPorViaje.remove(container);
				} else { //Donde ya es de importación siosi
					buqueDelBuqueViaje.removeContainer(container);
					containers.add(container);
				}
			}
		}
		bv.depart();
		setSePuedenRealizarPagos(true); //Pasa a Departing, donde si se pueden realizar pagos
	}
	
	//registrarPago(factura) error
	public void registrarPago(IFactura factura) throws OperacionNoDisponibleException{
		if(!sePuedenRealizarPagos) {
			throw new OperacionNoDisponibleException("No se pueden realizar pagos en este momento");
		}
	}

	@Override
	public MotorDeBusqueda cronogramaExportacion(Terminal terminalDestino) {
		List<IViaje> viajesDisponibles = new ArrayList<>();

		for (Naviera naviera : navierasRegistradas) {
			viajesDisponibles.addAll(naviera.getViajes());
		}
		return new MotorDeBusqueda(viajesDisponibles, this, terminalDestino);
	}

	@Override
	public void exportar(IContainer c, Terminal t, IRutaMaritima rm, List<IServicio> servicios, IShipper exportador,
			EmpresaTransportista empresa) throws OperacionNoDisponibleException{ 
		if(!sePuedenInformarImportacionesYExportaciones) {
			throw new OperacionNoDisponibleException("No se pueden informar importaciones o exportaciones en este momento");
		}
		
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
		
		IViaje viaje = rm.getViaje();
		containersPorViaje.put(c, viaje);
	}

	@Override
	public void datosParaElRetiro(IConsignee importador, EmpresaTransportista empresa, IContainer c) throws OperacionNoDisponibleException{ 
		if(!sePuedenInformarImportacionesYExportaciones) {
			throw new OperacionNoDisponibleException("No se pueden informar importaciones o exportaciones en este momento");
		}
		
		IBuque buque = navierasRegistradas.stream().
				flatMap(naviera -> naviera.getBuques().stream())
				.filter(b -> b.getContainers().contains(c))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Contenedor no encontrado en ningún buque registrado"));
		
		IViaje viajeDelContenedor = navierasRegistradas.stream()
			    .flatMap(naviera -> naviera.getViajes().stream())
			    .filter(viaje -> viaje.getBuque().equals(buque) && 
			                     viaje.getBuque().getContainers().contains(c))
			    .findFirst()
			    .orElseThrow(() -> new RuntimeException("Viaje no encontrado"));
				
		
		containersPorViaje.put(c, viajeDelContenedor);

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

	@Override
	public Coordenadas getCoordenadas() {
		
		return ubicacion;
	}

	protected Integer cantidadDeFacturas() {
		return facturas.size();
	}

	public void retiroDeContainer(String patente, String dni, String idContainer, LocalDateTime  fechaDeRetiro, double montoPorDiaExcedente) {
		if(verificarCargaRetiro(patente, dni)) {
			IOrden orden = ordenPorContainer.get(idContainer);
			IContainer container = orden.getDatosDeCarga();
			containers.remove(container);
			IFactura factura = orden.generarFactura(fechaDeRetiro, montoPorDiaExcedente, containersPorViaje.get(container));
			facturas.add(factura);
			orden.getCliente().recibirFactura(factura);
		}
	}

	protected void setSePuedenInformarImportacionesYExportaciones(boolean b) {
		sePuedenInformarImportacionesYExportaciones = b;
	}
	
	protected void setSePuedenRealizarPagos(boolean b) {
		sePuedenRealizarPagos = b;
	}

}
