package servicios;

import containers.Container;

public class ServicioRevisionDiaria implements Servicio {
	
	private int precioFijo; 
	private int diasEnLaTerminal;  // TODAVIA FALTA CALCULAR LA CANTIDAD DE DIAS
	
	public ServicioRevisionDiaria(int precioFijo, int diasEnLaTerminal ) {
		this.precioFijo = precioFijo;
		this.diasEnLaTerminal = diasEnLaTerminal;
		
	}

	@Override
	public int getPrecio(Container c) {
		return precioFijo * diasEnLaTerminal;
	}

}
