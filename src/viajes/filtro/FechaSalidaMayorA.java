package viajes.filtro;

import java.util.Date;

public class FechaSalidaMayorA extends FiltroSimple {

	private Date fechaLlegada;

	public FechaSalidaMayorA(Date unaFecha) {
		this.fechaLlegada = unaFecha;
	}

	@Override
	public boolean cumple(RutaMaritima unaRutaMaritima) {
		return unaRutaMaritima.fechaSalida().after(this.fechaLlegada);
	}

}
