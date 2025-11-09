package containers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

import servicios.Servicio;
import servicios.ServicioRevisionDiaria;

public class ContainerTanqueTest {
	
	ContainerTanque c;
	List<Servicio> servicios = new ArrayList<>();
	ServicioRevisionDiaria s;
	
	
	@BeforeEach
	void setUp() {
		c = new ContainerTanque(0, 0, 0, null, 0, null, null, servicios);
		s = mock(ServicioRevisionDiaria.class);
	}
	
	@Test
	void obtenerTipo() {
		assertEquals("Tanque", c.tipo());
	}
	
	@Test
	void verificarListaConServicioRevision() {
		c.agregarServicio(s);
		assertEquals(List.of(s), c.getServicios());
	}

}
