package rutasMaritimas;

import java.time.Duration;

public class Tramo {
	
	private Duration duracion;
	private int precio;
	private Terminal origen;
	private Terminal destino;
	
	public Tramo(Terminal origen, Terminal destino, Duration duracion, int precio) {
		this.origen = origen;
		this.destino = destino;
		this.duracion = duracion;
		this.precio = precio;
	}
	
	public Duration getDuracion() {
		return duracion;
	}
	
	public int getPrecio() {
		return precio;
	}
	
	public Terminal getOrigen() {
		return origen;		
	}
	
	public Terminal getDestino() {
		return destino;		
	}

}
