package bl;

import java.util.ArrayList;
import java.util.List;

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

	public List<String> tipoDeProducto() {
		List<String> resultado = new ArrayList<String>();
		resultado.add(tipoDeProducto);
		return resultado;
	}
	
	
	
}
