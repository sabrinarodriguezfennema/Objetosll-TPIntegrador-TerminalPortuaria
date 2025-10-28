package viajes.filtro;

import java.util.Date;

public class FechaLlegadaMayorA extends FiltroSimple {

	private Date fechaLlegada;

	public FechaLlegadaMayorA(Date unaFecha) {
		this.fechaLlegada = unaFecha;
	}

	@Override
	public boolean cumple(RutaMaritima unaRutaMaritima) {
		return unaRutaMaritima.fechaLlegada().after(this.fechaLlegada);
	}

}
