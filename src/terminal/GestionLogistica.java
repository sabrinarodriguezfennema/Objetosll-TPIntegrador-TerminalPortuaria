package terminal;

import java.time.LocalDateTime;

import interfaces.BuqueViaje;
import interfaces.IConsignee;
import interfaces.Container;
import ordenes.OrdenDeExportacion;
import interfaces.EmpresaTransportista;

public interface GestionLogistica {
	
	public void registrarCamion(String patenteCamion);
	public void registrarChofer(String dniChofer);
	public void registrarEmpresaTransportista(EmpresaTransportista empresa);
	public boolean verificarCargaLlegada(String patenteCamion, String dniChofer, LocalDateTime hora);
	public boolean verificarCargaRetiro(String patenteCamion, String dniChofer);
	public void avisoDeSalida(BuqueViaje bv);
	public void avisoDeLlegada(BuqueViaje bv);
}
