package filtro;

import java.time.LocalDateTime;

import interfaces.IRutaMaritima;

public class FechaLlegadaDespuesDe extends FiltroSimpleConFecha {

	public FechaLlegadaDespuesDe(LocalDateTime unaFecha) {
		this.fechaFiltro = unaFecha;
	}

	@Override
	public boolean cumple(IRutaMaritima unaRutaMaritima) {
		return unaRutaMaritima.fechaLlegada().isAfter(this.fechaFiltro);
	}

}
