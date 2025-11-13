package buqueViaje;

import interfaces.IBuqueViaje;
import interfaces.IViaje;
import interfaces.Localizable;
import terminal.Notificable;

public class BuqueViaje implements IBuqueViaje{
	
	private IViaje viaje;
	private FaseBuqueViaje faseBuqueViaje;
	private Coordenadas coordenadas;
	private Localizable destino;
	private Notificable aNotificar;
	
	public BuqueViaje(IViaje unViaje, Localizable unDestino, Notificable unNotificable) {
		this.viaje = unViaje;
		this.destino = unDestino;
		this.aNotificar = unNotificable;
		this.faseBuqueViaje = new Outbound(this);
		this.coordenadas = unViaje.getCircuito().getTodasLasTerminales().get(0).getCoordenadas();

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

	@Override
	public IViaje getViaje() {
		return viaje;
	}

}
