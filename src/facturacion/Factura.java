package facturacion;

import java.util.Set;

import interfaces.IFactura;
import servicios.Servicio;

public class Factura implements IFactura{
	
	private Desglose desglose;
	
	public Factura(Set<Servicio> servicios) {
		desglose = new Desglose();
	}
	
	public int montoTotal() {
		return desglose.montoTotal();
	}
}
