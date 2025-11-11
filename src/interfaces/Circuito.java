package interfaces;

import java.time.Duration;
import java.util.List;


public interface Circuito {

	public Duration duracionTotal();

	public Double precioTotal();

	public int cantidadDeTerminales();

	public Duration duracionDesde_Hasta_(Terminal terminalOrigen, Terminal terminalDestino);

	public List<Terminal> getTodasLasTerminalesDestino();

	public List<Tramo> getTramos();

}
