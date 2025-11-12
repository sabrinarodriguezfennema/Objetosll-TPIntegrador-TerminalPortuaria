package viaje;

import java.time.Duration;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import interfaces.ICircuito;
import interfaces.IRutaMaritima;
import interfaces.IBuque;
import interfaces.IViaje;
import rutaMaritima.RutaMaritima;
import interfaces.ITerminal;
import interfaces.ITramo;

public class Viaje implements IViaje{
	
	private LocalDateTime fechaSalida;
	private IBuque buque;
	private ICircuito circuito;
	
	
	public Viaje(LocalDateTime fechaSalida, IBuque buque,ICircuito circuito) {
		this.fechaSalida = fechaSalida;
		this.buque = buque;
		this.circuito = circuito;
	}
	
	public IBuque getBuque() {
		return buque;
	}
	
	public Map<ITerminal, LocalDateTime> cronograma() {
        Map<ITerminal, LocalDateTime> cronograma = new LinkedHashMap<>();

        List<ITramo> tramos = circuito.getTramos();

        LocalDateTime fechaActual = fechaSalida;

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
	
	
	public ICircuito getCircuito() {
		return circuito;
	}
	
	public double precioTotal() {
	    return circuito.precioTotal();
	}

	@Override
	public LocalDateTime fechaSalida() {
		return fechaSalida;
	}

	@Override
	public IRutaMaritima rutaMaritimaDesde_Hasta_(ITerminal t1, ITerminal t2) {
		return new RutaMaritima(this, t1, t2);
	}

	
}
