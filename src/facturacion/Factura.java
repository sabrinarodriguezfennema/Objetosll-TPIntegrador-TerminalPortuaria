package facturacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import containers.Container;
import interfaces.IFactura;
import interfaces.Viaje;
import servicios.Servicio;

public class Factura implements IFactura {
	
	private Set<Servicio> servicios;
	private List<ItemDesglose> desglose;
	private Viaje viaje;
	
	public Factura(Set<Servicio> servicios) {
		this.servicios = servicios;
		this.desglose = new ArrayList<>();
	}
	
	public Factura(Set<Servicio> servicios, Viaje viaje) {
		this.servicios = servicios;
		this.viaje = viaje;
		this.desglose = new ArrayList<>();
	}
	
	public List<ItemDesglose> desglose() {
		
	    Container c = null;
	    
	    for (Servicio servicio : servicios) {
	        String detalle = "Servicio";
	        double monto = servicio.getPrecio(c);
	        LocalDate fecha = servicio.getFecha();
	        
	        ItemDesglose item = new ItemDesglose(detalle, monto, fecha);
	        desglose.add(item);
	    }
	    
	    if (viaje != null) {
            String detalle = "Viaje:";
            double monto = viaje.precioTotal();
            LocalDate fecha = viaje.getFechaInicio();

            ItemDesglose itemViaje = new ItemDesglose(detalle, monto, fecha);
            desglose.add(itemViaje);
        }
	    return desglose;
	}

	public double montoTotal() {
		return desglose.stream().mapToDouble(ItemDesglose::getMonto).sum();
	}
}

	
