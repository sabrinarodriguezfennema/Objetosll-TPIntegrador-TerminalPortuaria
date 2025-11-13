package interfaces;


import java.time.LocalDateTime;




public interface IOrdenDeExportacion extends IOrden{

	public String getCamion();

	public String getChofer();

	public LocalDateTime turno();

	public LocalDateTime getFechaSalida();
	
	public LocalDateTime getFechaLlegada();

	public ITerminal getTerminalDestino();

	public IContainer getDatosDeCarga();

}
