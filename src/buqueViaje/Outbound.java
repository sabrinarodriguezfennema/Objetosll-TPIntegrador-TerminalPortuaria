package buqueViaje;

public class Outbound extends FaseBuqueViaje {
	
	@Override
	public void coordenadasActualizadas(BuqueViaje bv) {
		Coordenadas coordenadasBuqueViaje = bv.getCoordenadas();
		Coordenadas coordenadasTerminal = bv.getTerminal().getCoordenadas();
		
		if(coordenadasBuqueViaje.distanciaA(coordenadasTerminal) < 50) {
			bv.setFase(new Inbound(bv));
		}
	}
}
