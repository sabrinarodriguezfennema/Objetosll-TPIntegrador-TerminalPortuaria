package rutaMaritima;

import java.time.Duration;

import java.time.LocalDate;

import interfaces.Circuito;
import interfaces.Terminal;
import interfaces.Viaje;

public class RutaMaritima {
	
	private Viaje viaje;   // tuve que agregar viaje ya que con circuito no puedo obtener la fecha de inicio entonces no podria calcular la fecha limite
	private Circuito circuitoDeViaje;
	private Terminal terminal;
	
	public RutaMaritima(Viaje viaje, Terminal terminal) {
		this.viaje = viaje;
		this.circuitoDeViaje = viaje.getCircuito();
		this.terminal = terminal;
	} 
	
    public Terminal puertoDestino() {
    	return terminal;
    }
	
	public LocalDate fechaSalida() {
		return viaje.getFechaInicio();
	}
	
	public LocalDate fechaLlegada() {
		Duration duracionTotal = circuitoDeViaje.duracionTotal();
		int dias = (int) duracionTotal.toDays();
		return viaje.getFechaInicio().plusDays(dias);
		
	}

	public Circuito getCircuito() {
		return circuitoDeViaje;
	}
	
	public Viaje getViaje() {
		return viaje;
	}

	public String getNombre() {
		return viaje.getBuque().getNombre();
	}
}
