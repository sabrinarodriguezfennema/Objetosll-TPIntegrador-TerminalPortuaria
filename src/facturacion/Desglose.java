package facturacion;

import java.util.ArrayList;
import java.util.List;

public class Desglose {
	
	private List<ItemDesglose> items;
	
	public Desglose() {
		this.items = new ArrayList<>();
	} 
	
	public int montoTotal() {
		return items.stream().mapToInt(ItemDesglose::getMonto).sum();
	}
	
	public void agregarItem(ItemDesglose itd) {
		items.add(itd);
	}
	
	public List<ItemDesglose> getItems() {
		return items;
	}

}
