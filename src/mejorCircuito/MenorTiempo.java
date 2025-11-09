package mejorCircuito;

import interfaces.RutaMaritima;

public class MenorTiempo extends MejorCircuito{

	@Override
	protected boolean condicionDeMejor(RutaMaritima current, RutaMaritima mejor) {
		return current.getCircuito().duracionTotal().compareTo(mejor.getCircuito().duracionTotal()) < 0;
	}

}
