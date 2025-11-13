package servicios;

import java.time.LocalDateTime;

import interfaces.IContainer;
import interfaces.IServicio;

public abstract class Servicio implements IServicio{
	
	LocalDateTime fecha;
	
	public abstract double getPrecio(IContainer c);
	
	public LocalDateTime getFecha() { return fecha; }

}
