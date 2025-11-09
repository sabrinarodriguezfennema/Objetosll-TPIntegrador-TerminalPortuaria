package paraMock;

import buqueViaje.Coordenadas;
import interfaces.IBuqueViaje;
import interfaces.Terminal;

public interface TerminalGestionada extends Terminal{
	
	public Coordenadas getCoordenadas();

	public void avisoDeSalida(IBuqueViaje bv);

	public void inminenteArribo(IBuqueViaje bv);

	public void avisoDeLlegada(IBuqueViaje bv);

}
