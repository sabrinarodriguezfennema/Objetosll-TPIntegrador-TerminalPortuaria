package buqueViaje;

public class Working extends FaseBuqueViaje {
	public Working(BuqueViaje bv) {
		//Carga y descarga
	}
	
	@Override
	public void depart(BuqueViaje bv) {
		bv.setFase(new Departing(bv));
	}
	
}
