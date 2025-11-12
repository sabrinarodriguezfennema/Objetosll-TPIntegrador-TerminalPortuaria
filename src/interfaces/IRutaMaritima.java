package interfaces;

import java.time.LocalDate;

import terminal.Terminal;

public interface IRutaMaritima {

	public LocalDate fechaSalida();

	public LocalDate fechaLlegada();

	public Terminal puertoDestino();

	public ICircuito getCircuito();

	public String getNombre();

	public IViaje getViaje();


}
