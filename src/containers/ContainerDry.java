package containers;


import interfaces.BillOfLading;
import interfaces.IConsignee;
import clientes.Consignee;

public class ContainerDry extends Container {

	public ContainerDry(double altura, double ancho, double largo, String idAlfabetico, 
			int idNumerico, bl.BillOfLading productosDeSabri, IConsignee consigneeBrasilero) {
		super(altura, ancho, largo, idAlfabetico, idNumerico, productosDeSabri,consigneeBrasilero);
		this.tipo = "Dry";
	}

	@Override
	public String getTipo() {
		return tipo;
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
