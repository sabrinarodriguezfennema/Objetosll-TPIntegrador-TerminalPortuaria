package rutasMaritimas;

public class Tramo {
	
	private int duracion;
	private int precio;
	private Terminal origen;
	private Terminal destino;
	
	public Tramo(Terminal origen, Terminal destino, int duracion, int precio) {
		this.origen = origen;
		this.destino = destino;
		this.duracion = duracion;
		this.precio = precio;
	}
	
	public int getDuracion() {
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
