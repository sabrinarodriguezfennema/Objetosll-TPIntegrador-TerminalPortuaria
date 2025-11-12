package servicios;

import java.time.LocalDate;

import interfaces.IContainer;
import interfaces.IServicio;

public abstract class Servicio implements IServicio{
	
	LocalDate fecha;
	
	public abstract double getPrecio(IContainer c);
	
	public LocalDate getFecha() { return fecha; }

}
