package paraMock;

import java.util.Date;


import interfaces.Circuito;

import interfaces.Terminal;

public interface RutaMaritima {
	
	public Terminal puertoDestino();
	
	public Date fechaSalida();
	
	public Date fechaLlegada();

	public Circuito getCircuito();


}
