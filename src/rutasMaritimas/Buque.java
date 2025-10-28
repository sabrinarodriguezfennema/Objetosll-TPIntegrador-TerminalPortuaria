package rutasMaritimas;

import java.util.ArrayList;
import java.util.List;

import containers.Container;

public class Buque {
	
	private String nombre;
	private List<Container> containers = new ArrayList<>();
	
	public Buque(String nombre) {
		this.nombre = nombre;
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
