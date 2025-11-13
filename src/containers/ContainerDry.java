package containers;

import interfaces.IConsignee;
import interfaces.BillOfLading;

public class ContainerDry extends Container {

	public ContainerDry(double altura, double ancho, double largo, String idAlfabetico, 
			int idNumerico, BillOfLading productosDeSabri, IConsignee consigneeBrasilero) {
		super(altura, ancho, largo, idAlfabetico, idNumerico, productosDeSabri,consigneeBrasilero);
		this.tipo = "Dry";
	}
	
	//public boolean requiereServicioEspecial() {
	//	return bl.tieneMasDeUnImportador();
	//}
	
//	public void agregarServicioEspecial(ServicioDesconsolidado s) {
//		if (!this.requiereServicioEspecial()) {
//			throw new IllegalArgumentException("Solo tiene un importador");
//		}
//		servicios.add(s);
//	}
//	
}
