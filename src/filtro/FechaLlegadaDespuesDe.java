package filtro;

import java.util.Date;

import paraMock.RutaMaritima;

public class FechaLlegadaDespuesDe extends FiltroSimpleConFecha {

	public FechaLlegadaDespuesDe(Date unaFecha) {
		this.fechaFiltro = unaFecha;
	}

	@Override
	public boolean cumple(RutaMaritima unaRutaMaritima) {
		return unaRutaMaritima.fechaLlegada().after(this.fechaFiltro);
	}

}
