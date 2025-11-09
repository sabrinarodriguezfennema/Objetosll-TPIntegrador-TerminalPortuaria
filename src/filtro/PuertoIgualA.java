package filtro;

import interfaces.Terminal;
import paraMock.RutaMaritima;

public class PuertoIgualA extends FiltroSimple {

	private Terminal puerto;
	
	public PuertoIgualA(Terminal puerto) {
		this.puerto = puerto;
	}

	@Override
	public boolean cumple(RutaMaritima unaRutaMaritima) {
		return unaRutaMaritima.puertoDestino() == this.puerto;
	}

	
}
