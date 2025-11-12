package naviera;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import interfaces.Buque;
import interfaces.IBuque;
import interfaces.ICircuito;
import interfaces.INaviera;
import interfaces.IViaje;
import interfaces.Viaje;

public class Naviera implements INaviera{
	
	private List<Viaje> viajes = new ArrayList<>();
	private List<ICircuito> circuitos = new ArrayList<>();
	private List<IBuque> buques = new ArrayList<>();
	
	public Naviera() {}
	
	public void agregarCircuito(ICircuito c) {
		circuitos.add(c);
	}

	public void agregarViaje(Viaje v) {
		viajes.add(v);
	}
	
	public void agregarBuque(IBuque b) {
		buques.add(b);
	}
	
	public List<ICircuito> getCircuitos() {
		return circuitos;
	}
	 
	public List<IBuque> getBuques() {
		return buques; 
	}
	
	public List<IViaje> getViajes() {
		return viajes;
	}
	
	public List<LocalDate> getFechaDeViajes() {
		
		List<LocalDate> fechasViajes = new ArrayList<>();
		
		for (Viaje viaje: viajes) {
			fechasViajes.add(viaje.getFechaInicio());
		}
		return fechasViajes;
	}

	@Override
	public void agregarViaje(IViaje primerViaje) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IViaje crearViaje(LocalDate fechaSalida, IBuque unBuque, ICircuito circuito) {
		IViaje viaje = new Viaje(fechaSalida, unBuque, circuito);
		viajes.add(viaje);
		return viaje;
	}
}
