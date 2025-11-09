package facturacion;

import java.time.LocalDate;

public class ItemDesglose {
	
	String detalle;
	int monto;
	LocalDate fecha;
	
	public ItemDesglose(String detalle, int monto, LocalDate fecha) {
		this.detalle = detalle;
		this.monto = monto; 
		this.fecha = fecha;
	}
	
	public int getMonto() {
		return monto;
	}
	
	public String getDetalle() {
		return detalle;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}

}
