package filtro;

import java.time.LocalDate;
import java.util.Date;

import interfaces.IRutaMaritima;

public class FechaSalidaDespuesDe extends FiltroSimpleConFecha {

	public FechaSalidaDespuesDe(LocalDate unaFecha) {
		this.fechaFiltro = unaFecha;
	}

	@Override
	public boolean cumple(IRutaMaritima unaRutaMaritima) {
		return unaRutaMaritima.fechaSalida().isAfter(this.fechaFiltro);
	}

}
