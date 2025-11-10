package containers;

import java.util.List;

import servicios.Servicio;

public class ContainerReefer extends Container {
	
	private int kwPorHora;

	public ContainerReefer(int altura, int ancho, int largo, String idAlfabetico, 
			int idNumerico, BillOfLading bl, Consignee consignee, List<Servicio> servicios, int kwPorHora) {
		super(altura, ancho, largo, idAlfabetico, idNumerico, bl, consignee, servicios, "Reefer");
		this.kwPorHora = kwPorHora;
	}
	
	public int getkwPorHora() {
		return kwPorHora;
	}
}
