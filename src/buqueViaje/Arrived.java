package buqueViaje;

public class Arrived extends FaseBuqueViaje {
	
	public Arrived(BuqueViaje bv){
		bv.getNotificable().avisoDeLlegada(bv);
	}
	
	public void inicioDeTrabajo(BuqueViaje bv){
		bv.setFase(new Working(bv));
	}
}
