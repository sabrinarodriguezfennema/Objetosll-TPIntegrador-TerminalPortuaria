package containers;

import java.util.List;

import servicios.Servicio;
import servicios.ServicioRevisionDiaria;

public class ContainerTanque extends Container {
	
	public ContainerTanque(int altura, int ancho, int largo, String idAlfabetico, 
			int idNumerico, BillOfLading bl, Consignee consignee, List<Servicio> servicios ) {
		super(altura, ancho, largo, idAlfabetico, idNumerico, bl, consignee);
		this.tipo = "Tanque";
	}
}

/// COMO HACER PARA INSTANCIAR CON UN SERVICIO DE REVISION DIARIA?