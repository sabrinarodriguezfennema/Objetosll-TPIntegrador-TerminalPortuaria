package containers;

import bl.BillOfLading;

import interfaces.IConsignee;

public class ContainerReefer extends Container {
	
	private int kwPorHora;

	public ContainerReefer(double altura, double ancho, double largo, String idAlfabetico, int idNumerico, BillOfLading bl, IConsignee consignee, int kwPorHora) {
		super(altura, ancho, largo, idAlfabetico, idNumerico, bl, consignee);
		this.kwPorHora = kwPorHora;
		this.tipo = "Reefer";
	}
	
	public int getkwPorHora() {
		return kwPorHora;
	}
}
