package servicios;

import containers.Container;

public class ServicioLavado implements Servicio {
	
	private int montoSupera70m3;
	private int montoDebajo70m3;
	
	public ServicioLavado(int montoSupera70m3,int montoDebajo70m3 ) {
		this.montoSupera70m3 = montoSupera70m3;
		this.montoDebajo70m3 = montoSupera70m3;
	}

	@Override
	public int getPrecio(Container c) {
		return c.volumen() > 70 ? montoSupera70m3 : montoDebajo70m3;
	}

}
