package Traslado;

import java.util.HashSet;
import java.util.Set;

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

}
