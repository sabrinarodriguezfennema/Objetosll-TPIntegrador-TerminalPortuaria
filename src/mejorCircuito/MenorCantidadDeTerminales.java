package mejorCircuito;

import interfaces.RutaMaritima;

public class MenorCantidadDeTerminales extends MejorCircuito{

	@Override
	protected boolean condicionDeMejor(RutaMaritima current, RutaMaritima mejor) {
		return current.getCircuito().cantidadDeTerminales() < mejor.getCircuito().cantidadDeTerminales();
	}

}
