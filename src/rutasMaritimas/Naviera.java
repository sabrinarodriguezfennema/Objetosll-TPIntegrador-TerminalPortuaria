package rutasMaritimas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Naviera {
	
	List<Viaje> viajes = new ArrayList<>();
	List<Circuito> circuitos = new ArrayList<>();
	List<Buque> buques = new ArrayList<>();
	
	public Naviera() {}
	
	public List<Circuito> getCircuitos() {
		return circuitos;
	}
	
	public List<Buque> getBuques() {
		return buques;
	}
	
	public List<Viaje> getViajes() {
		return viajes;
	}
	
	public List<Date> getFechaDeViajes() {
		
		List<Date> fechasViajes = new ArrayList<>();
		
		for (Viaje viaje: viajes) {
			fechasViajes.add(viaje.getFechaInicio());
		}
		return fechasViajes;
		
	}
	

}
