package viajes.filtro;

import java.util.Date;

public class FechaSalidaMenorA extends FiltroSimple {

	private Date fechaSalida;

	public FechaSalidaMenorA(Date unaFecha) {
		this.fechaSalida = unaFecha;
	}

	@Override
	public boolean cumple(RutaMaritima unaRutaMaritima) {
		return unaRutaMaritima.fechaSalida().before(this.fechaSalida);
	}

}
