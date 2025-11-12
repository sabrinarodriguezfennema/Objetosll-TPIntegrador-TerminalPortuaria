package servicios;

import containers.Container;

public class ServicioPesado extends Servicio {
	
	private double costoFijo;
	
	public ServicioPesado(double costoFijo) {
		this.costoFijo = costoFijo;
	}

	@Override
	public double getPrecio(Container c) {
		return costoFijo;
	}
}
