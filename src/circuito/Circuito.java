package circuito;

import java.time.Duration;

import interfaces.ICircuito;
import interfaces.ITerminal;
import java.util.ArrayList;
import java.util.List;

import interfaces.ITramo;

public class Circuito implements ICircuito{
	
	private List<ITramo> tramos;
	private List <ITerminal> terminales;
	
	public Circuito(List<ITramo> tramos) {
		this.tramos = tramos;
		this.terminales = new ArrayList<>();
	}
			
	public void agregarTramo(ITramo t) {
		
		int cantTramos = tramos.size();
		
		if (tramos.isEmpty()) {
			tramos.add(t);
		} else {
			ITramo ultimo = tramos.get(cantTramos - 1);  // SI EL TRAMO-ORIGEN NO ES IGUAL AL ULTIMO TRAMO , NO SERIA UN CIRCUITO CONTINUO.
			if (!(ultimo.getDestino() == t.getOrigen())) { // CADA TRAMO INICIA CON EL DESTINO DEL ANTERIOR, MENOS EL INICIO DEL CIRCUITO
				throw new IllegalArgumentException("el tramo no es el mismo que el origen");
			}
			tramos.add(t);
		}
		this.agregarTerminalOrigenYDestinoDe(t);
	}
		
	public void agregarTerminalOrigenYDestinoDe(ITramo t) { 
		if (!terminales.contains(t.getOrigen())) terminales.add(t.getOrigen());
		if (!terminales.contains(t.getDestino())) terminales.add(t.getDestino());
	}
	
	public List<ITramo> getTramos() {
		return tramos;
	}
	
	public int precioTotal() {
		return tramos.stream().mapToInt(ITramo::getPrecio).sum();
	}
	
	public Duration duracionTotal() {
		Duration total = Duration.ZERO;
		
		for (ITramo t: tramos) {
			total = total.plus(t.getDuracion());
		}
		return total;
	}
	
	public Duration duracionDe(ITramo t) {
		validarTramoPerteneciente(t);
        return t.getDuracion();
	}
	
	public int precioDe(ITramo t) {
		validarTramoPerteneciente(t);
        return t.getPrecio(); 
	}
	
	public void validarTramoPerteneciente(ITramo t) {
		if (!tramos.contains(t)) {
        	throw new IllegalArgumentException("El tramo no pertenece al circuito");
		}
	}

	public List<ITerminal> getTodasLasTerminales() { 
		return terminales;
	}
	
	public int cantidadDeTerminales() {
		return terminales.size();
	}
	
	public List<ITerminal> terminalesDestino() {
		 
		List<ITerminal> terminalesDestino = new ArrayList<>(); 
		
		for (ITramo t: tramos) {
			terminalesDestino.add(t.getDestino());
		}
		return terminalesDestino;
	}

	@Override
	public Duration duracionDesde_Hasta_(ITerminal terminalOrigen, ITerminal terminalDestino) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ITerminal> getTodasLasTerminalesDestino() {
		// TODO Auto-generated method stub
		return null;
	}
}
