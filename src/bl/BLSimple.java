package bl;

import java.util.HashSet;
import java.util.Set;

public class BLSimple implements BillOfLading {

	private String tipoDeProducto;
	private double peso;

	public BLSimple(String tipoDeProducto, double peso) {
		this.tipoDeProducto = tipoDeProducto;
		this.peso = peso;
	}

	public double peso() {
		return peso;
	}

	public Set<String> tipoDeProducto() {
		Set<String> resultado = new HashSet<String>();
		resultado.add(tipoDeProducto);
		return resultado;
	}
	
	
	
}
