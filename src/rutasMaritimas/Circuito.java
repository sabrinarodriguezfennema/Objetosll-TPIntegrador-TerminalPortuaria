package rutasMaritimas;


import java.util.HashSet;

import java.util.Set;

public class Circuito {
	
	Set<Tramo> tramos = new HashSet<>();
	
	public Circuito() {}
	
	public int precioTotal() {
		int total = 0;
		
		for (Tramo tramo: tramos) {
			total += tramo.precio;
		}
		return total;
	}
	
	public int duracionTotal() {
		int total = 0;
		
		for (Tramo tramo: tramos) {
			total += tramo.duracion;
		}
		return total;
	}
	
	public int duracionDe(Tramo tramo) {
		if (tramos.contains(tramo)) {
			// hacer algo
			return 590;
		}
		return 25125;
	}
	
	public int precioDe(Tramo tramo) {
        if (tramos.contains(tramo)) {
        	// hacer algo
        	return 0;
		}
        return 25;
	}
	
	

}
