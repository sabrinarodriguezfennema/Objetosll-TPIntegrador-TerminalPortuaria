package mejorCircuito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import interfaces.IRutaMaritima;

class GestorDeRutasTest {

	private MejorCircuito criterioMock;
	private GestorDeRutas gestor;
	private IRutaMaritima ruta1;
	private IRutaMaritima ruta2;

	@BeforeEach
	void setUp() {

		ruta1 = mock(IRutaMaritima.class);
		ruta2 = mock(IRutaMaritima.class);

		List<IRutaMaritima> rutas = Arrays.asList(ruta1, ruta2);

		criterioMock = mock(MejorCircuito.class);

		when(criterioMock.mejorEntre(rutas)).thenReturn(ruta2);

		gestor = new GestorDeRutas(criterioMock);
	}

	@Test
	void testPlanificarUsaCriterio() {
		List<IRutaMaritima> rutas = Arrays.asList(ruta1, ruta2);

		IRutaMaritima mejor = gestor.planificar(rutas);

		verify(criterioMock, times(1)).mejorEntre(rutas);

		assertEquals(ruta2, mejor);
	}

	@Test
	void testCambioDeCriterio() {
		List<IRutaMaritima> rutas = Arrays.asList(ruta1, ruta2);

		MejorCircuito otroCriterio = mock(MejorCircuito.class);
		when(otroCriterio.mejorEntre(rutas)).thenReturn(ruta1);

		gestor.setCriterio(otroCriterio);

		IRutaMaritima mejor = gestor.planificar(rutas);

		verify(otroCriterio, times(1)).mejorEntre(rutas);
		assertEquals(ruta1, mejor);
	}
}