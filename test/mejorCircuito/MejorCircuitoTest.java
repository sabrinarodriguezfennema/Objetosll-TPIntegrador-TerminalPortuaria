package mejorCircuito;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


import interfaces.ICircuito;
import interfaces.IRutaMaritima;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MejorCircuitoTest {

	private IRutaMaritima ruta1;
	private IRutaMaritima ruta2;
	private IRutaMaritima ruta3;
	private ICircuito circuito1;
	private ICircuito circuito2;
	private ICircuito circuito3;
	private List<IRutaMaritima> rutas;

	@BeforeEach
	void setUp() {
		
		LocalDateTime fecha1 = LocalDateTime.of(2025, 12, 1, 0, 0);
		LocalDateTime fecha2 = LocalDateTime.of(2025, 11, 15, 0, 0);
		LocalDateTime fecha3 = LocalDateTime.of(2025, 11, 10, 0, 0);
		
		ruta1 = mock(IRutaMaritima.class);
		ruta2 = mock(IRutaMaritima.class);
		ruta3 = mock(IRutaMaritima.class);
		
		circuito1 = mock(ICircuito.class);
		circuito2 = mock(ICircuito.class);
		circuito3 = mock(ICircuito.class);
	

		when(ruta1.getCircuito()).thenReturn(circuito1);
		when(ruta2.getCircuito()).thenReturn(circuito2);
		when(ruta3.getCircuito()).thenReturn(circuito3);

		when(circuito1.precioTotal()).thenReturn(1000.0);
		when(circuito2.precioTotal()).thenReturn(800.0);
		when(circuito3.precioTotal()).thenReturn(1200.0);

		when(ruta1.fechaSalida()).thenReturn(fecha1);
		when(ruta2.fechaSalida()).thenReturn(fecha2);
		when(ruta3.fechaSalida()).thenReturn(fecha3);

		when(circuito1.cantidadDeTerminales()).thenReturn(5);
		when(circuito2.cantidadDeTerminales()).thenReturn(3);
		when(circuito3.cantidadDeTerminales()).thenReturn(7);

		when(circuito1.duracionTotal()).thenReturn(Duration.ofHours(10));
		when(circuito2.duracionTotal()).thenReturn(Duration.ofHours(8));
		when(circuito3.duracionTotal()).thenReturn(Duration.ofHours(12));

		rutas = Arrays.asList(ruta1, ruta2, ruta3);

	}

	@Test
    void testMenorPrecio() {
        MejorCircuito criterio = new MenorPrecio();
        IRutaMaritima resultado = criterio.mejorEntre(rutas);
        assertEquals(ruta2, resultado);
    }
	
	@Test
    void testMenorTiempo() {
		MejorCircuito criterio = new MenorTiempo();
	    IRutaMaritima resultado = criterio.mejorEntre(rutas);
	    assertEquals(ruta2, resultado); 
	}
    
	
	@Test
    void testMenorCantidadDeTerminales() {
        MejorCircuito criterio = new MenorCantidadDeTerminales();
        IRutaMaritima resultado = criterio.mejorEntre(rutas);
        assertEquals(ruta2, resultado);
    }
	
	@Test
	void testFechaMasProxima() {
        MejorCircuito criterio = new FechaMasProxima();
        IRutaMaritima resultado = criterio.mejorEntre(rutas);
        assertEquals(ruta3, resultado);
    }

}
