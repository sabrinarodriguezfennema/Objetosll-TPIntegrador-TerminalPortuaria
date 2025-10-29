package Terminal;

import java.time.LocalDateTime;

import InterfacesParaMockear.BuqueViaje;
import Ordenes.OrdenDeExportacion;
import Traslado.EmpresaTransportista;

public interface GestionLogistica {
	
	public void registrarCamion(String patenteCamion);
	public void registrarChofer(String dniChofer);
	public void registrarEmpresaTransportista(EmpresaTransportista empresa);
	public boolean verificarCargaLlegada(String patenteCamion, OrdenDeExportacion orden, String dniChofer, LocalDateTime hora);
	public boolean verificarCargaRetiro(String patenteCamion, String dniChofer);
	public void avisoDeSalida(BuqueViaje bv);
	public void avisoDeLlegada(BuqueViaje bv);
}
