package ordenes;

import java.util.HashSet;
import java.util.Set;

import interfaces.IContainer;
import interfaces.ICliente;
import interfaces.IOrden;
import interfaces.IServicio;
import terminal.TerminalGestionada;

public abstract class Orden implements IOrden{

	
	protected String patenteCamion;
	protected IContainer datosDeCarga;
	protected String dniChofer;
	protected ICliente cliente;
	protected Set<IServicio> servicios;

	public Orden(IContainer datosDeCarga, String patenteCamion, String dniChofer, ICliente cliente) {
		this.datosDeCarga = datosDeCarga;
		this.patenteCamion = patenteCamion;
		this.dniChofer = dniChofer;
		this.cliente = cliente;
		this.servicios = new HashSet<IServicio>();
	}


	public String getCamion() {
		return patenteCamion;
	}

	public IContainer getDatosDeCarga() {
		return datosDeCarga;
	}

	public String getChofer() {
		return dniChofer;
	}
	
	public ICliente getCliente() {
		return this.cliente;
	}

	public abstract void registrarEn(TerminalGestionada terminalGestionada);

	public void agregarServicio(IServicio s) {
		servicios.add(s);
	}
	
	protected Set<IServicio> getServicios() {
		return new HashSet<>(servicios);
	}
	
}
