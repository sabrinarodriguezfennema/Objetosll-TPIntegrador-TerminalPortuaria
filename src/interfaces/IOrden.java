package interfaces;

import java.time.LocalDate;

import terminal.TerminalGestionada;

public interface IOrden {

	void registrarEn(TerminalGestionada terminalGestionada);
	
	public String getChofer();
	
	public String getCamion();
	
	public Container getDatosDeCarga();

	public IFactura generarFactura(LocalDate fecha, double montoPorDíaExcedente, Viaje viaje);

	public ICliente getCliente();

}
