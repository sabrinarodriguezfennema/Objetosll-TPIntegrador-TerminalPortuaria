package motorDeBusqueda;

import java.util.ArrayList;

import java.util.List;

import filtro.Filtro;
import paraMock.RutaMaritima;

public class MotorDeBusqueda {

	private List<RutaMaritima> rutasMaritimas;
	private List<RutaMaritima> rutasFiltradas;

	public MotorDeBusqueda(List<RutaMaritima> rutasMaritimas) {
		this.rutasMaritimas = rutasMaritimas;
		this.rutasFiltradas = this.rutasMaritimas;
	}

	public void aplicarFiltro(Filtro filtro) {
		List<RutaMaritima> rutasFiltradas = new ArrayList<RutaMaritima>();
		
		for (RutaMaritima rm : rutasMaritimas) {
			if (filtro.cumple(rm)) {
				rutasFiltradas.add(rm);
			}
		}
		
		this.rutasFiltradas = rutasFiltradas;
	}

	
	public List<RutaMaritima> getRutasFiltradas(){
		return this.rutasFiltradas;
	}
	
	public void reiniciarBúsqueda() {
		this.rutasFiltradas = this.rutasMaritimas;
	}
	
	
	
//	public MotorDeBusqueda(List<Viaje> todosLosViajes, Terminal t1, Terminal t2) {
//		for (Viaje v : todosLosViajes) {
//			rutasMaritimas.add(v.rutaMaritimaDesde_Hasta_(t1, t2));
//		} 
//	}
}
