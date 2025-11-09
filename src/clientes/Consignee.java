package clientes;

import interfaces.IConsignee;

public class Consignee extends Cliente implements IConsignee{

	public Consignee(String nombre, String email) {
		super(nombre, email);
	}



}
