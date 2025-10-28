package rutasMaritimas;

import java.util.Date;

public class Viaje {
	
	Date fechaInicio;
	Buque buque;
	Circuito circuito;
	
	public Viaje(Date fechaInicio, Buque buque) {
		this.fechaInicio = fechaInicio;
		this.buque = buque;
	}
	
	public Buque getBuque() {
		return buque;
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	
	public Circuito getCircuito() {
		return circuito;
	}


}
