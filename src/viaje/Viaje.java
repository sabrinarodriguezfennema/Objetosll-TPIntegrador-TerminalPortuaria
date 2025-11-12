package viaje;

import java.time.Duration;


import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import interfaces.Buque;
import interfaces.ICircuito;
import interfaces.IBuque;
import interfaces.IViaje;
import interfaces.ITerminal;
import interfaces.ITramo;

public class Viaje implements IViaje{
	
	private LocalDate fechaInicio;
	private IBuque buque;
	private ICircuito circuito;
	
	
	public Viaje(LocalDate fechaInicio, IBuque buque,ICircuito circuito) {
		this.fechaInicio = fechaInicio;
		this.buque = buque;
		this.circuito = circuito;
	}
	
	public IBuque getBuque() {
		return buque;
	}
	
	public Map<ITerminal, LocalDate> cronograma() {
        Map<ITerminal, LocalDate> cronograma = new LinkedHashMap<>();

        List<ITramo> tramos = circuito.getTramos();

        LocalDate fechaActual = fechaInicio;

        if (!tramos.isEmpty()) {
            cronograma.put(tramos.get(0).getOrigen(), fechaActual);
        }
        
        // recorremos los tramos sumando la duración de cada tramo y agregandolos al map 
        for (ITramo tramo : tramos) {
            Duration duracion = tramo.getDuracion();
            fechaActual = fechaActual.plusDays(duracion.toDays()); // sumamos a la fecha actual los dias estimados de llegada al destino del tramo
            cronograma.put(tramo.getDestino(), fechaActual);
        }
        return cronograma;
    }
	
	public LocalDate getFechaSalida() {
		return fechaInicio;
	}
	
	public ICircuito getCircuito() {
		return circuito;
	}
	
	public double precioTotal() {
	    return circuito.precioTotal();
	}

	@Override
	public LocalDate fechaSalida() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalDate getFechaInicio() {
		// TODO Auto-generated method stub
		return null;
	}
}
