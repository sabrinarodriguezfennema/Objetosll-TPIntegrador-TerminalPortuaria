package buque;

import java.util.ArrayList;

import java.util.List;

import interfaces.IBuque;
import interfaces.IContainer;

public class Buque implements IBuque {
	
	private String nombre;
	private List<IContainer> containers;
	
	public Buque(String nombre) {
		this.nombre = nombre;
		containers = new ArrayList<>();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void addContainer(IContainer c) {
		containers.add(c);
	}
	
	public List<IContainer> getContainers() {
		return containers;
	}
	
	public void eliminarContainers() {
		containers.clear();
	}

	@Override
	public void removeContainer(IContainer container) {
		// TODO Auto-generated method stub
		
	}
}
