package bl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BLEspecial implements BillOfLading{

	private List<BillOfLading> blsAgrupados;

	public BLEspecial(List<BillOfLading> blsAgrupados) {
		this.blsAgrupados = blsAgrupados;
	}

	@Override
	public Set<String> tipoDeProducto() {
		Set<String> tiposDeProductos = new HashSet<String>(); 
		for (BillOfLading bl : blsAgrupados) {
			tiposDeProductos.addAll(bl.tipoDeProducto());
		}
		return tiposDeProductos;
	}

	@Override
	public double peso() {
		double pesos = 0; 
		for (BillOfLading bl : blsAgrupados) {
			pesos += bl.peso();
		}
		return pesos;
	}

}
