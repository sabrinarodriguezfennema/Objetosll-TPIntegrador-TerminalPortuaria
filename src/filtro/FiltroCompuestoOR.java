package filtro;

import paraMock.RutaMaritima;

public class FiltroCompuestoOR extends FiltroCompuesto {

	public FiltroCompuestoOR(Filtro filtro1, Filtro filtro2) {
		super(filtro1, filtro2);
	}

	@Override
	public boolean cumple(RutaMaritima rm) {
		return this.filtro1.cumple(rm) || this.filtro2.cumple(rm);
	}

}
