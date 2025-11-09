package filtro;

import paraMock.RutaMaritima;

public abstract class FiltroCompuesto extends Filtro {
	
	Filtro filtro1;
	Filtro filtro2;

	public FiltroCompuesto(Filtro filtro1, Filtro filtro2) {
		this.filtro1 = filtro1;
		this.filtro2 = filtro2;
	}

	@Override
	public abstract boolean cumple(RutaMaritima rm);
}
