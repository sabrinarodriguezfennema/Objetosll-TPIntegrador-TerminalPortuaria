package facturacion;

import java.util.HashSet;
import java.util.Set;

import interfaces.IFactura;
import servicios.Servicio;

public class Factura implements IFactura{
	
	private Desglose desglose;
	private Set<Servicio> servicios;
	
	public Factura(Set<Servicio> servicios, Desglose desglose) {
		this.servicios = new HashSet<Servicio>();
		this.desglose = desglose;
	}
	
	public int montoTotal() {
		return desglose.montoTotal();
	}
	
	public Desglose getDesglose() {
		return desglose;
	}
	
	public void agregarServicio(Servicio s) {
		servicios.add(s);
	}
}
