package buque;

import java.util.HashSet;
import java.util.Set;

import interfaces.IBuque;
import interfaces.IContainer;

public class Buque implements IBuque {
	
	private String nombre;
	private Set<IContainer> containers;
	
	public Buque(String nombre) {
		this.nombre = nombre;
		containers = new HashSet<>();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void addContainer(IContainer c) {
		containers.add(c);
	}
	
	public Set<IContainer> getContainers() {
		return containers;
	}

	@Override
	public void removeContainer(IContainer container) {
		containers.remove(container);
	}
}
