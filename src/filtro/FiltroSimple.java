package filtro;

import java.util.function.BooleanSupplier;

import paraMock.RutaMaritima;

public abstract class FiltroSimple implements Filtro{

	public abstract boolean cumple(RutaMaritima unaRutaMaritima);

}
