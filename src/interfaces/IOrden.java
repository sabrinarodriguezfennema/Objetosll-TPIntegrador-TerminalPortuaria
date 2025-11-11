package interfaces;

import java.time.LocalDate;
import java.time.LocalDateTime;

import terminal.TerminalGestionada;

public interface IOrden {

	void registrarEn(TerminalGestionada terminalGestionada);
	
	public String getChofer();
	
	public String getCamion();
	
	public Container getDatosDeCarga();

	public ICliente getCliente();
	
	public IFactura generarFactura(LocalDateTime fecha, double montoPorDíaExcedente, IViaje viaje);

}
