package interfaces;

import buqueViaje.Coordenadas;
import paraMock.TerminalGestionada;

public interface IBuqueViaje {
	
	public void recibirCoordenadas(Coordenadas cs);
	public TerminalGestionada getTerminal();
	public Coordenadas getCoordenadas();
	
	public void inicioDeTrabajo();
	public void depart();
}
