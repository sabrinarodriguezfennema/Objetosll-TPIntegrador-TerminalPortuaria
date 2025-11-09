package containers;

import java.util.List;

import servicios.Servicio;
import servicios.ServicioDesconsolidado;

public class ContainerDry extends Container {

	public ContainerDry(int altura, int ancho, int largo, String idAlfabetico, 
			int idNumerico, BillOfLading bl, Consignee consignee, List<Servicio> servicios) {
		super(altura, ancho, largo, idAlfabetico, idNumerico, bl,consignee, servicios, "Dry");
	}
	
	public boolean requiereServicioEspecial() {
		return bl.tieneMasDeUnImportador();
	}
	
	public void agregarServicioEspecial(ServicioDesconsolidado s) {
		if (!this.requiereServicioEspecial()) {
			throw new IllegalArgumentException("Solo tiene un importador");
		}
		servicios.add(s);
	}
	
}
