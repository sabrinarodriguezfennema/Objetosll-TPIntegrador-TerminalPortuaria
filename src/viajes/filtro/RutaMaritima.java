package viajes.filtro;

import java.util.Date;

import interfaces.Circuito;

public interface RutaMaritima {
	
	public Terminal puertoDestino();
	
	public Date fechaSalida();
	
	public Date fechaLlegada();

	public Circuito getCircuito();


}
