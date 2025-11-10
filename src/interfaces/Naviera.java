package interfaces;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import interfaces.Viaje;
import interfaces.Container;

public interface Naviera {

	public Set<Viaje> getViajes();

	public Set<Buque> getBuques();

}
