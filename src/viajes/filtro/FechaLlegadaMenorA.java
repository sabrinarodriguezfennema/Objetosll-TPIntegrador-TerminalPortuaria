package viajes.filtro;

import java.util.Date;

public class FechaLlegadaMenorA extends FiltroSimple {

	private Date fechaLlegada;

	public FechaLlegadaMenorA(Date unaFecha) {
		this.fechaLlegada = unaFecha;
	}

	@Override
	public boolean cumple(RutaMaritima unaRutaMaritima) {
		return unaRutaMaritima.fechaLlegada().before(this.fechaLlegada);
	}

}
