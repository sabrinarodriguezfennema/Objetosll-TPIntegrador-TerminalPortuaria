package filtro;

import java.time.LocalDate;

import interfaces.IRutaMaritima;

public class FechaSalidaAntesDe extends FiltroSimpleConFecha {

	public FechaSalidaAntesDe(LocalDate unaFecha) {
		this.fechaFiltro = unaFecha;
	}

	@Override
	public boolean cumple(IRutaMaritima unaRutaMaritima) {
		return unaRutaMaritima.fechaSalida().isBefore(this.fechaFiltro);
	}

}
