package viaje;

import java.time.Duration;


import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import interfaces.Buque;
import interfaces.Circuito;
import interfaces.Terminal;
import interfaces.Tramo;

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
	
	public Map<Terminal, LocalDate> cronograma() {
        Map<Terminal, LocalDate> cronograma = new LinkedHashMap<>();

        List<Tramo> tramos = circuito.getTramos();

        LocalDate fechaActual = fechaInicio;

        if (!tramos.isEmpty()) {
            cronograma.put(tramos.get(0).getOrigen(), fechaActual);
        }
        
        // recorremos los tramos sumando la duración de cada tramo y agregandolos al map 
        for (Tramo tramo : tramos) {
            Duration duracion = tramo.getDuracion();
            fechaActual = fechaActual.plusDays(duracion.toDays()); // sumamos a la fecha actual los dias estimados de llegada al destino del tramo
            cronograma.put(tramo.getDestino(), fechaActual);
        }
        return cronograma;
    }
	
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	
	public Circuito getCircuito() {
		return circuito;
	}
	
	public double precioTotal() {
	    return circuito.precioTotal();
	}
}
