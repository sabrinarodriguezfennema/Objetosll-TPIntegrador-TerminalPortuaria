package clientes;

import java.time.LocalDateTime;

import interfaces.IConsignee;

public class Consignee extends Cliente implements IConsignee{

	public Consignee(String nombre, String email) {
		super(nombre, email);
	}

	@Override
	public void fechaDeImportacion(LocalDateTime turno) {
		this.turno = turno;
	}

	@Override
	public LocalDateTime getFechaDeExportacion() {
		return turno;
	}
	
}
