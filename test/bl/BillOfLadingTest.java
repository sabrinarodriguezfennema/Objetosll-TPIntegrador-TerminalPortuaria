package bl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BillOfLadingTest {

	@BeforeEach
	void setUp() throws Exception {
	}
	
	//BLSimple

	@Test
	void testUnBLSimpleConoceSuPeso() {
		BLSimple unBLSimple = new BLSimple("Ropa", 15);
		
		double resultado = unBLSimple.peso();
		
		assertEquals(15, resultado);
	}

	@Test
	void testUnBLSimpleConoceSuTipoDeProducto() {
		BLSimple unBLSimple = new BLSimple("Ropa", 15);
		
		List<String> resultado = unBLSimple.tipoDeProducto();
		List<String> esperado = new ArrayList<String>();
		esperado.add("Ropa");
		assertEquals(esperado, resultado);
	}
	
	//BLEspecial
	@Test
	void testUnBLEspecialConoceSuPesoEnBaseASusBLsInternos() {
		List<BillOfLading> listaDeBLsEnUnBLEspecial = new ArrayList<BillOfLading>();
		BLSimple unBLSimple = mock(BLSimple.class);
		BLSimple otroBLSimple = mock(BLSimple.class);
		listaDeBLsEnUnBLEspecial.add(unBLSimple);
		listaDeBLsEnUnBLEspecial.add(otroBLSimple);
		when(unBLSimple.peso()).thenReturn(12.0);
		when(otroBLSimple.peso()).thenReturn(15.0);
		BLEspecial unBLEspecial = new BLEspecial(listaDeBLsEnUnBLEspecial);
		
		double resultado = unBLEspecial.peso();
		
		assertEquals(27, resultado);
	}
	
	@Test
	void testUnBLEspecialConoceSusTiposDeProductoEnBaseASusBLsInternos() {
		List<BillOfLading> listaDeBLsEnUnBLEspecial = new ArrayList<BillOfLading>();
		BLSimple unBLSimple = mock(BLSimple.class);
		BLSimple otroBLSimple = mock(BLSimple.class);
		listaDeBLsEnUnBLEspecial.add(unBLSimple);
		listaDeBLsEnUnBLEspecial.add(otroBLSimple);
		List<String> tipoDeProductoDeUnBLSimple = new ArrayList<String>();
		List<String> tipoDeProductoDeOtroBLSimple = new ArrayList<String>();
		tipoDeProductoDeUnBLSimple.add("Ropa");
		tipoDeProductoDeUnBLSimple.add("Cosmético");
		when(unBLSimple.tipoDeProducto()).thenReturn(tipoDeProductoDeUnBLSimple);
		when(otroBLSimple.tipoDeProducto()).thenReturn(tipoDeProductoDeOtroBLSimple);
		BLEspecial unBLEspecial = new BLEspecial(listaDeBLsEnUnBLEspecial);
		
		List<String> resultado = unBLEspecial.tipoDeProducto();
		List<String> expected  = new ArrayList<String>();
		expected.add("Ropa");
		expected.add("Cosmético");
		
		assertEquals(expected, resultado);
	}
	
	@Test
	void testUnBLEspecialPuedeContenerOtrosBLsEspeciales() {
		List<BillOfLading> listaDeBLsEnUnBLEspecial = new ArrayList<BillOfLading>();
		BLSimple unBLSimple = mock(BLSimple.class);
		BLSimple otroBLSimple = mock(BLSimple.class);
		listaDeBLsEnUnBLEspecial.add(unBLSimple);
		listaDeBLsEnUnBLEspecial.add(otroBLSimple);
		when(unBLSimple.peso()).thenReturn(12.0);
		when(otroBLSimple.peso()).thenReturn(15.0);
		BLEspecial unBLEspecial = new BLEspecial(listaDeBLsEnUnBLEspecial);
		
		List<BillOfLading> listaDeBLsEnOtroBLEspecial = new ArrayList<BillOfLading>();
		listaDeBLsEnOtroBLEspecial.add(unBLEspecial);
		BillOfLading unBLSimpleMás = mock(BLSimple.class);
		when(unBLSimpleMás.peso()).thenReturn(17.0);
		listaDeBLsEnOtroBLEspecial.add(unBLSimpleMás);
		BLEspecial otroBLEspecial = new BLEspecial(listaDeBLsEnOtroBLEspecial);
		
		double resultado = otroBLEspecial.peso();
		
		assertEquals(44, resultado);
	}
}
