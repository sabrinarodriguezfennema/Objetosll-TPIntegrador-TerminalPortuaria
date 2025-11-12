package motorDeBusqueda;

import java.util.ArrayList;

import java.util.List;

import filtro.Filtro;
import interfaces.IRutaMaritima;
import interfaces.ITerminal;
import interfaces.IViaje;

public class MotorDeBusqueda {

	private List<IRutaMaritima> rutasMaritimas;
	private List<IRutaMaritima> rutasFiltradas;

	public MotorDeBusqueda(List<IRutaMaritima> rutasMaritimas) {
		this.rutasMaritimas = rutasMaritimas;
		this.rutasFiltradas = this.rutasMaritimas;
	}

	public void aplicarFiltro(Filtro filtro) {
		List<IRutaMaritima> rutasFiltradas = new ArrayList<IRutaMaritima>();
		
		for (IRutaMaritima rm : rutasMaritimas) {
			if (filtro.cumple(rm)) {
				rutasFiltradas.add(rm);
			}
		}
		
		this.rutasFiltradas = rutasFiltradas;
	}

	
	public List<IRutaMaritima> getRutasFiltradas(){
		return this.rutasFiltradas;
	}
	
	public void reiniciarBúsqueda() {
		this.rutasFiltradas = this.rutasMaritimas;
	}
	
	
	
	public MotorDeBusqueda(List<IViaje> todosLosViajes, ITerminal t1, ITerminal t2) {
		for (IViaje v : todosLosViajes) {
			rutasMaritimas.add(v.rutaMaritimaDesde_Hasta_(t1, t2));
		} 
	}
}
