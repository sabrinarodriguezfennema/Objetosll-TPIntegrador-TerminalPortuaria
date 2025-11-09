package buqueViaje;

import interfaces.IBuqueViaje;
import paraMock.TerminalGestionada;
import paraMock.Viaje;

public class BuqueViaje implements IBuqueViaje{
	
	private TerminalGestionada terminal;
	private Viaje viaje;
	private FaseBuqueViaje faseBuqueViaje;
	private Coordenadas coordenadas;

	public BuqueViaje(Viaje unViaje, TerminalGestionada unaTerminal) {
		this.viaje = unViaje;
		this.terminal = unaTerminal;
		this.faseBuqueViaje = new Outbound();
	}

	@Override
	public void recibirCoordenadas(Coordenadas cs) {
		this.coordenadas = cs;
		faseBuqueViaje.coordenadasActualizadas(this);

	}

	@Override
	public TerminalGestionada getTerminal() {
		return this.terminal;
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
