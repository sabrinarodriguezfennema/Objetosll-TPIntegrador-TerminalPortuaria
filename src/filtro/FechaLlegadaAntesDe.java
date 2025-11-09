package filtro;

import java.util.Date;

import paraMock.RutaMaritima;

public class FechaLlegadaAntesDe extends FiltroSimpleConFecha {


	public FechaLlegadaAntesDe(Date unaFecha) {
		this.fechaFiltro = unaFecha;
	}

	@Override
	public boolean cumple(RutaMaritima unaRutaMaritima) {
		return unaRutaMaritima.fechaLlegada().before(this.fechaFiltro);
	}

}
