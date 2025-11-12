package tramo;

import java.time.Duration;


import interfaces.ITerminal;
import interfaces.ITramo;

public class Tramo implements ITramo{
	
	private Duration duracion;
	private int precio;
	private ITerminal origen;
	private ITerminal destino;
	
	public Tramo(ITerminal origen, ITerminal destino, Duration duracion, int precio) {
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
	
	public ITerminal getOrigen() {
		return origen;		
	}
	
	public ITerminal getDestino() {
		return destino;		
	}

}
