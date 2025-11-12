package interfaces;

import java.time.Duration;

public interface ITramo {
	
	public Duration getDuracion();
	public int getPrecio();
	public ITerminal getOrigen();
	public ITerminal getDestino();

}
