package viajes.filtro;

import java.util.function.BooleanSupplier;

public abstract class FiltroSimple extends Filtro{

	public abstract boolean cumple(RutaMaritima unaRutaMaritima);

}
