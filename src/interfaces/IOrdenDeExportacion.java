package interfaces;

import java.time.LocalDate;
import java.time.LocalDateTime;

import terminal.Terminal;


public interface IOrdenDeExportacion extends IOrden{

	public String getCamion();

	public String getChofer();

	public LocalDateTime turno();

	public LocalDate getFechaSalida();
	
	public LocalDate getFechaLlegada();

	public Terminal getTerminalDestino();

	public Container getDatosDeCarga();

}
