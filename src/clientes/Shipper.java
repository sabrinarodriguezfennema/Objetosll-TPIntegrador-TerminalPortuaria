package clientes;

import java.time.LocalDateTime;

import interfaces.IShipper;

public class Shipper extends Cliente implements IShipper{
	
	private LocalDateTime turno;
	
	public Shipper(String nombre, String email) {
		super(nombre, email);
	}

	@Override
	public void fechaDeExportacion(LocalDateTime turno) {
		this.turno = turno;
	}

	@Override
	public LocalDateTime getFechaDeExportacion() {
		return turno;
	}
	


}
