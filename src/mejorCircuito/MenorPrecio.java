package mejorCircuito;

import interfaces.IRutaMaritima;

public class MenorPrecio extends MejorCircuito {

	@Override
	protected boolean condicionDeMejor(IRutaMaritima current, IRutaMaritima mejor) {
		return current.getCircuito().precioTotal() < mejor.getCircuito().precioTotal();
	}

}
