package interfaces;

import java.util.List;

public interface ICliente {

	public void recibirMail(String contenido);

	public String getNombre();

	public String getEmail();

	public List<String> getMailsRecibidos();

	public void recibirFactura(IFactura factura);
	
	public List<IFactura> getFacturasRecibidas();
}
