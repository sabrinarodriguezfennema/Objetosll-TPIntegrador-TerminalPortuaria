package servicios;

import interfaces.IContainer;

public class ServicioDesconsolidado extends Servicio {
	
	private double precioFijo;
	
	public ServicioDesconsolidado(double precioFijo) {
		this.precioFijo = precioFijo;
		
	}

	@Override
	public double getPrecio(IContainer c) {
		return precioFijo;
	}

}
 