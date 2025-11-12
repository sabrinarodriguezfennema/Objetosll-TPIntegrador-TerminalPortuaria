package rutaMaritima;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import interfaces.ICircuito;
import interfaces.ITerminal;
import interfaces.IViaje;

public class RutaMaritima {
	
	private IViaje viaje;
	private ICircuito circuitoDeViaje;
	private ITerminal terminalOrigen;
	private ITerminal terminalDestino;
	private Map<ITerminal, LocalDate> cronograma;
	
	public RutaMaritima(IViaje viaje, ITerminal terminalOrigen, ITerminal terminalDestino) {
		this.viaje = viaje;
		this.circuitoDeViaje = viaje.getCircuito();
		this.terminalOrigen = terminalOrigen;
		this.terminalDestino = terminalDestino;
		this.cronograma = viaje.cronograma();
	} 
	
	private void validarTerminal(ITerminal t) {
		if (!perteneceARuta(t)) {
			throw new IllegalArgumentException("la terminal no pertenece al circuito del viaje");
		}
	}
	
	public ITerminal puertoDestino() {
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
	
	public boolean perteneceARuta(ITerminal t) {
		List<ITerminal> terminales = circuitoDeViaje.getTodasLasTerminales();
		return terminales.contains(t);
	}

	public ICircuito getCircuito() {
		return circuitoDeViaje;
	}
	
	public IViaje getViaje() {
		return viaje;
	}

	public String getNombreDeBuque() {
		return viaje.getBuque().getNombre();
	}
}