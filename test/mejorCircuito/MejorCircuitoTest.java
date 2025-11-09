package mejorCircuito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


import interfaces.Circuito;
import interfaces.RutaMaritima;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MejorCircuitoTest {

	private RutaMaritima ruta1;
	private RutaMaritima ruta2;
	private RutaMaritima ruta3;
	private Circuito circuito1;
	private Circuito circuito2;
	private Circuito circuito3;
	private List<RutaMaritima> rutas;

	@BeforeEach
	void setUp() {
		
		LocalDate fecha1 = LocalDate.of(2025, 12, 1);
		LocalDate fecha2 = LocalDate.of(2025, 11, 15);
		LocalDate fecha3 = LocalDate.of(2025, 11, 10);
		
		ruta1 = mock(RutaMaritima.class);
		ruta2 = mock(RutaMaritima.class);
		ruta3 = mock(RutaMaritima.class);
		
		circuito1 = mock(Circuito.class);
		circuito2 = mock(Circuito.class);
		circuito3 = mock(Circuito.class);
	

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
        RutaMaritima resultado = criterio.mejorEntre(rutas);
        assertEquals(ruta2, resultado);
    }
	
	@Test
    void testMenorTiempo() {
		MejorCircuito criterio = new MenorTiempo();
	    RutaMaritima resultado = criterio.mejorEntre(rutas);
	    assertEquals(ruta2, resultado); 
	}
    
	
	@Test
    void testMenorCantidadDeTerminales() {
        MejorCircuito criterio = new MenorCantidadDeTerminales();
        RutaMaritima resultado = criterio.mejorEntre(rutas);
        assertEquals(ruta2, resultado);
    }
	
	@Test
	void testFechaMasProxima() {
        MejorCircuito criterio = new FechaMasProxima();
        RutaMaritima resultado = criterio.mejorEntre(rutas);
        assertEquals(ruta3, resultado);
    }

}
