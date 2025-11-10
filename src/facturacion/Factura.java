package facturacion;

import java.util.Set;

import interfaces.IFactura;
import servicios.Servicio;

public class Factura implements IFactura{
	
	private Desglose desglose;
	
	public Factura(Set<Servicio> servicios) {
		
	}
	
	public int montoTotal() {
		return desglose.montoTotal();
	}
}
