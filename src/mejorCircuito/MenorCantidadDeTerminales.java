package mejorCircuito;

import interfaces.IRutaMaritima;

public class MenorCantidadDeTerminales extends MejorCircuito{

	@Override
	protected boolean condicionDeMejor(IRutaMaritima current, IRutaMaritima mejor) {
		return current.getCircuito().cantidadDeTerminales() < mejor.getCircuito().cantidadDeTerminales();
	}

}
