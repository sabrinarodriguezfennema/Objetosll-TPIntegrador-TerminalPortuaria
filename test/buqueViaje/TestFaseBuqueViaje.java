package buqueViaje;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buqueViaje.Arrived;
import buqueViaje.BuqueViaje;
import buqueViaje.Coordenadas;
import buqueViaje.Departing;
import buqueViaje.FaseBuqueViaje;
import buqueViaje.Inbound;
import buqueViaje.Outbound;
import buqueViaje.Working;
import paraMock.TerminalGestionada;

class TestFaseBuqueViaje {

	@BeforeEach
	void setUp() throws Exception {
	}

	//Outbound
	
	@Test
	void testOutboundNoCambiaDeFaseSiEstáAMásDe50KmDeLaTerminal() {
		BuqueViaje buqueViaje = mock(BuqueViaje.class);
		when(buqueViaje.getCoordenadas()).thenReturn(new Coordenadas(50, 0));
		
		TerminalGestionada mockTerminal = mock(TerminalGestionada.class); 
		when(buqueViaje.getTerminal()).thenReturn(mockTerminal);
		when(mockTerminal.getCoordenadas()).thenReturn(new Coordenadas(0, 0));
		
		FaseBuqueViaje faseBuqueViaje = new Outbound();
		
		faseBuqueViaje.coordenadasActualizadas(buqueViaje);
		
		verify(buqueViaje, never()).setFase(any());
	}
	
	@Test
	void testOutboundCambiaDeFaseSiEstáAMenosDe50KmDeLaTerminal() {
		BuqueViaje buqueViaje = mock(BuqueViaje.class); 
		when(buqueViaje.getCoordenadas()).thenReturn(new Coordenadas(40, 0));
		
		TerminalGestionada mockTerminal = mock(TerminalGestionada.class); 
		when(buqueViaje.getTerminal()).thenReturn(mockTerminal);
		when(mockTerminal.getCoordenadas()).thenReturn(new Coordenadas(0, 0));
		
		FaseBuqueViaje faseBuqueViaje = new Outbound();
		
		faseBuqueViaje.coordenadasActualizadas(buqueViaje);
		
		verify(buqueViaje).setFase(any());
		verify(mockTerminal).inminenteArribo(buqueViaje);
	}
	
	//Inbound
	
	@Test
	void testInboundNoCambiaDeFaseSiEstáEntre50y0kmDeLaTerminal() {
		BuqueViaje buqueViaje = mock(BuqueViaje.class);
		when(buqueViaje.getCoordenadas()).thenReturn(new Coordenadas(30, 0));
		
		TerminalGestionada mockTerminal = mock(TerminalGestionada.class); 
		when(buqueViaje.getTerminal()).thenReturn(mockTerminal);
		when(mockTerminal.getCoordenadas()).thenReturn(new Coordenadas(0, 0));
		
		FaseBuqueViaje faseBuqueViaje = new Inbound(buqueViaje);
		
		faseBuqueViaje.coordenadasActualizadas(buqueViaje);
		
		verify(buqueViaje, never()).setFase(any());
	}
	
	@Test
	void testInboundCambiaDeFaseSiEstáAMásDe50kmDeLaTerminal() {
		BuqueViaje buqueViaje = mock(BuqueViaje.class);
		when(buqueViaje.getCoordenadas()).thenReturn(new Coordenadas(53, 0));
		
		TerminalGestionada mockTerminal = mock(TerminalGestionada.class); 
		when(buqueViaje.getTerminal()).thenReturn(mockTerminal);
		when(mockTerminal.getCoordenadas()).thenReturn(new Coordenadas(0, 0));
		
		FaseBuqueViaje faseBuqueViaje = new Inbound(buqueViaje);
		
		faseBuqueViaje.coordenadasActualizadas(buqueViaje);
		
		verify(buqueViaje).setFase(any());
	}
	
	@Test
	void testInboundCambiaDeFaseSiEstáA0kmDeLaTerminal() {
		BuqueViaje buqueViaje = mock(BuqueViaje.class);
		when(buqueViaje.getCoordenadas()).thenReturn(new Coordenadas(0, 0));
		
		TerminalGestionada mockTerminal = mock(TerminalGestionada.class); 
		when(buqueViaje.getTerminal()).thenReturn(mockTerminal);
		when(mockTerminal.getCoordenadas()).thenReturn(new Coordenadas(0, 0));
		
		FaseBuqueViaje faseBuqueViaje = new Inbound(buqueViaje);
		
		faseBuqueViaje.coordenadasActualizadas(buqueViaje);
		
		verify(buqueViaje).setFase(any());
		verify(mockTerminal).avisoDeLlegada(buqueViaje);
	}
	
