package containers;

import interfaces.BillOfLading;

import interfaces.Consignee;

public class ContainerTanque extends Container {
	
	public ContainerTanque(int altura, int ancho, int largo, String idAlfabetico, 
			int idNumerico, BillOfLading bl, Consignee consignee
			) {
		super(altura, ancho, largo, idAlfabetico, idNumerico, bl, consignee);
		this.tipo = "Tanque";
	}

	@Override
	public String getTipo() {
		return tipo;
	}
}
