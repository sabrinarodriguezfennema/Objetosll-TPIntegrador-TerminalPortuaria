package buqueViaje;

public class Departing extends FaseBuqueViaje {
	
	@Override
	public void coordenadasActualizadas(BuqueViaje bv) {
		Coordenadas coordenadasBuqueViaje = bv.getCoordenadas();
		Coordenadas coordenadasDestino = bv.getDestino().getCoordenadas();
		
		if(coordenadasBuqueViaje.distanciaA(coordenadasDestino) > 0) {
			bv.getNotificable().avisoDeSalida(bv);
			bv.setFase(new Outbound(bv));
		}
	}
}
