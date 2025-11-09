package filtro;

import java.util.function.BooleanSupplier;

import paraMock.RutaMaritima;

public abstract class FiltroSimple extends Filtro{

	public abstract boolean cumple(RutaMaritima unaRutaMaritima);

}
