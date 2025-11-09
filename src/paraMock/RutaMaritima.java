package paraMock;

import java.util.Date;

import interfaces.Terminal;

public interface RutaMaritima {
	
	public Terminal puertoDestino();
	
	public Date fechaSalida();
	
	public Date fechaLlegada();
}
