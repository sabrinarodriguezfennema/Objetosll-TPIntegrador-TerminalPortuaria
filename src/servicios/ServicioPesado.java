package servicios;

import containers.Container;

public class ServicioPesado implements Servicio {
	
	private int costoFijo;
	
	public ServicioPesado(int costoFijo) {
		this.costoFijo = costoFijo;
	}

	@Override
	public int getPrecio(Container c) {
		return costoFijo;
	}
}
