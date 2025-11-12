package filtro;

import interfaces.ITerminal;
import paraMock.RutaMaritima;

public class PuertoIgualA extends FiltroSimple {

	private ITerminal puerto;
	
	public PuertoIgualA(ITerminal puerto) {
		this.puerto = puerto;
	}

	@Override
	public boolean cumple(RutaMaritima unaRutaMaritima) {
		return unaRutaMaritima.puertoDestino() == this.puerto;
	}

	
}
