package interfaces;

import java.time.LocalDate;
import java.util.Map;

public interface IViaje {

	public LocalDate fechaSalida();

	public ICircuito getCircuito();

	public IBuque getBuque();

	public double precioTotal();
	
	public Map<ITerminal, LocalDate> cronograma();

}
