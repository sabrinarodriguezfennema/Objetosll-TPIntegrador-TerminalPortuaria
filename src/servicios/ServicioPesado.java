package servicios;

import interfaces.IContainer;

public class ServicioPesado extends Servicio {
	
	private double costoFijo;
	
	public ServicioPesado(double costoFijo) {
		this.costoFijo = costoFijo;
	}

	@Override
	public double getPrecio(IContainer c) {
		return costoFijo;
	}
}
