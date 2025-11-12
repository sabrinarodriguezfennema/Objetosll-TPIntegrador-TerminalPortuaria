package interfaces;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import interfaces.IViaje;
import interfaces.IContainer;

public interface INaviera {

	public Set<IViaje> getViajes();

	public Set<IBuque> getBuques();
	
	public void agregarCircuito(ICircuito c);
	
	public void agregarBuque(IBuque buque1); 
	
	public void agregarViaje(IViaje primerViaje);

	public IViaje crearViaje(LocalDate of, IBuque buque1, ICircuito circuitoBsAsABrasil);

}
