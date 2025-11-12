package facturacion;

import java.time.LocalDate;

public class ItemDesglose {
	
	private String detalle;
	private double monto;
	private LocalDate fecha;
	
	public ItemDesglose(String detalle, double monto, LocalDate fecha) {
		this.detalle = detalle;
		this.monto = monto; 
		this.fecha = fecha;
	}
	
	public double getMonto() {
		return monto;
	}
	
	public String getDetalle() {
		return detalle;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}//terminando factura

}
