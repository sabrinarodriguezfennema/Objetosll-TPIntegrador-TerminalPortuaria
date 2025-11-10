package ordenes;

import clientes.Cliente;
import interfaces.Container;
import interfaces.ICliente;
import interfaces.IOrden;
import terminal.TerminalGestionada;

public abstract class Orden implements IOrden{

	
	protected String patenteCamion;
	protected Container datosDeCarga;
	protected String dniChofer;
	protected ICliente cliente;

	public Orden(Container datosDeCarga, String patenteCamion, String dniChofer, ICliente cliente) {
		this.datosDeCarga = datosDeCarga;
		this.patenteCamion = patenteCamion;
		this.dniChofer = dniChofer;
		this.cliente = cliente;
	}


	public String getCamion() {
		return patenteCamion;
	}

	public Container getDatosDeCarga() {
		return datosDeCarga;
	}

	public String getChofer() {
		return dniChofer;
	}
	
	public ICliente getCliente() {
		return this.cliente;
	}


	public abstract void registrarEn(TerminalGestionada terminalGestionada);

	
	
}
