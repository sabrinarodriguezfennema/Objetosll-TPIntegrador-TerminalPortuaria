package Terminal;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ClasesParaMockear.Viaje;
import Clientes.Cliente;
import Clientes.Shipper;
import InterfacesParaMockear.BuqueViaje;
import InterfacesParaMockear.Container;
import ClasesParaMockear.MotorDeBusqueda;
import InterfacesParaMockear.Naviera;
import InterfacesParaMockear.RutaMarítima;
import InterfacesParaMockear.Servicio;
import Terminal.Terminal;
import Ordenes.Orden;
import Ordenes.OrdenDeExportacion;
import Traslado.EmpresaTransportista;

public class TerminalGestionada extends Terminal implements  GestionLogistica, GestionEnvio{

	private String nombre;
	private String ubicacion;
	private Set<Naviera> navierasRegistradas;
	private Set<Container> containers;
	private Set<EmpresaTransportista> empresasTransportistas;
	private Set<String> camionesRegistrados;
	private Set<String> choferesRegistrados;
	private Set<Cliente> clientesRegistrados;
	private Set<Orden> ordenes;

	public TerminalGestionada(String nombre, String ubicacion) {

		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.navierasRegistradas = new HashSet<>();
		this.containers = new HashSet<>();
		this.ordenes = new HashSet<>();
		this.empresasTransportistas = new HashSet<>();
		this.camionesRegistrados = new HashSet<>();
		this.choferesRegistrados = new HashSet<>();
		this.clientesRegistrados = new HashSet<>();
	
	}

	public void registrarNaviera(Naviera naviera) {
		navierasRegistradas.add(naviera);
	}

	public void registrarOrden(Orden orden) {
		ordenes.add(orden);
	}

	public void registrarContainer(Container container) {
		this.containers.add(container);
	}
	
	public void registrarEmpresaTransportista(EmpresaTransportista empresa) {
		this.empresasTransportistas.add(empresa);
	}
	
	public void registrarCliente(Cliente cliente) {
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
	public boolean verificarCargaLlegada(String patenteCamion, OrdenDeExportacion orden, String dniChofer,LocalDateTime hora) {
		
		boolean validarCamion = 
				this.camionesRegistrados.contains(patenteCamion) && 
				orden.getCamion() == patenteCamion;
		
		boolean validarChofer = 
				this.choferesRegistrados.contains(dniChofer) && 
				orden.getChofer() == dniChofer ;
		
		boolean validarHorario = 
				Duration.between(orden.turno(), hora).toHours() >= 3;
		
		
		return  validarCamion && validarChofer && validarHorario ;
	}

	@Override
	public boolean verificarCargaRetiro(String patenteCamion, String dniChofer) {
		
		return this.camionesRegistrados.contains(patenteCamion) && this.choferesRegistrados.contains(dniChofer);
	}

	@Override
	public void avisoDeSalida(BuqueViaje bv) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void avisoDeLlegada(BuqueViaje bv) {
		// TODO Auto-generated method stub
		
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
	public void exportar(Carga.Container c, Terminal t, RutaMarítima rm, List<Servicio> servicios, Shipper exportador) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void datosParaElRetiro(String patenteCamion, String dniChofer) {
		// TODO Auto-generated method stub
		
	}

}

























