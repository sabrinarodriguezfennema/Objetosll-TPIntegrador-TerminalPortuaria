package interfaces;

import java.time.LocalDateTime;

public interface IShipper extends ICliente {

	public void fechaDeExportacion(LocalDateTime turno);
	
	public LocalDateTime getFechaDeExportacion();

}
