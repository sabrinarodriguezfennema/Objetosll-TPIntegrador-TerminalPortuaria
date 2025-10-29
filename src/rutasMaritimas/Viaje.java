package rutasMaritimas;

import java.time.LocalDate;

public class Viaje {
	
	private LocalDate fechaInicio;
	private Buque buque;
	private Circuito circuito;
	
	public Viaje(LocalDate  fechaInicio, Buque buque,Circuito circuito) {
		this.fechaInicio = fechaInicio;
		this.buque = buque;
		this.circuito = circuito;
	}
	
	public Buque getBuque() {
		return buque;
	}
	
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	
	public Circuito getCircuito() {
		return circuito;
	}


}
