package interfaces;

import java.time.LocalDate;

public interface Viaje {

	public LocalDate getFechaInicio();

	public Circuito getCircuito();
	
	public Buque getBuque();
}
