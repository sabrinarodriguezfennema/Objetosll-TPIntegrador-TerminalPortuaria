package containers;

public class ContainerReefer extends Container {
	
	private int kwPorHora;

	public ContainerReefer(int altura, int ancho, int largo, String idAlfabetico, 
			int idNumerico, BillOfLading bl, Consignee consignee, int kwPorHora) {
		super(altura, ancho, largo, idAlfabetico, idNumerico, bl, consignee);
		this.kwPorHora = kwPorHora;
		this.tipo = "Reefer";
	}
	
	public int getkwPorHora() {
		return kwPorHora;
	}
}
