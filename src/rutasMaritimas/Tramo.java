package rutasMaritimas;

public class Tramo {
	
	int duracion;
	int precio;
	int inicio;
	int destino;
	
	public Tramo(int inicio, int destino, int duracion, int precio) {
		this.inicio = inicio;
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

}
