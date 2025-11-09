package mejorCircuito;

import interfaces.RutaMaritima;

public class MenorPrecio extends MejorCircuito {

	@Override
	protected boolean condicionDeMejor(RutaMaritima current, RutaMaritima mejor) {
		return current.getCircuito().precioTotal() < mejor.getCircuito().precioTotal();
	}

}
