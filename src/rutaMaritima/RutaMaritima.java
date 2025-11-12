package rutaMaritima;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import interfaces.Circuito;
import interfaces.Terminal;
import interfaces.Viaje;

public class RutaMaritima {
	
	private Viaje viaje;
	private Circuito circuitoDeViaje;
	private Terminal terminalOrigen;
	private Terminal terminalDestino;
	private Map<Terminal, LocalDate> cronograma;
	
	public RutaMaritima(Viaje viaje, Terminal terminalOrigen, Terminal terminalDestino) {
		this.viaje = viaje;
		this.circuitoDeViaje = viaje.getCircuito();
		this.terminalOrigen = terminalOrigen;
		this.terminalDestino = terminalDestino;
		this.cronograma = viaje.cronograma();
	} 
	
	private void validarTerminal(Terminal t) {
		if (!perteneceA(t)) {
			throw new IllegalArgumentException("la terminal no pertenece al circuito del viaje");
		}
	}
	
	public Terminal puertoDestino() {
		validarTerminal(terminalDestino);
		return terminalDestino;
	}
	
	public LocalDate fechaSalida() {
		validarTerminal(terminalOrigen);
		return cronograma.get(terminalOrigen);
	}
	
	public LocalDate fechaLlegada() {
		validarTerminal(terminalDestino);
		return cronograma.get(terminalDestino);
	}
	
	public boolean perteneceA(Terminal t) {
		List<Terminal> terminales = circuitoDeViaje.getTodasLasTerminales();
		return terminales.contains(t);
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