package interfaces;

import java.time.Duration;
import java.util.List;


public interface ICircuito {

	public Duration duracionTotal();

	public double precioTotal();

	public int cantidadDeTerminales();
	
	public List<ITerminal> getTodasLasTerminales();

	public Duration duracionDesde_Hasta_(ITerminal terminalOrigen, ITerminal terminalDestino);

	public List<ITerminal> getTodasLasTerminalesDestino();

	public List<ITramo> getTramos();

}
