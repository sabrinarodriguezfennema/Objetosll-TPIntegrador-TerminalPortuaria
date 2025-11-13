package facturacion;

import java.time.LocalDateTime;

public class ItemDesglose {
	
	private String detalle;
	private double monto;
	private LocalDateTime fecha;
	
	public ItemDesglose(String detalle, double monto, LocalDateTime fecha2) {
		this.detalle = detalle;
		this.monto = monto; 
		this.fecha = fecha2;
	}
	
	public double getMonto() {
		return monto;
	}
	
	public String getDetalle() {
		return detalle;
	}
	
	public LocalDateTime getFecha() {
		return fecha;
	}//terminando factura

}
