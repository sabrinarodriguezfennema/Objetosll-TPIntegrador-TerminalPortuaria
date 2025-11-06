package containers;

import java.util.List;

import servicios.Servicio;

public class ContainerDry extends Container {

	public ContainerDry(int altura, int ancho, int largo, String idAlfabetico, 
			int idNumerico, BillOfLading bl, Consignee consignee, List<Servicio> servicios) {
		super(altura, ancho, largo, idAlfabetico, idNumerico, bl,consignee, servicios);
	}
	
	public boolean requiereServicioEspecial() {
		return bl.tieneMasDeUnImportador();
	}
	
	@Override
	public void agregarServicio(Servicio s) {
		if (!this.requiereServicioEspecial()) {
			throw new IllegalArgumentException("Solo tiene un importador");
		}
		servicios.add(s);
	}
	
}
