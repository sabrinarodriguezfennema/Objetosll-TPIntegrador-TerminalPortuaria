package containers;

import bl.BillOfLading;

import interfaces.IConsignee;

public class ContainerTanque extends Container {
	
	public ContainerTanque(double altura, double ancho, double largo, String idAlfabetico, 
			int idNumerico, BillOfLading bl, IConsignee consignee
			) {
		super(altura, ancho, largo, idAlfabetico, idNumerico, bl, consignee);
		this.tipo = "Tanque";
	}
}
