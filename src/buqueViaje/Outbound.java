package buqueViaje;

public class Outbound extends FaseBuqueViaje {
	
	public Outbound(BuqueViaje bv) {}
	
	@Override
	public void coordenadasActualizadas(BuqueViaje bv) {
		Coordenadas coordenadasBuqueViaje = bv.getCoordenadas();
		Coordenadas coordenadasTerminal = bv.getDestino().getCoordenadas();
		
		if(coordenadasBuqueViaje.distanciaA(coordenadasTerminal) < 50) {
			bv.setFase(new Inbound(bv));
		}
	}
}
