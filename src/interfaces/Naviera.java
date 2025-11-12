package interfaces;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import interfaces.IViaje;
import interfaces.IContainer;

public interface Naviera {

	public Set<IViaje> getViajes();

	public Set<IBuque> getBuques();

}
