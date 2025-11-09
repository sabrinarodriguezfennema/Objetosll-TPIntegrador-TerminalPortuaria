package Ordenes;

import java.time.temporal.Temporal;

import Carga.Container;
import Clientes.Cliente;

public abstract class Orden {

	
	protected String patenteCamion;
	protected Container datosDeCarga;
	protected String dniChofer;

	public Orden(Container datosDeCarga, String patenteCamion, String dniChofer) {
		this.datosDeCarga = datosDeCarga;
		this.patenteCamion = patenteCamion;
		this.dniChofer = dniChofer;
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

	
	
}
