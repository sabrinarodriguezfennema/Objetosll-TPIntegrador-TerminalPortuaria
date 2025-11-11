package facturacion;

import java.util.HashSet;
import java.util.Set;

import interfaces.IFactura;
import servicios.Servicio;

public class Factura implements IFactura{
	
	private Desglose desglose;
	private Set<Servicio> servicios;
	
	public Factura(Set<Servicio> servicios) {
		this.servicios = new HashSet<Servicio>(); 
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
