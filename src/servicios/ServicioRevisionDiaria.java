package servicios;

import interfaces.IContainer;

public class ServicioRevisionDiaria extends Servicio {
	
	private double precioFijo; 
	private int diasEnLaTerminal;  // TODAVIA FALTA CALCULAR LA CANTIDAD DE DIAS
	
	public ServicioRevisionDiaria(double precioFijo, int diasEnLaTerminal) {
		this.precioFijo = precioFijo;
		this.diasEnLaTerminal = diasEnLaTerminal;
		
	}

	@Override
	public double getPrecio(IContainer c) {
		return precioFijo * diasEnLaTerminal;
	}

}
