package buqueViaje;

public class Inbound extends FaseBuqueViaje {
	
	public Inbound(BuqueViaje bv){
		bv.getTerminal().inminenteArribo(bv);
	}
	
	@Override
	public void coordenadasActualizadas(BuqueViaje bv){
		Coordenadas coordenadasBuqueViaje = bv.getCoordenadas();
		Coordenadas coordenadasTerminal = bv.getTerminal().getCoordenadas();
		
		if(coordenadasBuqueViaje.distanciaA(coordenadasTerminal) >= 50) {
			bv.setFase(new Outbound());
		}
		if(coordenadasBuqueViaje.distanciaA(coordenadasTerminal) == 0) {
			bv.setFase(new Arrived(bv));
		}
	}
}
