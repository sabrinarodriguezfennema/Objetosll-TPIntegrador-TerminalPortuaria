package terminal;

import java.util.List;

import excepciones.OperacionNoDisponibleException;
import interfaces.IShipper;
import interfaces.ITerminal;
import motorDeBusqueda.MotorDeBusqueda;
import interfaces.IConsignee;
import interfaces.IContainer;
import interfaces.IEmpresaTransportista;
import interfaces.IRutaMaritima;
import interfaces.IServicio;

public interface GestionEnvio {
	
	//Exportación
	public MotorDeBusqueda cronogramaExportacion(ITerminal t);
	
	public void exportar(IContainer c, Terminal t, IRutaMaritima rm, IShipper exportador, IEmpresaTransportista empresa) throws OperacionNoDisponibleException;

	//Importación
	public void datosParaElRetiro(IConsignee importador, IEmpresaTransportista empresa, IContainer c) throws OperacionNoDisponibleException;

}
