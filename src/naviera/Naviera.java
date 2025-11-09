package naviera;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import buque.Buque;
import circuito.Circuito;
import viaje.Viaje;

public class Naviera {
	
	private List<Viaje> viajes = new ArrayList<>();
	private List<Circuito> circuitos = new ArrayList<>();
	private List<Buque> buques = new ArrayList<>();
	
	public Naviera() {}
	
	public void agregarCircuito(Circuito c) {
		circuitos.add(c);
	}

	public void agregarViaje(Viaje v) {
		viajes.add(v);
	}
	
	public void agregarBuque(Buque b) {
		buques.add(b);
	}
	
	public List<Circuito> getCircuitos() {
		return circuitos;
	}
	 
	public List<Buque> getBuques() {
		return buques; 
	}
	
	public List<Viaje> getViajes() {
		return viajes;
	}
	
	public List<LocalDate> getFechaDeViajes() {
		
		List<LocalDate> fechasViajes = new ArrayList<>();
		
		for (Viaje viaje: viajes) {
			fechasViajes.add(viaje.getFechaInicio());
		}
		return fechasViajes;
	}
}
