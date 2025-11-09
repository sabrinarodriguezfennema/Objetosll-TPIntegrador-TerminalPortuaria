package buqueViaje;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buqueViaje.Arrived;
import buqueViaje.BuqueViaje;
import buqueViaje.Coordenadas;
import buqueViaje.FaseBuqueViaje;
import buqueViaje.Working;
import interfaces.Terminal;
import paraMock.TerminalGestionada;
import paraMock.Viaje;

class testBuqueViaje {
	Viaje unViaje;
	TerminalGestionada unaTerminal;
	BuqueViaje unBuqueViaje;
	
	@BeforeEach
	void setUp() throws Exception {
		unViaje = mock(Viaje.class);
		unaTerminal = mock(TerminalGestionada.class);
		unBuqueViaje = new BuqueViaje(unViaje, unaTerminal);
	}

	@Test
	void testUnBuqueViajeConoceSuTerminal() {
		Terminal resultado = unBuqueViaje.getTerminal();
		
		assertEquals(unaTerminal, resultado);
	}
	
	@Test
	void testUnBuqueViajeConoceSusCoordenadas() {
		Coordenadas unasCoordenadas = mock(Coordenadas.class);
		unBuqueViaje.recibirCoordenadas(unasCoordenadas);
		
		Coordenadas resultado = unBuqueViaje.getCoordenadas();
		
		assertEquals(unasCoordenadas, resultado);
	}
	
//	@Test
//	void testUnBuqueViajeIniciaAlComienzoDeSuViaje() {
//		Coordenadas resultado = unBuqueViaje.getCoordenadas();
//		Coordenadas coordenadasDeLaPrimeraTerminal = ; 
//		
//		assertEquals(unaTerminal, resultado);
//	}
	
	@Test
	void testUnBuqueViajeLeAvisaASuFaseCuandoRecibeCoordenadas() {
		FaseBuqueViaje unaFaseEspía = spy(FaseBuqueViaje.class);
		unBuqueViaje.setFase(unaFaseEspía);
		Coordenadas unasCoordenadas = mock(Coordenadas.class);
		unBuqueViaje.recibirCoordenadas(unasCoordenadas);
		Coordenadas otrasCoordenadas = mock(Coordenadas.class);
		
		when(unasCoordenadas.distanciaA(otrasCoordenadas)).thenReturn(30d);
		verify(unaFaseEspía).coordenadasActualizadas(any());
	}
	
	@Test
	void testUnBuqueViajeLeAvisaASuFaseCuandoSeLeDaLaOrdenDeIniciarSuTrabajo() {
		FaseBuqueViaje unaFaseEspía = spy(new Arrived(unBuqueViaje));
		unBuqueViaje.setFase(unaFaseEspía);
		
		unBuqueViaje.inicioDeTrabajo();
		
		verify(unaFaseEspía).inicioDeTrabajo(unBuqueViaje);
	}
	
	@Test
	void testUnBuqueViajeLeAvisaASuFaseCuandoSeLeDaLaOrdenDeDepart() {
		FaseBuqueViaje unaFaseEspía = spy(new Working(unBuqueViaje));
		unBuqueViaje.setFase(unaFaseEspía);
		
		unBuqueViaje.depart();
		
		verify(unaFaseEspía).depart(unBuqueViaje);
	}

}
