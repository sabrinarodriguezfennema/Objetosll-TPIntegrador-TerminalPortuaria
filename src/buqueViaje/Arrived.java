package buqueViaje;

public class Arrived extends FaseBuqueViaje {
	
	Arrived(BuqueViaje bv){
		bv.getTerminal().avisoDeLlegada(bv);
	}
	
	public void inicioDeTrabajo(BuqueViaje bv){
		bv.setFase(new Working(bv));
	}
}
