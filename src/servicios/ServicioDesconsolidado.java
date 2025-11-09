package servicios;

import containers.Container;

public class ServicioDesconsolidado implements Servicio {
	
	private int precioFijo;
	
	public ServicioDesconsolidado(int precioFijo) {
		this.precioFijo = precioFijo;
		
	}

	@Override
	public int getPrecio(Container c) {
		// TODO Auto-generated method stub
		return precioFijo;
	}

}
