package servicios;

import java.time.LocalDate;

import containers.Container;

public abstract class Servicio {
	
	LocalDate fecha;
	
	public abstract double getPrecio(Container c);
	
	public LocalDate getFecha() { return fecha; }

}
