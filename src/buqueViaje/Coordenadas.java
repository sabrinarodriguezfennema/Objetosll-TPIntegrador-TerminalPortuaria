package buqueViaje;

public class Coordenadas {
	double coordenadaX;
	double coordenadaY;
	
	public Coordenadas(double x, double y) {
		this.coordenadaX = x;
		this.coordenadaY = y;
	}
	
	public double distanciaA(Coordenadas cs) {
		return Math.abs(coordenadaX - cs.getX()) + Math.abs(coordenadaY - cs.getY());
	}

	public double getX() {
		return coordenadaX;
	}
	
	public double getY() {
		return coordenadaY;
	}
}
