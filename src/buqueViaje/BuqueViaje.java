package buqueViaje;

import interfaces.IBuqueViaje;
import interfaces.Localizable;
import interfaces.Notificable;
import paraMock.TerminalGestionada;
import paraMock.Viaje;

public class BuqueViaje implements IBuqueViaje{
	
	private Viaje viaje;
	private FaseBuqueViaje faseBuqueViaje;
	private Coordenadas coordenadas;
	private Localizable destino;
	private Notificable aNotificar;

//	public BuqueViaje(Viaje unViaje, TerminalGestionada unaTerminal) {
//		this.viaje = unViaje;
//		this.terminal = unaTerminal;
//		this.faseBuqueViaje = new Outbound();
//	}
	
	public BuqueViaje(Viaje unViaje, Localizable unDestino, Notificable unNotificable) {
		this.viaje = unViaje;
		this.destino = unDestino;
		this.aNotificar = unNotificable;
		this.faseBuqueViaje = new Outbound(this);
	}

	@Override
	public void recibirCoordenadas(Coordenadas cs) {
		this.coordenadas = cs;
		faseBuqueViaje.coordenadasActualizadas(this);

	}

	public Localizable getDestino() {
		return this.destino;
	}
	
	public Notificable getNotificable() {
		return this.aNotificar;
	}

	@Override
	public Coordenadas getCoordenadas() {
		return coordenadas;
	}

	protected void setFase(FaseBuqueViaje fbv) {
		this.faseBuqueViaje = fbv;

	}

	@Override
	public void inicioDeTrabajo() {
		this.faseBuqueViaje.inicioDeTrabajo(this);

	}

	@Override
	public void depart() {
		this.faseBuqueViaje.depart(this);

	}

}
