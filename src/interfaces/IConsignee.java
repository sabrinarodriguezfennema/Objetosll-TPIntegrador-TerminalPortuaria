package interfaces;

import java.time.LocalDateTime;

public interface IConsignee extends ICliente {

	void recibirMail(String string);
	
	public void fechaDeImportacion(LocalDateTime turno);

	public LocalDateTime getFechaDeImportacion();
	
}
