package traslado;

import java.util.HashSet;
import java.util.Set;

import interfaces.IContainer;

public class EmpresaTransportista {

	private String nombre;
	private Set<String> patentesCamiones;
	private Set<String> dniChoferes;

	public EmpresaTransportista(String nombre) {
		this.nombre = nombre;
		this.patentesCamiones = new HashSet<>();
		this.dniChoferes = new HashSet<>();
	}

	public void agregarCamion(String patente) {
		patentesCamiones.add(patente);
	}

	public void agregarChofer(String dni) {
		dniChoferes.add(dni);
	}

	public boolean perteneceCamion(String patente) {
		return patentesCamiones.contains(patente);
	}

	public boolean perteneceChofer(String dni) {
		return dniChoferes.contains(dni);
	}

	public int cantidadDeCamiones() {

		return patentesCamiones.size();
	}

	public int cantidadDeChoferes() {

		return dniChoferes.size();
	}

	public String asignarCamionPara(IContainer container) {
		return patentesCamiones.stream()
	            .findAny() 
	            .orElseThrow(() -> new IllegalStateException("No hay camiones disponibles"));
	}



	public String asignarChoferPara(IContainer container) {
		return dniChoferes.stream()
	            .findAny() 
	            .orElseThrow(() -> new IllegalStateException("No hay choferes disponibles"));
	}
}


