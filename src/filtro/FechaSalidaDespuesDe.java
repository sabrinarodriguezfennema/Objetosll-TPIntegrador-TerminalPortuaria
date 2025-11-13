package filtro;

import java.time.LocalDateTime;
import interfaces.IRutaMaritima;

public class FechaSalidaDespuesDe extends FiltroSimpleConFecha {

	public FechaSalidaDespuesDe(LocalDateTime unaFecha) {
		this.fechaFiltro = unaFecha;
	}

	@Override
	public boolean cumple(IRutaMaritima unaRutaMaritima) {
		return unaRutaMaritima.fechaSalida().isAfter(this.fechaFiltro);
	}

}
