package filtro;

import java.util.Date;

import paraMock.RutaMaritima;

public class FechaSalidaAntesDe extends FiltroSimpleConFecha {

	public FechaSalidaAntesDe(Date unaFecha) {
		this.fechaFiltro = unaFecha;
	}

	@Override
	public boolean cumple(RutaMaritima unaRutaMaritima) {
		return unaRutaMaritima.fechaSalida().before(this.fechaFiltro);
	}

}
