package buqueViaje;

public class Inbound extends FaseBuqueViaje {
	
	public Inbound(BuqueViaje bv){
		bv.getNotificable().inminenteArribo(bv);
	}
	
	@Override
	public void coordenadasActualizadas(BuqueViaje bv){
		Coordenadas coordenadasBuqueViaje = bv.getCoordenadas();
		Coordenadas coordenadasDestino = bv.getDestino().getCoordenadas();
		
		if(coordenadasBuqueViaje.distanciaA(coordenadasDestino) >= 50) {
			bv.setFase(new Outbound(bv));
		}
		if(coordenadasBuqueViaje.distanciaA(coordenadasDestino) == 0) {
			bv.setFase(new Arrived(bv));
			bv.getNotificable().avisoDeLlegada(bv);
		}
	}
}
