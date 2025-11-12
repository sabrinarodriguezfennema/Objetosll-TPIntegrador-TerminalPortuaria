package terminal;

import buqueViaje.Coordenadas;
import interfaces.ITerminal;
import interfaces.IViaje;
import interfaces.Localizable;

public class Terminal implements Localizable, ITerminal{
	
	protected String nombre;
	protected Coordenadas ubicacion;
	
	public Terminal(String nombre, Coordenadas ubicacion) {
		this.nombre = nombre;
		this.ubicacion = ubicacion;
	}
	
	@Override
	public Coordenadas getCoordenadas() {
		return ubicacion;
	}

	@Override
	public void viajeIniciado(IViaje unViaje) {
		// TODO Auto-generated method stub
		
	}

}
