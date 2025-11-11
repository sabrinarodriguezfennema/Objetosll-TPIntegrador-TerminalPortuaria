package mejorCircuito;

import interfaces.IRutaMaritima;

public class MenorTiempo extends MejorCircuito{

	@Override
	protected boolean condicionDeMejor(IRutaMaritima current, IRutaMaritima mejor) {
		return current.getCircuito().duracionTotal().compareTo(mejor.getCircuito().duracionTotal()) < 0;
	}

}
