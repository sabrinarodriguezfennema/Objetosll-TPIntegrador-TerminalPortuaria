package interfaces;

import java.time.LocalDateTime;

public interface Shipper extends Cliente{

	void fechaDeExportacion(LocalDateTime turno);

	void recibirMail(String string);


}
