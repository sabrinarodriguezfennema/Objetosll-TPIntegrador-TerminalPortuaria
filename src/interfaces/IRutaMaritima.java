package interfaces;

import java.time.LocalDateTime;

public interface IRutaMaritima {

	public LocalDateTime fechaSalida();

	public LocalDateTime fechaLlegada();

	public ITerminal puertoDestino();

	public ICircuito getCircuito();

	public IViaje getViaje();


}
