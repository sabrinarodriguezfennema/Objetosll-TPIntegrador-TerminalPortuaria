package paraMock;

import java.util.Date;


import interfaces.ICircuito;

import interfaces.ITerminal;

public interface RutaMaritima {
	
	public ITerminal puertoDestino();
	
	public Date fechaSalida();
	
	public Date fechaLlegada();

	public ICircuito getCircuito();


}
