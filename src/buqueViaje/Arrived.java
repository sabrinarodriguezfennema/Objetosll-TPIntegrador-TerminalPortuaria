package buqueViaje;

public class Arrived extends FaseBuqueViaje {
	
	public Arrived(BuqueViaje bv){
	}
	
	@Override
	public void inicioDeTrabajo(BuqueViaje bv){
		bv.setFase(new Working(bv));
	}
}
