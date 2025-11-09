package facturacion;

public class Factura {
	
	private Desglose desglose;
	
	public Factura(Desglose desglose) {
		this.desglose = desglose;
	}
	
	public int montoTotal() {
		return desglose.montoTotal();
	}
}
