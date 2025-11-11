package mejorCircuito;

import interfaces.IRutaMaritima;


public class FechaMasProxima extends MejorCircuito {

	@Override
	protected boolean condicionDeMejor(IRutaMaritima current, IRutaMaritima mejor) {
        return current.fechaSalida().isBefore(mejor.fechaSalida());
	}

}
