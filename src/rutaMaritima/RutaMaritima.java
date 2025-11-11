package rutaMaritima;

import java.time.Duration;

import java.time.LocalDate;
import java.util.List;

import circuito.Circuito;
import terminal.Terminal;
import viaje.Viaje;

public class RutaMaritima {
	
	private Viaje viaje;   // tuve que agregar viaje ya que con circuito no puedo obtener la fecha de inicio entonces no podria calcular la fecha limite
	private Circuito circuitoDeViaje;
	
	public RutaMaritima(Viaje viaje) {
		this.viaje = viaje;
		this.circuitoDeViaje = viaje.getCircuito();
	}
	
	public boolean contienePuertoDestino(Terminal destino) {
		List<Terminal> terminales = circuitoDeViaje.getTodasLasTerminalesDestino();
		return terminales.contains(destino);
	}
		
	public LocalDate fechaEstimadaDeLlegada() {
		Duration duracionTotal = circuitoDeViaje.duracionTotal();
		int dias = (int) duracionTotal.toDays();
		return viaje.getFechaInicio().plusDays(dias);
	}
		
	public boolean llegaAntesDe(LocalDate fechaLimite) {
		return fechaEstimadaDeLlegada().isBefore(fechaLimite);
	}
		
	public boolean saleDespuesDe(LocalDate fechaInicio) {
		return viaje.getFechaInicio().isAfter(fechaInicio);
	}
}
