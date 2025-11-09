package mejorCircuito;

import interfaces.RutaMaritima;


public class FechaMasProxima extends MejorCircuito {

	@Override
	protected boolean condicionDeMejor(RutaMaritima current, RutaMaritima mejor) {
        return current.fechaSalida().isBefore(mejor.fechaSalida());
	}

}
