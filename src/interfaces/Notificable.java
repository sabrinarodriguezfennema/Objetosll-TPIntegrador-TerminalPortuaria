package interfaces;

public interface Notificable {
	public void avisoDeLlegada(IBuqueViaje bv);
	
	public void avisoDeSalida(IBuqueViaje bv);
	
	public void inminenteArribo(IBuqueViaje bv);
}
