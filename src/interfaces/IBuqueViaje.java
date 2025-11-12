package interfaces;

import buqueViaje.Coordenadas;
import terminal.Notificable;
import viaje.Viaje;


public interface IBuqueViaje {
	
	public void recibirCoordenadas(Coordenadas cs);
	//public TerminalGestionada getTerminal();
	public Coordenadas getCoordenadas();
	public Localizable getDestino();
	public Notificable getNotificable();
	
	public void inicioDeTrabajo();
	public void depart();
	public IViaje getViaje();
}
