package facturacion;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.*;

public class DesgloseTest {
	
	ItemDesglose itd1, itd2, itd3, itd4;
	Desglose desglose;
	
	@BeforeEach 
	void setUp() {
		
		itd1 = mock(ItemDesglose.class);
		itd2 = mock(ItemDesglose.class);
		itd3 = mock(ItemDesglose.class);
		itd4 = mock(ItemDesglose.class);
		
		desglose = new Desglose();
	}
	
	@Test
	void agregacionDeItems() {
		
		desglose.agregarItem(itd1);
		desglose.agregarItem(itd2);
		desglose.agregarItem(itd3);
		
		assertEquals(List.of(itd1,itd2,itd3), desglose.getItems());
		
		assertFalse(desglose.getItems().contains(itd4));
	}
	
	@Test
	void montoTotal() {

		desglose.agregarItem(itd1);
		desglose.agregarItem(itd2);
		desglose.agregarItem(itd3);
		
		when(itd1.getMonto()).thenReturn(100.00);
		when(itd2.getMonto()).thenReturn(150.00);
		when(itd3.getMonto()).thenReturn(350.00); 
		
		assertEquals(600, desglose.montoTotal(),1);
	}
	

}
