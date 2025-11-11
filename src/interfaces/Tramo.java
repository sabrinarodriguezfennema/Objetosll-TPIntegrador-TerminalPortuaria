package interfaces;

import java.time.Duration;

public interface Tramo {
	
	public Duration getDuracion();
	public int getPrecio();
	public Terminal getOrigen();
	public Terminal getDestino();

}
