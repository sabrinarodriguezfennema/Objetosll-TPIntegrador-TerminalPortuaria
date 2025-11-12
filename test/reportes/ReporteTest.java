package reportes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import interfaces.IBuque;
import interfaces.IContainer;
import interfaces.IOrdenDeExportacion;
import interfaces.IOrdenDeImportacion;
import interfaces.IViaje;
import reportes.Reporte;

class ReporteTest {

	private IViaje viaje;
	private IBuque buque;
	private IContainer container1;
	private IContainer container2;
	private IOrdenDeExportacion ordenExportacion;
	private IOrdenDeImportacion ordenImportacion;

	private Reporte reporte;

	@BeforeEach
	void setUp() {

		viaje = mock(IViaje.class);
		buque = mock(IBuque.class);
		container1 = mock(IContainer.class);
		container2 = mock(IContainer.class);
		ordenExportacion = mock(IOrdenDeExportacion.class);
		ordenImportacion = mock(IOrdenDeImportacion.class);

		when(viaje.getBuque()).thenReturn(buque);
		when(viaje.fechaSalida()).thenReturn(LocalDate.of(2025, 11, 9));
		when(buque.getNombre()).thenReturn("Titanic");

		when(container1.getId()).thenReturn("C1");
		when(container1.getTipo()).thenReturn("Dry");

		when(container2.getId()).thenReturn("C2");
		when(container2.getTipo()).thenReturn("Seco");

		when(buque.getContainers()).thenReturn(Set.of(container1, container2));

		when(ordenExportacion.getDatosDeCarga()).thenReturn(container1);
		when(ordenImportacion.getDatosDeCarga()).thenReturn(container2);

		reporte = new Reporte(viaje, List.of(ordenImportacion), List.of(ordenExportacion));
	}

	@Test
	void testGenerarReporteMuelle() {
		String resultado = reporte.generarReporteMuelle();

		assertTrue(resultado.contains("Titanic"));
		assertTrue(resultado.contains("2025-11-09"));
		assertTrue(resultado.contains("2"));
	}

	@Test
	void testGenerarReporteAduana() {
		String resultado = reporte.generarReporteAduana();

		assertTrue(resultado.contains("<html>"));
		assertTrue(resultado.contains("Reporte Aduana"));
		assertTrue(resultado.contains("Titanic"));
		assertTrue(resultado.contains("C1"));
		assertTrue(resultado.contains("C2"));
		assertTrue(resultado.contains("Dry"));
		assertTrue(resultado.contains("Seco"));
	}

	@Test
	void testGenerarReporteBuque() {
		String resultado = reporte.generarReporteBuque();

		assertTrue(resultado.startsWith("<report>"));
		assertTrue(resultado.contains("<import>"));
		assertTrue(resultado.contains("<export>"));
		assertTrue(resultado.endsWith("</report>"));

		assertTrue(resultado.contains("C2"));
		assertTrue(resultado.contains("C1"));
	}
}
