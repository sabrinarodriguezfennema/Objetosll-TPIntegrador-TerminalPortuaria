package containers;

import java.util.List;

import servicios.Servicio;
import servicios.ServicioDesconsolidado;

public class ContainerDry extends Container {

	public ContainerDry(double altura, double ancho, double largo, String idAlfabetico, 
			int idNumerico, BillOfLading bl, Consignee consignee) {
		super(altura, ancho, largo, idAlfabetico, idNumerico, bl,consignee);
		this.tipo = "Dry";
	}
	
	public boolean requiereServicioEspecial() {
		return bl.tieneMasDeUnImportador();
	}
	
//	public void agregarServicioEspecial(ServicioDesconsolidado s) {
//		if (!this.requiereServicioEspecial()) {
//			throw new IllegalArgumentException("Solo tiene un importador");
//		}
//		servicios.add(s);
//	}
//	
}
