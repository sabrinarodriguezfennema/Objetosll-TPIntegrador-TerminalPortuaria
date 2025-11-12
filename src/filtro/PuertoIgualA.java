package filtro;

import interfaces.ITerminal;
import interfaces.IRutaMaritima;

public class PuertoIgualA extends FiltroSimple {

	private ITerminal puerto;
	
	public PuertoIgualA(ITerminal puerto) {
		this.puerto = puerto;
	}

	@Override
	public boolean cumple(IRutaMaritima unaRutaMaritima) {
		return unaRutaMaritima.puertoDestino() == this.puerto;
	}

	
}
