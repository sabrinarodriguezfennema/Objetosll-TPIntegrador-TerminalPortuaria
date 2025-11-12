package interfaces;

import java.time.LocalDateTime;
import java.util.Map;

public interface IViaje {

	public LocalDateTime fechaSalida();

	public ICircuito getCircuito();

	public IBuque getBuque();

	public double precioTotal();
	
	public Map<ITerminal, LocalDateTime> cronograma();

	public IRutaMaritima rutaMaritimaDesde_Hasta_(ITerminal t1, ITerminal t2);

}
