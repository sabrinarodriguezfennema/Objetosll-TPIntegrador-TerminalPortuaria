package rutaMaritima;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Map;

import interfaces.ICircuito;
import interfaces.IRutaMaritima;
import interfaces.ITerminal;
import interfaces.IViaje;

public class RutaMaritima implements IRutaMaritima{
	
	private IViaje viaje;
	private ICircuito circuitoDeViaje;
	private ITerminal terminalOrigen;
	private ITerminal terminalDestino;
	private Map<ITerminal, LocalDateTime> cronograma;
	
	public RutaMaritima(IViaje viaje, ITerminal terminalOrigen, ITerminal terminalDestino) {
		this.viaje = viaje;
		this.circuitoDeViaje = viaje.getCircuito();
		this.terminalOrigen = terminalOrigen;
		this.terminalDestino = terminalDestino;
		this.cronograma = viaje.cronograma();
		validarTerminal(terminalOrigen);
		validarTerminal(terminalDestino);
	} 
	
	private void validarTerminal(ITerminal t) {
		if (!perteneceARuta(t)) {
			throw new IllegalArgumentException("la terminal no pertenece al circuito del viaje");
		} 
	}
	
	public ITerminal puertoDestino() {
		return terminalDestino;
	}
	
	public LocalDateTime fechaSalida() {
		return cronograma.get(terminalOrigen);
	}
	
	public LocalDateTime fechaLlegada() {
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



}