package interfaces;

import java.time.Duration;
import java.util.Date;

import terminal.Terminal;

public interface Circuito {

	public Duration duracionTotal();

	public Double precioTotal();

	public int cantidadDeTerminales();

	public Duration duracionDesde_Hasta_(Terminal terminalOrigen, Terminal terminalDestino);

}
