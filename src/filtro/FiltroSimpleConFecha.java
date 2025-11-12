package filtro;

import java.time.LocalDate;

import interfaces.IRutaMaritima;

public abstract class FiltroSimpleConFecha extends FiltroSimple {

	LocalDate fechaFiltro;
	
	@Override
	public abstract boolean cumple(IRutaMaritima unaRutaMaritima);

}
