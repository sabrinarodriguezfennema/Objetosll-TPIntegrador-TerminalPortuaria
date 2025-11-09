package mejorCircuito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import interfaces.RutaMaritima;

class GestorDeRutasTest {

	private MejorCircuito criterioMock;
	private GestorDeRutas gestor;
	private RutaMaritima ruta1;
	private RutaMaritima ruta2;

	@BeforeEach
	void setUp() {

		ruta1 = mock(RutaMaritima.class);
		ruta2 = mock(RutaMaritima.class);

		List<RutaMaritima> rutas = Arrays.asList(ruta1, ruta2);

		criterioMock = mock(MejorCircuito.class);

		when(criterioMock.mejorEntre(rutas)).thenReturn(ruta2);

		gestor = new GestorDeRutas(criterioMock);
	}

	@Test
	void testPlanificarUsaCriterio() {
		List<RutaMaritima> rutas = Arrays.asList(ruta1, ruta2);

		RutaMaritima mejor = gestor.planificar(rutas);

		verify(criterioMock, times(1)).mejorEntre(rutas);

		assertEquals(ruta2, mejor);
	}

	@Test
	void testCambioDeCriterio() {
		List<RutaMaritima> rutas = Arrays.asList(ruta1, ruta2);

		MejorCircuito otroCriterio = mock(MejorCircuito.class);
		when(otroCriterio.mejorEntre(rutas)).thenReturn(ruta1);

		gestor.setCriterio(otroCriterio);

		RutaMaritima mejor = gestor.planificar(rutas);

		verify(otroCriterio, times(1)).mejorEntre(rutas);
		assertEquals(ruta1, mejor);
	}
}