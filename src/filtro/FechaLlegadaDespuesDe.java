package filtro;

import java.time.LocalDate;
import java.util.Date;

import interfaces.IRutaMaritima;

public class FechaLlegadaDespuesDe extends FiltroSimpleConFecha {

	public FechaLlegadaDespuesDe(LocalDate unaFecha) {
		this.fechaFiltro = unaFecha;
	}

	@Override
	public boolean cumple(IRutaMaritima unaRutaMaritima) {
		return unaRutaMaritima.fechaLlegada().isAfter(this.fechaFiltro);
	}

}
