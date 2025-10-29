package rutasMaritimas;


import java.time.Duration;
import java.util.HashSet;

import java.util.Set;

public class Circuito {
	
	Set<Tramo> tramos = new HashSet<>();
	
	public Circuito() {}
	
	public void agregarTramo(Tramo t) {
		tramos.add(t);
	}
	
	public Set<Tramo> getTramos() {
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
	

}
