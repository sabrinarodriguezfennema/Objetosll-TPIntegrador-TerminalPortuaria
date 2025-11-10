package buque;

import java.util.ArrayList;
import java.util.List;

import containers.Container;

public class Buque {
	
	private String nombre;
	private List<Container> containers;
	
	public Buque(String nombre) {
		this.nombre = nombre;
		containers = new ArrayList<>();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void addContainer(Container c) {
		containers.add(c);
	}
	
	public List<Container> getContainers() {
		return containers;
	}
}
