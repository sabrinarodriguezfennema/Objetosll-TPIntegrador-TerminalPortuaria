package interfaces;

import java.time.LocalDate;
import java.util.Map;

public interface Viaje {

	public LocalDate getFechaInicio();

	public Circuito getCircuito();
	
	public Buque getBuque();
	
	public Map<Terminal, LocalDate> cronograma();
	
	public int precioTotal();
}
