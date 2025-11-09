package interfaces;

import java.time.LocalDate;

import terminal.Terminal;

public interface RutaMaritima {

	public LocalDate fechaSalida();

	public LocalDate fechaLlegada();

	public Terminal puertoDestino();

	public Circuito getCircuito();

	public String getNombre();


}
