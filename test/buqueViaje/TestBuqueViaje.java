package buqueViaje;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import circuito.Circuito;
import interfaces.ITerminal;
import interfaces.IViaje;
import interfaces.Localizable;
import paraMock.Viaje;
import terminal.Notificable;

class TestBuqueViaje {
	IViaje unViaje;
	BuqueViaje unBuqueViaje;
	Notificable unNotificable;
	Localizable unDestino;
	
	@BeforeEach
	void setUp() throws Exception {
		unViaje = mock(IViaje.class);
		unNotificable = mock(Notificable.class);
		unDestino = mock(Localizable.class);
		unBuqueViaje = new BuqueViaje(unViaje, unDestino, unNotificable);
	}

	@Test
	void testUnBuqueViajeConoceSuNotificable() {
		Notificable resultado = unBuqueViaje.getNotificable();
		
		assertEquals(unNotificable, resultado);
	}
	
	@Test
	void testUnBuqueViajeConoceSuDestino() {
		Localizable resultado = unBuqueViaje.getDestino();
		
		assertEquals(unDestino, resultado);
	}
	
	@Test
	void testUnBuqueViajeConoceSusCoordenadas() {
		Coordenadas unasCoordenadas = mock(Coordenadas.class);
		unBuqueViaje.recibirCoordenadas(unasCoordenadas);
		
		Coordenadas resultado = unBuqueViaje.getCoordenadas();
		
		assertEquals(unasCoordenadas, resultado);
	}
	
	@Test
	void testUnBuqueViajeConoceSuViaje() {		
		IViaje resultado = unBuqueViaje.getViaje();
		
		assertEquals(unViaje, resultado);
	}
	
	@Test
	void testUnBuqueViajeIniciaAlComienzoDeSuViaje() {
		Coordenadas resultado = unBuqueViaje.getCoordenadas();
		Circuito unCircuito = mock(Circuito.class);
		
		when(unViaje.getCircuito()).thenReturn(unCircuito); //.getTodasLasTerminales().get(0).getCoordenadas()).thenReturn(new Coordenadas(0, 0)
		List<ITerminal> todasLasTerminales = mock(List.class);
		
		when(unCircuito.getTodasLasTerminales()).thenReturn(todasLasTerminales); //.get(0).getCoordenadas()).thenReturn(new Coordenadas(0, 0)
		ITerminal unaTerminal = mock(ITerminal.class);
		
		when(todasLasTerminales.get(0)).thenReturn(unaTerminal); //.getCoordenadas()).thenReturn(new Coordenadas(0, 0)
		Coordenadas unasCoordenadas = new Coordenadas(0, 0);
		
		when(unaTerminal.getCoordenadas()).thenReturn(unasCoordenadas); 
		Coordenadas coordenadasDeLaPrimeraTerminal = unaTerminal.getCoordenadas();
		
		assertEquals(coordenadasDeLaPrimeraTerminal, resultado);
	}
	
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
