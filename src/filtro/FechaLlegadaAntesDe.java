package filtro;

import java.time.LocalDate;

import interfaces.IRutaMaritima;

public class FechaLlegadaAntesDe extends FiltroSimpleConFecha {


	public FechaLlegadaAntesDe(LocalDate unaFecha) {
		this.fechaFiltro = unaFecha;
	}

	@Override
	public boolean cumple(IRutaMaritima unaRutaMaritima) {
		return unaRutaMaritima.fechaLlegada().isBefore(this.fechaFiltro);
	}

}
