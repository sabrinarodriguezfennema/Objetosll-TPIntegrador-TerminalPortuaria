package bl;

import java.util.ArrayList;
import java.util.List;

public class BLEspecial implements BillOfLading{

	private List<BillOfLading> blsAgrupados;

	public BLEspecial(List<BillOfLading> blsAgrupados) {
		this.blsAgrupados = blsAgrupados;
	}

	@Override
	public List<String> tipoDeProducto() { //TODO posiblemente podría ser un Set
		List<String> tiposDeProductos = new ArrayList<String>(); 
		for (BillOfLading bl : blsAgrupados) {
			tiposDeProductos.addAll(bl.tipoDeProducto());
		} //TODO buqueViaje IBuqueVIaje Viaje TerminalGestionada Terminal RutaMaritima
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
