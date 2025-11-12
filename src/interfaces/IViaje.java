package interfaces;

import java.time.LocalDate;

public interface IViaje {

	public LocalDate fechaSalida();

	public Circuito getCircuito();

	public IBuque getBuque();

	public double precioTotal();

	public LocalDate getFechaInicio();


}
