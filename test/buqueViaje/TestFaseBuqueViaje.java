package buqueViaje;

import static org.mockito.Mockito.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import interfaces.Localizable;
import terminal.Notificable;

class TestFaseBuqueViaje {
	BuqueViaje buqueViaje;
	Notificable unNotificable;
	Localizable unDestino; 

	@BeforeEach
	void setUp() throws Exception {
		buqueViaje    = mock(BuqueViaje.class);
		unNotificable = mock(Notificable.class);
		unDestino     = mock(Localizable.class);
		
		when(buqueViaje.getNotificable()).thenReturn(unNotificable); 
		
		when(buqueViaje.getDestino()).thenReturn(unDestino);
		when(unDestino.getCoordenadas()).thenReturn(new Coordenadas(0, 0));
	}

	//Outbound
	
	@Test
	void testOutboundNoCambiaDeFaseSiEstáAMásDe50KmDeLaTerminal() {
		when(buqueViaje.getCoordenadas()).thenReturn(new Coordenadas(50, 0));
		
		FaseBuqueViaje faseBuqueViaje = new Outbound(buqueViaje);
		
		faseBuqueViaje.coordenadasActualizadas(buqueViaje);
		
		verify(buqueViaje, never()).setFase(any());
	}
	
	@Test
	void testOutboundCambiaDeFaseSiEstáAMenosDe50KmDeLaTerminal() {
		when(buqueViaje.getCoordenadas()).thenReturn(new Coordenadas(40, 0));
		
		FaseBuqueViaje faseBuqueViaje = new Outbound(buqueViaje);
		
		faseBuqueViaje.coordenadasActualizadas(buqueViaje);
		
		verify(buqueViaje).setFase(any());
		verify(unNotificable).inminenteArribo(buqueViaje);
	}
	
	//Inbound
	
	@Test
	void testInboundNoCambiaDeFaseSiEstáEntre50y0kmDeLaTerminal() {
		when(buqueViaje.getCoordenadas()).thenReturn(new Coordenadas(30, 0));
		
		FaseBuqueViaje faseBuqueViaje = new Inbound(buqueViaje);
		
		faseBuqueViaje.coordenadasActualizadas(buqueViaje);
		
		verify(buqueViaje, never()).setFase(any());
	}
	
	@Test
	void testInboundCambiaDeFaseSiEstáAMásDe50kmDeLaTerminal() {
		when(buqueViaje.getCoordenadas()).thenReturn(new Coordenadas(53, 0));
		
		FaseBuqueViaje faseBuqueViaje = new Inbound(buqueViaje);
		
		faseBuqueViaje.coordenadasActualizadas(buqueViaje);
		
		verify(buqueViaje).setFase(any());
	}
	
	@Test
	void testInboundCambiaDeFaseSiEstáA0kmDeLaTerminal() {
		when(buqueViaje.getCoordenadas()).thenReturn(new Coordenadas(0, 0));
		
		FaseBuqueViaje faseBuqueViaje = new Inbound(buqueViaje);
		
		faseBuqueViaje.coordenadasActualizadas(buqueViaje);
		
		verify(buqueViaje).setFase(any());
		verify(unNotificable).avisoDeLlegada(buqueViaje);
	}
	
	//Arrived
	@Test
	void testArrivedCambiaDeFaseSiSeLeIndicaIniciarSuTrabajo() {		
		FaseBuqueViaje faseBuqueViaje = new Arrived(buqueViaje);
		
		faseBuqueViaje.inicioDeTrabajo(buqueViaje);
		
		verify(buqueViaje).setFase(any());
	}
	
	//Working
		@Test
		void testWorkingCambiaDeFaseSiSeLeIndicaPartir() {			
			FaseBuqueViaje faseBuqueViaje = new Working(buqueViaje);
			
			faseBuqueViaje.depart(buqueViaje);
			
			verify(buqueViaje).setFase(any());
		}
		
		//Departing
		
		@Test
		void testDepartingNoCambiaDeFaseSiEstáA0KmDeLaTerminal() {
			when(buqueViaje.getCoordenadas()).thenReturn(new Coordenadas(0, 0));
			
			FaseBuqueViaje faseBuqueViaje = new Departing(buqueViaje);
			
			faseBuqueViaje.coordenadasActualizadas(buqueViaje);
			
			verify(buqueViaje, never()).setFase(any());
		}
		
		@Test
		void testDepartingCambiaDeFaseSiEstáAMásDe0KmDeLaTerminal() {
			when(buqueViaje.getCoordenadas()).thenReturn(new Coordenadas(1, 0));
			
			FaseBuqueViaje faseBuqueViaje = new Departing(buqueViaje);
			
			faseBuqueViaje.coordenadasActualizadas(buqueViaje);
			
			verify(buqueViaje).setFase(any());
			verify(unNotificable).avisoDeSalida(buqueViaje);
		}
		
		//Otros
		
		@Test
		void testSiAUnaFaseDistintaDeArrivedSeLeOrdenaInicioTrabajoNoSeHaceNada() { 
			FaseBuqueViaje faseBuqueViaje = new Working(buqueViaje);
			
			faseBuqueViaje.inicioDeTrabajo(buqueViaje);
			
			verify(buqueViaje, never()).setFase(any());
			verifyNoInteractions(unNotificable);
		}
		
		@Test
		void testSiAUnaFaseDistintaDeWorkingSeLeOrdenaDepartNoSeHaceNada() {
			FaseBuqueViaje faseBuqueViaje = new Arrived(buqueViaje);
			
			faseBuqueViaje.depart(buqueViaje);
			
			verify(buqueViaje, never()).setFase(any());
		}
}
