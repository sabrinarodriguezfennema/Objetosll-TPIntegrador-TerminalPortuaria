package facturacion;

import java.util.ArrayList;
import java.util.List;

public class Desglose {
	
	List<ItemDesglose> items;
	
	public Desglose() {
		this.items = new ArrayList<>();
	}
	
	public int montoTotal() {
		int total = 0; 
		
		for (ItemDesglose itemDesglose : items) 
			total += itemDesglose.getMonto(); 
		return total;
	}
	
	public void agregarItem(ItemDesglose itd) {
		items.add(itd);
	}
	
	public List<ItemDesglose> getItems() {
		return items;
	}

}
