package bl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		
		Set<String> resultado = unBLSimple.tipoDeProducto();
		Set<String> esperado = new HashSet<String>();
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
		Set<String> tipoDeProductoDeUnBLSimple = new HashSet<String>();
		Set<String> tipoDeProductoDeOtroBLSimple = new HashSet<String>();
		tipoDeProductoDeUnBLSimple.add("Ropa");
		tipoDeProductoDeUnBLSimple.add("Cosmético");
		when(unBLSimple.tipoDeProducto()).thenReturn(tipoDeProductoDeUnBLSimple);
		when(otroBLSimple.tipoDeProducto()).thenReturn(tipoDeProductoDeOtroBLSimple);
		BLEspecial unBLEspecial = new BLEspecial(listaDeBLsEnUnBLEspecial);
		
		Set<String> resultado = unBLEspecial.tipoDeProducto();
		Set<String> esperado  = new HashSet<String>();
		esperado.add("Ropa");
		esperado.add("Cosmético");
		
		assertEquals(esperado, resultado);
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
