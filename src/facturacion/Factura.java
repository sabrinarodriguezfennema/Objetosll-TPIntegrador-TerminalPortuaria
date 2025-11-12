package facturacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import interfaces.IContainer;
import interfaces.IFactura;
import interfaces.IViaje;
import interfaces.IServicio;

public class Factura implements IFactura {
	
	private Set<IServicio> servicios;
	private List<ItemDesglose> desglose;
	private IViaje viaje;
	
	public Factura(Set<IServicio> servicios) {
		this.servicios = servicios;
		this.desglose = new ArrayList<>();
	}
	
	public Factura(Set<IServicio> servicios, IViaje viaje) {
		this.servicios = servicios;
		this.viaje = viaje;
		this.desglose = new ArrayList<>();
	}
	
	public List<ItemDesglose> desglose() {
		
	    IContainer c = null;
	    
	    for (IServicio servicio : servicios) {
	        String detalle = "Servicio";
	        double monto = servicio.getPrecio(c);
	        LocalDate fecha = servicio.getFecha();
	        
	        ItemDesglose item = new ItemDesglose(detalle, monto, fecha);
	        desglose.add(item);
	    }
	    
	    if (viaje != null) {
            String detalle = "Viaje:";
            double monto = viaje.precioTotal();
            LocalDate fecha = viaje.fechaSalida();

            ItemDesglose itemViaje = new ItemDesglose(detalle, monto, fecha);
            desglose.add(itemViaje);
        }
	    return desglose;
	}

	public double montoTotal() {
		return desglose.stream().mapToDouble(ItemDesglose::getMonto).sum();
	} //terminando factura
}

	
