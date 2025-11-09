package filtro;

import java.util.Date;

import paraMock.RutaMaritima;

public abstract class FiltroSimpleConFecha extends FiltroSimple {

	Date fechaFiltro;
	
	@Override
	public abstract boolean cumple(RutaMaritima unaRutaMaritima);

}
