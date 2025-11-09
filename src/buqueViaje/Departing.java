package buqueViaje;

public class Departing extends FaseBuqueViaje {
	
	@Override
	public void coordenadasActualizadas(BuqueViaje bv) {
		Coordenadas coordenadasBuqueViaje = bv.getCoordenadas();
		Coordenadas coordenadasTerminal = bv.getTerminal().getCoordenadas();
		
		if(coordenadasBuqueViaje.distanciaA(coordenadasTerminal) > 0) {
			bv.getTerminal().avisoDeSalida(bv);
			bv.setFase(new Outbound());
		}
	}
}