	//Arrived
	@Test
	void testArrivedCambiaDeFaseSiSeLeIndicaIniciarSuTrabajo() {
		BuqueViaje buqueViaje = mock(BuqueViaje.class);
		
		TerminalGestionada mockTerminal = mock(TerminalGestionada.class); 
		when(buqueViaje.getTerminal()).thenReturn(mockTerminal);
		
		FaseBuqueViaje faseBuqueViaje = new Arrived(buqueViaje);
		
		faseBuqueViaje.inicioDeTrabajo(buqueViaje);
		
		verify(buqueViaje).setFase(any());
	}
	
	//Working
		@Test
		void testWorkingCambiaDeFaseSiSeLeIndicaPartir() {
			BuqueViaje buqueViaje = mock(BuqueViaje.class);
			
			TerminalGestionada mockTerminal = mock(TerminalGestionada.class); 
			when(buqueViaje.getTerminal()).thenReturn(mockTerminal);
			
			FaseBuqueViaje faseBuqueViaje = new Working(buqueViaje);
			
			faseBuqueViaje.depart(buqueViaje);
			
			verify(buqueViaje).setFase(any());
		}
		
		//Departing
		
		@Test
		void testDepartingNoCambiaDeFaseSiEstáA0KmDeLaTerminal() {
			BuqueViaje buqueViaje = mock(BuqueViaje.class);
			when(buqueViaje.getCoordenadas()).thenReturn(new Coordenadas(0, 0));
			
			TerminalGestionada mockTerminal = mock(TerminalGestionada.class); 
			when(buqueViaje.getTerminal()).thenReturn(mockTerminal);
			when(mockTerminal.getCoordenadas()).thenReturn(new Coordenadas(0, 0));
			
			FaseBuqueViaje faseBuqueViaje = new Departing();
			
			faseBuqueViaje.coordenadasActualizadas(buqueViaje);
			
			verify(buqueViaje, never()).setFase(any());
		}
		
		@Test
		void testDepartingCambiaDeFaseSiEstáAMásDe0KmDeLaTerminal() {
			BuqueViaje buqueViaje = mock(BuqueViaje.class);
			when(buqueViaje.getCoordenadas()).thenReturn(new Coordenadas(1, 0));
			
			TerminalGestionada mockTerminal = mock(TerminalGestionada.class); 
			when(buqueViaje.getTerminal()).thenReturn(mockTerminal);
			when(mockTerminal.getCoordenadas()).thenReturn(new Coordenadas(0, 0));
			
			FaseBuqueViaje faseBuqueViaje = new Departing();
			
			faseBuqueViaje.coordenadasActualizadas(buqueViaje);
			
			verify(buqueViaje).setFase(any());
			verify(mockTerminal).avisoDeSalida(buqueViaje);
		}
		
		//Otros
		
		@Test
		void testSiAUnaFaseDistintaDeArrivedSeLeOrdenaInicioTrabajoNoSeHaceNada() { //O tira un error, o todas pasan a working
			BuqueViaje buqueViaje = mock(BuqueViaje.class);
			FaseBuqueViaje faseBuqueViaje = new Working(buqueViaje);
			TerminalGestionada mockTerminal = mock(TerminalGestionada.class); 
			when(buqueViaje.getTerminal()).thenReturn(mockTerminal);
			
			faseBuqueViaje.inicioDeTrabajo(buqueViaje);
			
			verify(buqueViaje, never()).setFase(any());
			verifyNoInteractions(mockTerminal);
		}
		
		@Test
		void testSiAUnaFaseDistintaDeWorkingSeLeOrdenaDepartNoSeHaceNada() { //O tira un error, o todas pasan a departing
			BuqueViaje buqueViaje = mock(BuqueViaje.class);
			TerminalGestionada mockTerminal = mock(TerminalGestionada.class); 
			when(buqueViaje.getTerminal()).thenReturn(mockTerminal);
			FaseBuqueViaje faseBuqueViaje = new Arrived(buqueViaje);
			
			faseBuqueViaje.depart(buqueViaje);
			
			verify(buqueViaje, never()).setFase(any());
		}
}
