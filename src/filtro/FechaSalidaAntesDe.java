package filtro;

import java.time.LocalDateTime;

import interfaces.IRutaMaritima;

public class FechaSalidaAntesDe extends FiltroSimpleConFecha {

	public FechaSalidaAntesDe(LocalDateTime unaFecha) {
		this.fechaFiltro = unaFecha;
	}

	@Override
	public boolean cumple(IRutaMaritima unaRutaMaritima) {
		return unaRutaMaritima.fechaSalida().isBefore(this.fechaFiltro);
	}

}
