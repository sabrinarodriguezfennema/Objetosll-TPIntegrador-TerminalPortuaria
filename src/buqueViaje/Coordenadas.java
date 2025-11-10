package buqueViaje;

public class Coordenadas {
	double latitud;
	double longitud;
	
	public Coordenadas(double latitud, double longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}
	
	public double distanciaA(Coordenadas cs) {
		return Math.abs(latitud - cs.getLatitud()) + Math.abs(longitud - cs.getLongitud());
	}

	public double getLatitud() {
		return latitud;
	}
	
	public double getLongitud() {
		return longitud;
	}
}
