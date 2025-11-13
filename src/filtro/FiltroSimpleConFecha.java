package filtro;

import java.time.LocalDateTime;

import interfaces.IRutaMaritima;

public abstract class FiltroSimpleConFecha extends FiltroSimple {

	LocalDateTime fechaFiltro;
	
	@Override
	public abstract boolean cumple(IRutaMaritima unaRutaMaritima);

}
