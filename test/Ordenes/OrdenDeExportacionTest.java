package Ordenes;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import Carga.Container;
import Clientes.Shipper;

class OrdenDeExportacionTest {

	private Shipper mockShipper;
	private Container mockContainer;
	private String patenteCamion;
	private String dniChofer;
	private LocalDate fechaSalida;
	private LocalDate fechaLlegada;
	private OrdenDeExportacion orden;

	@BeforeEach
	void setUp() {

		//setUp
		mockShipper = mock(Shipper.class);
		mockContainer = mock(Container.class);
		patenteCamion = "ABC123";
		dniChofer = "40555999";

		fechaSalida = LocalDate.of(2025, 10, 29);
		fechaLlegada = LocalDate.of(2025, 11, 2);

		orden = new OrdenDeExportacion(mockShipper, mockContainer, patenteCamion, dniChofer, fechaSalida, fechaLlegada);
	}

	@Test
	void testVerificarSiElShipperQueDevuelveEsElCorrecto() {
		//Ejecucion
		Shipper shipper = orden.getShipper();

		//Assert
		assertEquals(mockShipper, shipper);
	}

	@Test
	void testVerificarQueLeAsigneElTurnoAlShipper() {
		//setUp
		LocalDateTime turno = LocalDateTime.of(2025, 10, 28, 15, 0);

		//Ejecucion
		orden.asignarTurno(turno);

		//Assert
		verify(mockShipper).fechaDeExportacion(LocalDateTime.of(2025, 10, 28, 15, 0));
	}

	@Test
	void testGetFechaSalidaYFechaLlegada() {

		//Assert
		assertEquals(fechaSalida, orden.getFechaSalida());
		assertEquals(fechaLlegada, orden.getFechaLlegada());

	}

}
