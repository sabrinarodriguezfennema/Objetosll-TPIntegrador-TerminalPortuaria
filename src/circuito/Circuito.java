package circuito;

import java.time.Duration;


import interfaces.Terminal;
import java.util.ArrayList;
import java.util.List;

import interfaces.Tramo;

public class Circuito {
	
	private List<Tramo> tramos = new ArrayList<>();
	private List <Terminal> terminales = new ArrayList<>();
	
	public Circuito() {}
			
	public void agregarTramo(Tramo t) {
		
		int cantTramos = tramos.size();
		
		if (tramos.isEmpty()) {
			tramos.add(t);
		} else {
			Tramo ultimo = tramos.get(cantTramos - 1);  // SI EL TRAMO-ORIGEN NO ES IGUAL AL ULTIMO TRAMO , NO SERIA UN CIRCUITO CONTINUO.
			if (!(ultimo.getDestino() == t.getOrigen())) { // CADA TRAMO INICIA CON EL DESTINO DEL ANTERIOR, MENOS EL INICIO DEL CIRCUITO
				throw new IllegalArgumentException("el tramo no es el mismo que el origen");
			}
			tramos.add(t);
		}
		this.agregarTerminalOrigenYDestinoDe(t);
	}
		
	public void agregarTerminalOrigenYDestinoDe(Tramo t) { 
		if (!terminales.contains(t.getOrigen())) terminales.add(t.getOrigen());
		if (!terminales.contains(t.getDestino())) terminales.add(t.getDestino());
	}
	
	public List<Tramo> getTramos() {
		return tramos;
	}
	
	public int precioTotal() {
		int total = 0;
		
		for (Tramo tramo: tramos) {
			total += tramo.getPrecio();
		}
		return total;
	}
	
	public Duration duracionTotal() {
		Duration total = Duration.ZERO;
		
		for (Tramo t: tramos) {
			total = total.plus(t.getDuracion());
		}
		return total;
	}
	
	public Duration duracionDe(Tramo t) {
		validarTramoPerteneciente(t);
        return t.getDuracion();
	}
	
	public int precioDe(Tramo t) {
		validarTramoPerteneciente(t);
        return t.getPrecio(); 
	}
	
	public void validarTramoPerteneciente(Tramo t) {
		if (!tramos.contains(t)) {
        	throw new IllegalArgumentException("El tramo no pertenece al circuito");
		}
	}

	public List<Terminal> getTodasLasTerminales() { 
		return terminales;
	}
	
	public int cantidadDeTerminales() {
		return terminales.size();
	}
	
	public List<Terminal> getTodasLasTerminalesDestino() {
		 
		List<Terminal> terminalesDestino = new ArrayList<>(); 
		
		for (Tramo t: tramos) {
			terminalesDestino.add(t.getDestino());
		}
		return terminalesDestino;
	}
}
