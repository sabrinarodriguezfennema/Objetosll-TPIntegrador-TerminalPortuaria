package ordenes;

import interfaces.Container;
import terminal.TerminalGestionada;

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


	public abstract void registrarEn(TerminalGestionada terminalGestionada);

	
	
}
