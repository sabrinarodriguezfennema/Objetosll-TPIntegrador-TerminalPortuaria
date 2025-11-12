package paraMock;

import buqueViaje.Coordenadas;
import interfaces.IBuqueViaje;
import interfaces.ITerminal;
import terminal.Notificable;

public interface TerminalGestionada extends ITerminal, Notificable{
	
	public Coordenadas getCoordenadas();

	public void avisoDeSalida(IBuqueViaje bv);

	public void inminenteArribo(IBuqueViaje bv);

	public void avisoDeLlegada(IBuqueViaje bv);

}
