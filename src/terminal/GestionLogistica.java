package terminal;

import java.time.LocalDateTime;
import interfaces.EmpresaTransportista;
import interfaces.IBuqueViaje;

public interface GestionLogistica {
	
	public void registrarCamion(String patenteCamion);
	public void registrarChofer(String dniChofer);
	public void registrarEmpresaTransportista(EmpresaTransportista empresa);
	public boolean verificarCargaLlegada(String patenteCamion, String dniChofer, LocalDateTime hora);
	public boolean verificarCargaRetiro(String patenteCamion, String dniChofer);
	public void avisoDeSalida(IBuqueViaje bv);
	public void avisoDeLlegada(IBuqueViaje bv);
	public void inminenteArribo(IBuqueViaje bv);
}
