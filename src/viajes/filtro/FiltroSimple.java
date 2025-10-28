package viajes.filtro;

import java.util.function.BooleanSupplier;

import viajes.filtros.Filtro;

public abstract class FiltroSimple extends Filtro{

	public abstract boolean cumple(RutaMaritima unaRutaMaritima);

}
