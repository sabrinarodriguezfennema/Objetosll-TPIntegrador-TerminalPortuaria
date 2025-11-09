package filtro;

import java.util.Date;

import paraMock.RutaMaritima;

public class FechaSalidaDespuesDe extends FiltroSimpleConFecha {

	public FechaSalidaDespuesDe(Date unaFecha) {
		this.fechaFiltro = unaFecha;
	}

	@Override
	public boolean cumple(RutaMaritima unaRutaMaritima) {
		return unaRutaMaritima.fechaSalida().after(this.fechaFiltro);
	}

}
