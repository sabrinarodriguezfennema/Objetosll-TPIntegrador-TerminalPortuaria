package interfaces;

import java.time.LocalDate;

public interface IRutaMaritima {

	public LocalDate fechaSalida();

	public LocalDate fechaLlegada();

	public ITerminal puertoDestino();

	public ICircuito getCircuito();

	public IViaje getViaje();


}
