package containers;

import interfaces.BillOfLading;

import interfaces.IConsignee;

public class ContainerReefer extends Container {
	
	private double kwPorHora;

	public ContainerReefer(double altura, double ancho, double largo, String idAlfabetico, int idNumerico, BillOfLading bl, IConsignee consignee, double kwPorHora) {
		super(altura, ancho, largo, idAlfabetico, idNumerico, bl, consignee);
		this.kwPorHora = kwPorHora;
		this.tipo = "Reefer";
	}
	
	public double getkwPorHora() {
		return kwPorHora;
	}
}
