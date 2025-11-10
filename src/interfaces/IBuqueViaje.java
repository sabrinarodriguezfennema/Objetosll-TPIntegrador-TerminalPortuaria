package interfaces;

import buqueViaje.Coordenadas;
// TODO sacar si sale bien import paraMock.TerminalGestionada;

public interface IBuqueViaje {
	
	public void recibirCoordenadas(Coordenadas cs);
	//public TerminalGestionada getTerminal();
	public Coordenadas getCoordenadas();
	public Localizable getDestino();
	public Notificable getNotificable();
	
	public void inicioDeTrabajo();
	public void depart();
}
