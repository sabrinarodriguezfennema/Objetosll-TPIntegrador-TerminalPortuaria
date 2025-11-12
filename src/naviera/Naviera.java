package naviera;

import java.time.LocalDate;


import java.util.HashSet;
import java.util.Set;
import interfaces.IBuque;
import interfaces.ICircuito;
import interfaces.INaviera;
import interfaces.IViaje;
import viaje.Viaje;

public class Naviera implements INaviera{
	
	private Set<IViaje> viajes = new HashSet<IViaje>();
	private Set<ICircuito> circuitos = new HashSet<ICircuito>();
	private Set<IBuque> buques = new HashSet<IBuque>();
	
	public Naviera() {}
	
	public void agregarCircuito(ICircuito c) {
		circuitos.add(c);
	}

	@Override
	public void agregarViaje(IViaje v) {
		viajes.add(v);
	}
	
	public void agregarBuque(IBuque b) {
		buques.add(b);
	}
	
	public Set<ICircuito> getCircuitos() {
		return circuitos;
	}
	 
	public Set<IBuque> getBuques() {
		return buques; 
	}
	
	public Set<IViaje> getViajes() {
		return viajes;
	}
	
	public Set<LocalDate> getFechaDeViajes() {
		
		Set<LocalDate> fechasViajes = new HashSet<LocalDate>();
		
		for (IViaje viaje: viajes) {
			fechasViajes.add(viaje.fechaSalida());
		}
		return fechasViajes;
	}



	@Override
	public IViaje crearViaje(LocalDate fechaSalida, IBuque unBuque, ICircuito circuito) {
		IViaje viaje = new Viaje(fechaSalida, unBuque, circuito);
		viajes.add(viaje);
		return viaje;
	}
}
