package servicios;

import containers.Container;

public class ServicioDesconsolidado implements Servicio {
	
	private double precioFijo;
	
	public ServicioDesconsolidado(double precioFijo) {
		this.precioFijo = precioFijo;
		
	}

	@Override
	public double getPrecio(Container c) {
		return precioFijo;
	}

}
