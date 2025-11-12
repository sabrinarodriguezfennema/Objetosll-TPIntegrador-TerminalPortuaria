package facturacion;

import java.util.ArrayList;
import java.util.List;

public class Desglose {
	
	private List<ItemDesglose> items;
	
	public Desglose() {
		this.items = new ArrayList<>();
	} 
	
	public double montoTotal() {
		return items.stream().mapToDouble(ItemDesglose::getMonto).sum();
	}
	
	public void agregarItem(ItemDesglose itd) {
		items.add(itd);
	}
	
	public List<ItemDesglose> getItems() {
		return items;
	}

}
