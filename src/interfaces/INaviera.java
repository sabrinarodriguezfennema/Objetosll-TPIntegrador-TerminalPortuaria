package interfaces;

import java.time.LocalDateTime;
import java.util.Set;


public interface INaviera {

	public Set<IViaje> getViajes();

	public Set<IBuque> getBuques();
	
	public void agregarCircuito(ICircuito c);
	
	public void agregarBuque(IBuque buque1); 
	
	public void agregarViaje(IViaje primerViaje);

	public IViaje crearViaje(LocalDateTime of, IBuque buque1, ICircuito circuitoBsAsABrasil);

	public void iniciarViaje(IViaje primerViaje);

}
