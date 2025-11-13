package filtro;

import java.time.LocalDateTime;

import interfaces.IRutaMaritima;

public class FechaLlegadaAntesDe extends FiltroSimpleConFecha {


	public FechaLlegadaAntesDe(LocalDateTime unaFecha) {
		this.fechaFiltro = unaFecha;
	}

	@Override
	public boolean cumple(IRutaMaritima unaRutaMaritima) {
		return unaRutaMaritima.fechaLlegada().isBefore(this.fechaFiltro);
	}

}
