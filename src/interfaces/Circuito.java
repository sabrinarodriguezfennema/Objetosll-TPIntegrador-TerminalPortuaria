package interfaces;

import java.time.Duration;
import java.util.List;


public interface Circuito {

	public Duration duracionTotal();

	public double precioTotal();

	public int cantidadDeTerminales();
	
	public List<Terminal> getTodasLasTerminales();

	public Duration duracionDesde_Hasta_(Terminal terminalOrigen, Terminal terminalDestino);

	public List<Terminal> getTodasLasTerminalesDestino();

	public List<Tramo> getTramos();

}
